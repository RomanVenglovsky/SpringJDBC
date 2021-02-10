package ru.myhome;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		try(AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(Builder.class)){
			CourseDAO courseDao = context.getBean(CourseDAO.class);
			
			System.out.println("Seach corse fo id(1)\n\t" + courseDao.findById(1));
			List<Course> courses = courseDao.findAll();
			for(Course item: courses) {
				System.out.println(item);
			}
			/*Course course = new Course();
			course.setTitle("dfshs");
			course.setLength(10);
			course.setDescription("dfgsagsdfgsdf");
			
			courseDao.insert(course);
			
			
			/*List<Course> coursesT = courseDao.findByTitle("Web");
			for(Course item: coursesT) {
				System.out.println(item);
			}*/
		}

	}

}
