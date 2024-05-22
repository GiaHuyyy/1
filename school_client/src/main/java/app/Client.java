package app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entities.Department;
import entities.OnsiteCourse;
import entities.Student;

public class Client {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner(System.in)) {
			try (Socket socket = new Socket("172.16.33.42", 8888)) {

				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

				int choice = 0;
				System.out.println("Enter your choice: \n" + 
						"1. Get the number of courses by department\n" + 
						"2. Get the number of students enrolled in a course by course title\n" + 
						"3. Get list of onsite courses\n");
				
				while (true) {
					
					choice = sc.nextInt();
					out.writeInt(choice);
					out.flush();
					
					switch (choice) {
					case 1:
						
						Map<Department, Long> map = (Map<Department, Long>) in.readObject();
						map.entrySet().stream().forEach(entry -> {
							System.out.println(entry.getKey().getName() + " : " + entry.getValue());
						}); 
						break;

					case 2:
						sc.nextLine();
						System.out.println("Enter the course title to search: ");
						String title = sc.nextLine();
						out.writeUTF(title);
						out.flush();

						List<Student> students = (List<Student>) in.readObject();
						students.forEach(System.out::println);

						break;

					case 3:
						List<OnsiteCourse> onsiteCourses = (List<OnsiteCourse>) in.readObject();
						onsiteCourses.forEach(System.out::println);

						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
