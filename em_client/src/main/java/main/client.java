package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import entities.Candidate;
import entities.Certificate;
import entities.Position;

public class client {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			try (Socket socket = new Socket("localhost", 1245)) {

				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

				int choice = 0;
				System.out.println("Enter your choice: \n" + "1. Get the number of courses by department\n"
						+ "2. Get the number of students enrolled in a course by course title\n"
						+ "3. Get the number of courses by department\n"
						+ "4. Get the number of courses by department\n"
						+ "5. Get the number of courses by department\n"
						+ "6. Get the number of courses by department\n");

				while (true) {

					choice = sc.nextInt();
					out.writeInt(choice);
					out.flush();

					switch (choice) {
					case 1:
						sc.nextLine();
						System.out.println("Nhap ten de tim: ");
						String id = sc.nextLine();
						System.out.println("Nhap gia tu: ");
						Double salaryFrom = sc.nextDouble();
						sc.nextLine();
						System.out.println("Nhap gia den: ");
						Double salaryTo = sc.nextDouble();
						out.writeUTF(id);
						out.writeDouble(salaryFrom);
						out.writeDouble(salaryTo);
						out.flush();

						List<Position> ps = (List<Position>) in.readObject();
						if (ps != null) {
							System.out.println("Tim thay !");
							ps.forEach(System.out::println);
						} else {
							System.out.println("Khong tim thay !");
						}
					case 2:
						Map<Candidate, Long> map = (Map<Candidate, Long>) in.readObject();
						map.forEach((candidate, companyCount) -> System.out
								.println(candidate + " worked in " + companyCount + " companies"));
						break;
					case 3:
						Map<Candidate, Position> map2 = (Map<Candidate, Position>) in.readObject();
						map2.forEach((candidate,
								position) -> System.out.println("Candidate [ " + candidate.getId()
										+ candidate.getFull_name() + " worked longest in Position[ " + position.getId()
										+ position.getName()));
						break;
					case 4:
						Candidate cd = new Candidate("S113", "một ngày mưa bão", "giahuy@gmail.com", "GiaHuy", "nam",
								"0932920885", 2003, new Position("P110"));
						out.writeObject(cd);
						out.flush();

						boolean kq = (boolean) in.readBoolean();
						if (kq) {
							System.out.println("Them Thanh Cong: " + cd);
						} else {
							System.out.println("Them That bai !");
						}
					case 5:
						sc.nextLine();
						System.out.println("Nhap ma Position de tim: ");
						String id2 = sc.nextLine();
						out.writeUTF(id2);
						out.flush();
						Map<Position, Integer> map3 = (Map<Position, Integer>) in.readObject();
						map3.forEach((position, years) -> System.out.println(
								"Position [ " + position.getId() + position.getName() + ": " + years + " years"));
						break;
					case 6:
						Map<Candidate, Set<Certificate>> map4 = (Map<Candidate, Set<Certificate>>) in.readObject();
						map4.forEach((candidate, certificate) -> System.out.println("Candidate [ " + candidate.getId()
								+ candidate.getFull_name() + "]: " + certificate));
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
