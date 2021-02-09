package ru.myhome;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		try(AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(Builder.class)){
			CourseDAO courseDao = context.getBean(JdbcCourseDAO.class);
			
			Course course = new Course();
			course.setTitle("dfshs");
			course.setLength(10);
			course.setDescription("dfgsagsdfgsdf");
			
			courseDao.insert(course);
			
			List<Course> courses = courseDao.findAll();
			for(Course item: courses) {
				System.out.println(item);
			}
			/*List<Course> coursesT = courseDao.findByTitle("Web");
			for(Course item: coursesT) {
				System.out.println(item);
			}*/
		}

	}

}
