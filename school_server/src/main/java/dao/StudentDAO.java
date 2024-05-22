package dao;

import java.util.List;

import entities.Student;

public interface StudentDAO {
	
	public List<Student> findByEnrollmentInYear(int year);
	public List<Student> findStudentsEnrolledInCourseByCourseTitle(String title);
	
}
