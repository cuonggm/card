package com.cuong.dao.impl;

import java.util.List;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.cuong.dao.WordDAO;
import com.cuong.models.Word;

public class WordDAOImpl extends GenericDAO<Long, Word> implements WordDAO {

	private static final Logger LOGGER = Logger.getLogger(WordDAOImpl.class.getName());

	@Override
	public List<Word> loadRememberedWords() {
		LOGGER.info("loadRememberedWords()");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Query<Word> query = session.createQuery("from Word where remembered = :remembered", Word.class);
		query.setParameter("remembered", true);
		List<Word> results = query.getResultList();
		session.flush();
		transaction.commit();
		return results;
	}

	@Override
	public List<Word> loadLearningWords() {
		LOGGER.info("loadLearningWords()");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Query<Word> query = session.createQuery("from Word where remembered = :remembered", Word.class);
		query.setParameter("remembered", false);
		List<Word> results = query.getResultList();
		session.flush();
		transaction.commit();
		return results;
	}

}
