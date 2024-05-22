package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import entities.Book;

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
						System.out.println("Nhap ten tac gia: ");
						String id = sc.nextLine();
						System.out.println("Nhap so danh gia (rating): ");
						int rating = sc.nextInt();
						out.writeUTF(id);
						out.writeInt(rating);
						out.flush();

						List<Book> books = (List<Book>) in.readObject();
						if (books != null) {
							System.out.println("Tim thay !");
							books.forEach(System.out::println);
						} else {
							System.out.println("Khong tim thay !");
						}
					case 2:
						Map<String, Long> map = (Map<String, Long>) in.readObject();
						map.forEach((author, cout) -> System.out
								.println(author + " has " + cout + " ban dich"));
						break;
					case 3:
						sc.nextLine();
						System.out.println("Nhap ma cuon sach: ");
						String isbn = sc.nextLine();
						System.out.println("Nhap ma doc gia: ");
						String readerID = sc.nextLine();
						System.out.println("Nhap ma doc gia: ");
						int rating2 = in.readInt();
						System.out.println("Nhap danh gia: ");
						String comment = in.readUTF();
						System.out.println("Nhap binh luan ");
						out.writeUTF(isbn);
						out.writeUTF(readerID);
						out.writeInt(rating2);
						out.writeUTF(comment);
						
						boolean kq = (boolean) in.readBoolean();
						if(kq) {
							System.out.println("Them thanh cong!");
						} else {
							System.out.println("Them thanh cong!");
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
