package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entities.Candidate;
import entities.Certificate;
import entities.Position;
import jakarta.persistence.EntityManager;
import services.EmService;
import services.EntityManagerFactoryUtil;

public class Server {
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(1245);){
			
			System.out.println("Server started on port 1234");

			while (true) {
				Socket socket = server.accept();

				Thread t = new Thread(new ClientHandler(socket));
				t.start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ClientHandler implements Runnable {
	private Socket clientSocket;
	private EntityManagerFactoryUtil mangerFactoryUtil;
	private EntityManager entityManager;
	private EmService emService;

	public ClientHandler(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
		this.mangerFactoryUtil = new EntityManagerFactoryUtil();
		this.entityManager = mangerFactoryUtil.getEnManager();
		this.emService = new EmService(this.entityManager);
	}

	@Override
	public void run() {

		try {
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

			int choice = 0;

			while (true) {
				choice = in.readInt();
				
				switch (choice) {
				case 1:
					String name = in.readUTF();
					Double salaryFrom = in.readDouble();
					Double salaryTo = in.readDouble();
					List<Position> positions = emService.listPositions(name, salaryFrom, salaryTo);
					out.writeObject(positions);
					out.flush();
					break;
				case 2:
					Map<Candidate, Long> map = emService.listCadidatesByCompanies();
					out.writeObject(map);
					out.flush();
					break;
				case 3:
					Map<Candidate, Position> map2 = emService.listCandidatesWithLongestWorking();
					out.writeObject(map2);
					out.flush();
					break;
				case 4:
					Candidate candidate = (Candidate) in.readObject();
					boolean kq = emService.addCandidate(candidate);
					out.writeBoolean(kq);
					out.flush();
					break;
				case 5:
					String id = in.readUTF();
					Map<Position, Integer> map3 = emService.listYearsOfExperienceByPosition(id);
					out.writeObject(map3);
					out.flush();
					break;
				case 6:
					Map<Candidate, Set<Certificate>> map4 = emService.listCandidatesAndCertificates();
					out.writeObject(map4);
					out.flush();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.mangerFactoryUtil.closeEnManager();
			this.mangerFactoryUtil.closeEnManagerFactory();
		}

	}

}
