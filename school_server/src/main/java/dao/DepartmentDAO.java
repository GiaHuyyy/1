package dao;

import java.util.Map;

import entities.Department;


public interface DepartmentDAO {
	// Count Course by Department
	public Map<Department, Long> countCoursesByDepartment();
}
