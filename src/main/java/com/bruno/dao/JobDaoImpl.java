package com.bruno.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruno.models.Job;

	

@Repository
public class JobDaoImpl implements JobDao {

	@Autowired
	SessionFactory sessionFactory;
		
	@Override
	public void save(Job job) {
		persist(job);
	}
	
	@Override
	public Job findById(Long id) {
		CriteriaQuery<Job> criteria = getCriteriaBuilder().createQuery(Job.class);
		Root<Job> root = criteria.from(Job.class);
		criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id));
		return getCurrentSession().createQuery(criteria).uniqueResult();
	}

	@Override
	public List<Job> findAllJobs() {
		CriteriaQuery<Job> criteria = getCriteriaBuilder().createQuery(Job.class);
		Root<Job> root = criteria.from(Job.class);
		criteria.select(root);
		return getCurrentSession().createQuery(criteria).getResultList();
	}

	public void persist(Object entity) {
		getCurrentSession().persist(entity);
	}
	
	public void delete(Object entity) {
		getCurrentSession().delete(entity);
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	private CriteriaBuilder getCriteriaBuilder() {
		return getCurrentSession().getCriteriaBuilder();
	}
}
