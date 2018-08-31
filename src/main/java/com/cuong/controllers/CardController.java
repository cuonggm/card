package com.cuong.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import com.cuong.models.Word;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CardController implements Initializable {

	private static final Logger LOGGER = Logger.getLogger(CardController.class.getName());

	@FXML
	private Label hiraganaLabel;

	@FXML
	private Label meaningLabel;

	@FXML
	private Label amHanVietLabel;

	private List<Word> wordList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOGGER.info(CardController.class.getName() + " : initialize()");
		LOGGER.info("wordList.size() = " + wordList.size());
	}

	@FXML
	private void onMouseClickedShowHideDetailInfo(MouseEvent event) {
		LOGGER.info("onActionShowHideDetailInfo() is called");
		setDetailInfoVisible(!isDetailShown);
	}

	@FXML
	private void onKeyPressed(KeyEvent event) {
		LOGGER.info("PRESSED KEY: " + event.getCode());
		if (event.getCode() == KeyCode.SPACE) {
			setDetailInfoVisible(!isDetailShown);
		}
	}

	private void setDetailInfoVisible(boolean isShown) {
		hiraganaLabel.setVisible(isShown);
		meaningLabel.setVisible(isShown);
		amHanVietLabel.setVisible(isShown);

		isDetailShown = isShown;
	}

	boolean isDetailShown = true;

	public List<Word> getWordList() {
		return wordList;
	}

	public void setWordList(List<Word> wordList) {
		this.wordList = wordList;
	}

}
