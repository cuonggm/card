package com.cuong.dao;

import com.cuong.models.List;
import com.cuong.models.Word;

public interface ListDAO extends BaseDAO<Long, List> {

	java.util.List<Word> getWords(Long id);

	boolean deleteWords(Long id);

}
