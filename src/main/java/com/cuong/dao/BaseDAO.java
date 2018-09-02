package com.cuong.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<PK extends Serializable, T> {

	List<T> loadAll();

	T findById(Serializable id);

	T save(T entity);

	T update(T entity);

	T saveOrUpdate(T entity);

	boolean delete(T entity);

}
