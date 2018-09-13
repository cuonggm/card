package com.cuong.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cuong.dao.ListDAO;
import com.cuong.models.Word;

public class ListDAOImpl extends GenericDAO<Long, com.cuong.models.List> implements ListDAO {

	@Override
	public List<Word> getWords(Long id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		com.cuong.models.List list = session.find(com.cuong.models.List.class, id);
		List<Word> results = list.getWords();
		session.flush();
		transaction.commit();
		return results;
	}

	@Override
	public boolean deleteWords(Long id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		com.cuong.models.List list = session.find(com.cuong.models.List.class, id);
		if (list.getWords() != null) {
			for (Word word : list.getWords()) {
				session.delete(word);
			}
		}
		session.flush();
		transaction.commit();
		return true;
	}

}
