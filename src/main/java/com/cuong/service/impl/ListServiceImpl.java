package com.cuong.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import com.cuong.dao.ListDAO;
import com.cuong.dao.impl.ListDAOImpl;
import com.cuong.service.ListService;

public class ListServiceImpl implements ListService {

	private static final Logger LOGGER = Logger.getLogger(ListServiceImpl.class.getName());

	private ListDAO listDAO;

	public ListServiceImpl() {
		listDAO = new ListDAOImpl();
	}

	@Override
	public List<com.cuong.models.List> loadAll() {
		try {
			return listDAO.loadAll();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	@Override
	public com.cuong.models.List findById(Serializable id) {
		try {
			return listDAO.findById(id);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	@Override
	public com.cuong.models.List save(com.cuong.models.List entity) {
		try {
			return listDAO.save(entity);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	@Override
	public com.cuong.models.List update(com.cuong.models.List entity) {
		try {
			return listDAO.update(entity);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	@Override
	public com.cuong.models.List saveOrUpdate(com.cuong.models.List entity) {
		try {
			return listDAO.saveOrUpdate(entity);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean delete(com.cuong.models.List entity) {
		try {
			return listDAO.delete(entity);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return false;
		}
	}

}
