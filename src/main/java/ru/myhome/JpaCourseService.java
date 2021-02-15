package ru.myhome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jpaCourseService")
@Transactional
@Repository
public class JpaCourseService implements CourseDAO{

	@Autowired
	private CourseRepository courseRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	public CourseRepository getRepository() {
		return courseRepository;
	}

	@Override
	public List<Course> findAll() {
		System.out.println("Seaching all course...");
		return new ArrayList<Course>(getRepository().findAll());
	}

	@Override
	public Course findById(int id) {
		return getRepository().findById(id);
	}

	@Override
	public List<Course> findByTitle(String title) {
		
		return getEm().createQuery(
				"select c from Course c where title like :title", Course.class).
				setParameter("title", "%"+title.trim()+"%").
				getResultList();
	}

	@Override
	public void insert(Course course) {
		getRepository().save(course);
	}

	@Override
	public void update(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	

}
