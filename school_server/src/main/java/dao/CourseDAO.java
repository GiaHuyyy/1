package dao;

import java.util.List;

import entities.Course;
import entities.OnsiteCourse;

public interface CourseDAO {
	public boolean addCourse(Course course);
	public boolean updateCourse(Course course);
	public boolean deleteCourse(int id);
	public Course findCourseByID(int id);
	public Course findCourseByTitle(String title);
	public List<Course> findCourseByTitleLike(String title);
	public List<Course> findAll(); 
	public List<OnsiteCourse> findAllOnsiteCourses();
}
