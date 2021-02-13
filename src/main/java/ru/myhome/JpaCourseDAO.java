package ru.myhome;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Lazy
@Transactional
public class JpaCourseDAO implements CourseDAO{

	private static final Log LOG = LogFactory.getLog(JpaCourseDAO.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	public EntityManager getSessionFactory() {
		return em;
	}

	public void setSessionFactory(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional(readOnly = true)
	public Course findById(int id) {
		return em.find(Course.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findAll() {
		return em.createQuery("select c from Course c", Course.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findByTitle(String title) {
		System.out.println("Seaching with title \""+title+"\"...");
		return em.createQuery("select c from Course c where c.title like :title", Course.class).
		setParameter("title", "%"+title.trim()+"%").getResultList();
	}

	@Override
	public void insert(Course course) {
		em.persist(course);
		LOG.info("Course has been saved with id: " + course.getId());
	}

	@Override
	public void update(Course course) {
		em.merge(course);
		LOG.info("Course with id: " + course.getId() +"has been updated");
	}

	@Override
	public void delete(int id) {
		Course temp;
		if((temp=findById(id)) != null) em.remove(temp);
		LOG.info("Course with id: " + temp.getId() +" has been deleted with id: " + temp.getId());
	}
}
