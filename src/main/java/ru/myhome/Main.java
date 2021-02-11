package ru.myhome;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		try(AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(Builder.class)){
			CourseDAO courseDao = context.getBean("hibCourseDAO", CourseDAO.class);
			
			/*Course course = courseDao.findById(18);
			course.setLength(30);
			course.setTitle("Основы С++");
			course.setLength(20);
			course.setDescription("Программирование на С++");
			courseDao.insert(course);
			
			//System.out.println("Searching course for id(1)\n" + courseDao.findById(1));
			//courseDao.delete(17);
			courseDao.update(course);
			List<Course> courses = courseDao.findAll();
			for(Course item: courses) {
				System.out.println(item);
			}*/
			
			
			
			List<Course> coursesT = courseDao.findByTitle("Основы");
			for(Course item: coursesT) {
				System.out.println(item);
			}
		}

	}

}
