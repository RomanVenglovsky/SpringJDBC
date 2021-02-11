package ru.myhome;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcCourseDAO implements CourseDAO{

	private static final String SQL_SELECT_COURCE = 
			"SELECT id, title, length, description FROM courses";
	private static final String SQL_SELECT_BY_TITLE = 
			SQL_SELECT_COURCE + " WHERE title LIKE ?";
	private static final String SQL_INSERT_COURSE = 
			"INSERT INTO courses (title, length, description) VALUES (?, ?, ?)";
	
	@Autowired
	private JdbcTemplate template;
	
	public Course findById(int id) {
		
		return null;
	}

	public List<Course> findAll() {
		List<Course> courses = template.query(SQL_SELECT_COURCE, new BeanPropertyRowMapper<Course>(Course.class));
		return courses;
	}

	public List<Course> findByTitle(String title) {
		String sechString = "%"+title.trim()+"%";
		List<Course> courses = template.query(
				SQL_SELECT_BY_TITLE,
				new BeanPropertyRowMapper<Course>(Course.class),
				sechString);
		return courses;
	}

	public void insert(Course course) {
		template.update(SQL_INSERT_COURSE, course.getTitle(), course.getLength(), course.getDescription());
	}

	public void update(Course course) {
		// TODO Auto-generated method stub
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
	}

}
