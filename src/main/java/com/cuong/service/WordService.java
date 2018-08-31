package com.cuong.service;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cuong.dao.WordDAO;
import com.cuong.models.Word;

public class WordService implements BaseService<Long, Word> {

	private static final Logger LOGGER = Logger.getLogger(WordService.class.getName());

	private WordDAO wordDAO = new WordDAO();

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
