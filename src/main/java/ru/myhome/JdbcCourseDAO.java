package ru.myhome;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		/*List<Course> courses = new ArrayList<Course>();
		List<Map<String, Object>> rows = template.queryForList(SQL_SELECT_COURCE);
		for(Map<String, Object> row: rows) {
			Course course = new Course();
			course.setId((int)(row.get("id")));
			course.setTitle((String)(row.get("title")));
			course.setLength((int)(row.get("length")));
			course.setDescription((String)(row.get("description")));
			courses.add(course);
			System.out.println(course);
		}*/
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

	public int insert(Course course) {
		return template.update(SQL_INSERT_COURSE, course.getTitle(), course.getLength(), course.getDescription());
	}

	public int update(Course course) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
