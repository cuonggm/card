package com.cuong.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.cuong.utils.HibernateUtils;

public abstract class GenericDAO<PK extends Serializable, T> {

	private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	private Class<T> persistenceClass;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.persistenceClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	public T findById(Serializable id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		T entity = session.find(getPersistenceClass(), id);
		session.flush();
		transaction.commit();
		return entity;
	}

	public T save(T entity) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.save(entity);
		session.flush();
		transaction.commit();
		return entity;
	}

	public T update(T entity) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		session.flush();
		transaction.commit();
		return entity;
	}

	public T saveOrUpdate(T entity) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(entity);
		session.flush();
		transaction.commit();
		return entity;
	}

	public boolean delete(T entity) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(entity);
		session.flush();
		transaction.commit();
		return true;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Class<T> getPersistenceClass() {
		return persistenceClass;
	}

}
