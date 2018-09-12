package com.cuong.dao;

import java.util.List;
import com.cuong.models.Word;

public interface WordDAO extends BaseDAO<Long, Word> {

	List<Word> loadRememberedWords();

	List<Word> loadLearningWords();

}
