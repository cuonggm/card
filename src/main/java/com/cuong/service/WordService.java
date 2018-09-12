package com.cuong.service;

import java.util.List;

import com.cuong.models.Word;

public interface WordService extends BaseService<Long, Word> {

	List<Word> loadRememberedWords();

	List<Word> loadLearningWords();

}
