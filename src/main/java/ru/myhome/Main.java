package ru.myhome;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

	public static void main(String[] args) {
		try(AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(Builder.class)){
			CourseDAO courseDao = context.getBean("jpaCourseDAO", CourseDAO.class);
			
			/*Course course = new Course();
			course.setTitle("New cousre");
			course.setLength(25);
			course.setDescription("Very long description of a new intresting course");*/
			
			Course course = new Course();
			course.setId(19);
			course.setLength(45);
			updateCourse(courseDao, course);
			//findById(courseDao, 7);
			//addCourse(courseDao, course);
			//findByTitle(courseDao, "основы");
			showAllCourses(courseDao);
		}
	}
	
	private static void showAllCourses(CourseDAO courseDao) {
		List<Course> courses = courseDao.findAll();
		for(Course item: courses) {
			System.out.println(item);
		}
	}
	
	private static void findById(CourseDAO courseDao, int id) {
		System.out.println("Searching course by id("+id+")\n" + courseDao.findById(id));
	}
	
	private static void findByTitle(CourseDAO courseDao, String title) {
		List<Course> coursesT = courseDao.findByTitle(title);
		for(Course item: coursesT) {
			System.out.println(item);
		}
	}
	private static void addCourse(CourseDAO courseDao, Course course) {
		courseDao.insert(course);
	}
	private static void updateCourse(CourseDAO courseDao, Course course) {
		Course item = courseDao.findById(course.getId());
		if(item!=null) {
			if(course.getTitle()!=null) item.setTitle(course.getTitle());
			if(course.getLength()!=0) item.setLength(course.getLength());
			if(course.getDescription()!=null) item.setDescription(course.getDescription());
			courseDao.update(item);
		}
	}
	private static void delCourse(CourseDAO courseDao, int id) {
		courseDao.delete(id);
	}
}
