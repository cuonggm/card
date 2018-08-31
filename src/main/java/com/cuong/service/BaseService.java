package com.cuong.service;

import java.io.Serializable;
import com.cuong.models.TimeManageable;

public interface BaseService<PK extends Serializable, T extends TimeManageable> {

	T findById(Serializable id);

	T save(T entity);

	T update(T entity);

	T saveOrUpdate(T entity);

	boolean delete(T entity);

}
