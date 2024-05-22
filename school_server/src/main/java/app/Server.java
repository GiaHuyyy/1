package app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import entities.Department;
import entities.OnsiteCourse;
import entities.Student;
import jakarta.persistence.EntityManager;
import services.CourseService;
import services.DepartmentService;
import services.EntityManagerFactoryUtil;
import services.StudentService;

public class Server {
	public static void main(String[] args) {

		try (ServerSocket server = new ServerSocket(8888);) {

			System.out.println("Server started on port 8888");
//			ExecutorService executorService = Executors.newCachedThreadPool();

			while (true) {
				Socket clientSocket = server.accept();

//				executorService.execute(new ClientHandler(clientSocket));
				
//				Way 2
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ClientHandler implements Runnable {
	private Socket clientSocket;
	private EntityManagerFactoryUtil mangerFactoryUtil;
	private EntityManager entityManager;
	private StudentService studentService;
	private DepartmentService departmentService;
	private CourseService courseService;

	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.mangerFactoryUtil = new EntityManagerFactoryUtil();
		this.entityManager = mangerFactoryUtil.getEnManager();
		this.studentService = new StudentService(this.entityManager);
		this.departmentService = new DepartmentService(this.entityManager);
		this.courseService = new CourseService(this.entityManager);
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
					Map<Department, Long> map = departmentService.countCoursesByDepartment();
					out.writeObject(map);
					out.flush();
					break;

				case 2:
					String title = in.readUTF();
					System.out.println("Client requested to search for: " + title);
					List<Student> students = studentService.findStudentsEnrolledInCourseByCourseTitle(title);
					out.writeObject(students);
					out.flush();
					break;

				case 3:
					List<OnsiteCourse> onsiteCourses = courseService.findAllOnsiteCourses();
					out.writeObject(onsiteCourses);
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