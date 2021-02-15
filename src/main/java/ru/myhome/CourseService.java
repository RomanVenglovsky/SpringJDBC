package ru.myhome;

import java.util.List;

public interface CourseService{

	CourseRepository getCourseRepository();
	List<Course> findAll();
}
