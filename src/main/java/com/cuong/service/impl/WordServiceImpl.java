package com.cuong.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.cuong.dao.impl.WordDAOImpl;
import com.cuong.models.Word;
import com.cuong.service.WordService;

public class WordServiceImpl implements WordService {

	private static final Logger LOGGER = Logger.getLogger(WordServiceImpl.class.getName());

	private WordDAOImpl wordDAO = new WordDAOImpl();

	@Override
	public List<Word> loadAll() {
		try {
			return wordDAO.loadAll();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return Collections.emptyList();
		}
	}

	@Override
	public Word findById(Serializable id) {
		try {
			return wordDAO.findById(id);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Word save(Word entity) {
		try {
			Date currentDate = new Date();
			entity.setCreatedAt(currentDate);
			entity.setUpdatedAt(currentDate);
			return wordDAO.save(entity);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Word update(Word entity) {
		try {
			entity.setUpdatedAt(new Date());
			return wordDAO.update(entity);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Word saveOrUpdate(Word entity) {
		try {
			return wordDAO.saveOrUpdate(entity);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
	}

	@Override
	public boolean delete(Word entity) {
		try {
			return wordDAO.delete(entity);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
	}

}
