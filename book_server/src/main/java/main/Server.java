package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import entities.Book;
import jakarta.persistence.EntityManager;
import services.BookService;
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
	private BookService bookService;

	public ClientHandler(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
		this.mangerFactoryUtil = new EntityManagerFactoryUtil();
		this.entityManager = mangerFactoryUtil.getEnManager();
		this.bookService = new BookService(this.entityManager);
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
					int rating = in.readInt();
					List<Book> books = bookService.listRatedBooks(name, rating);
					out.writeObject(books);
					out.flush();
					break;
				case 2:
					Map<String, Long> map = bookService.countBooksByAuthor();
					out.writeObject(map);
					out.flush();
					break;
				case 3:
					String isbn = in.readUTF();
					String readerID = in.readUTF();
					int rating2 = in.readInt();
					String comment = in.readUTF();
					
					boolean kq = bookService.updateReviews(isbn, readerID, rating2, comment);
					out.writeBoolean(kq);
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
