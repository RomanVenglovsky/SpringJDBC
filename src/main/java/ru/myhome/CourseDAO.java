package ru.myhome;

import java.util.List;

public interface CourseDAO {
	
	Course findById(int id);
	List<Course> findAll();
	List<Course> findByTitle(String title);
	
	int insert(Course course);
	int update(Course course);
	int delete(int id);

}
