package com.bruno.dao;

import java.util.List;

import javax.persistence.Query;
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

	@Override
	public void save(Job job) {
		persist(job);
	}
	
	@Override
	public Job update(Long id, Job job) {
		String hql = "UPDATE Job set " +
			"ADDRESS = :address ," +
			"CUSTOMER_NAME = :customerName ," +
			"EMAIL = :email ," +
			"PHONE = :phone ," +
			"SCHEDULED_DATE_TIME = :scheduledDateTime ," +
			"DESCRIPTION = :description " +
			"WHERE ID = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("address", job.getAddress());
		query.setParameter("customerName", job.getCustomerName());
		query.setParameter("email", job.getEmail());
		query.setParameter("phone", job.getPhone());
		query.setParameter("scheduledDateTime",	job.getDate());
		query.setParameter("description", job.getDescription());
		query.setParameter("id", id);
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		if (result == 0) {
			return null;
		}
		else {
			return job;
		}
	}

	@Override
	public void deleteById(Long id) {
		String hql = "DELETE FROM Job WHERE ID = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
	}
	
	public void persist(Object entity) {
		getCurrentSession().persist(entity);
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	private CriteriaBuilder getCriteriaBuilder() {
		return getCurrentSession().getCriteriaBuilder();
	}
}
