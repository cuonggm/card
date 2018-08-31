package com.cuong.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import com.cuong.models.Word;
import javafx.fxml.Initializable;

public class CardController implements Initializable {

	private static final Logger LOGGER = Logger.getLogger(CardController.class.getName());

	private List<Word> wordList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOGGER.info(CardController.class.getName() + " : initialize()");
		LOGGER.info("wordList.size() = " + wordList.size());
	}

	public List<Word> getWordList() {
		return wordList;
	}

	public void setWordList(List<Word> wordList) {
		this.wordList = wordList;
	}

}
