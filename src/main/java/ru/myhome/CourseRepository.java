package ru.myhome;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer>{
	
	Course findById(int id);
	List<Course> findAll();
	List<Course> findByTitle(String title);
}
