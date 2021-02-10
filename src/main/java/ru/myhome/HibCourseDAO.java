package ru.myhome;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class HibCourseDAO implements CourseDAO, InitializingBean{

	@Autowired
	private SessionFactory sessionFactory;
	
	public HibCourseDAO() {
		super();
		System.out.println("HibCourseDAO created");
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional(readOnly = true)
	public Course findById(int id) {
		return (Course) getSessionFactory().getCurrentSession().byId(Course.class).load(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findAll() {
		List<Course> courses = getSessionFactory().getCurrentSession().
				createQuery("from Course c", Course.class).list();
		return courses;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Course course) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Course course) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(getSessionFactory());
	}

}
