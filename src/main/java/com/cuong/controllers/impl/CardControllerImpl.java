package com.cuong.controllers.impl;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import com.cuong.models.Word;
import com.cuong.service.WordService;
import com.cuong.utils.Constant;
import com.cuong.utils.Images;
import com.cuong.utils.PathUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CardControllerImpl implements Initializable {

	private static final Logger LOGGER = Logger.getLogger(CardControllerImpl.class.getName());

	private enum ViewMode {
		ALL, REMEMBERED, LEARNING
	}

	private WordService wordService;

	boolean isDetailShown;
	private List<Word> words;
	private int currentIndex;

	public CardControllerImpl() {
		isDetailShown = true;
		currentIndex = 0;
	}

	private void initUI() {
		initImages();
		updateUI();
	}

	private void initImages() {
		ImageView imageView = (ImageView) backwardButton.getGraphic();
		imageView.setImage(new Image(PathUtils.getImagePath(Images.BACKWARD).toString()));

		imageView = (ImageView) forwardButton.getGraphic();
		imageView.setImage(new Image(PathUtils.getImagePath(Images.FORWARD).toString()));

		imageView = (ImageView) rememberButton.getGraphic();
		imageView.setImage(new Image(PathUtils.getImagePath(Images.LEARNING).toString()));
	}

	private void setDetailInfoVisible(boolean isShown) {
		hiraganaLabel.setVisible(isShown);
		meaningLabel.setVisible(isShown);
		amHanVietLabel.setVisible(isShown);

		isDetailShown = isShown;
	}

	private void updateUI() {
		if (words == null || words.size() <= 0) {
			return;
		}
		Word currentWord = words.get(currentIndex);
		// index label
		indexLabel.setText(currentIndex + "/" + words.size());
		// kanji
		if (currentWord.getKanji() != null) {
			kanjiLabel.setText(currentWord.getKanji());
		} else {
			kanjiLabel.setText(Constant.CONTENT_EMPTY);
		}
		// hiragana
		if (currentWord.getHiragara() != null) {
			hiraganaLabel.setText(currentWord.getHiragara());
		} else {
			hiraganaLabel.setText(Constant.CONTENT_EMPTY);
		}
		// meaning
		if (currentWord.getMeaning() != null) {
			meaningLabel.setText(currentWord.getMeaning());
		} else {
			meaningLabel.setText(Constant.CONTENT_EMPTY);
		}
		// am han viet
		if (currentWord.getAmHanViet() != null) {
			amHanVietLabel.setText(currentWord.getAmHanViet());
		} else {
			amHanVietLabel.setText(Constant.CONTENT_EMPTY);
		}
		// remembered
		if (currentWord.isRemembered()) {
			ImageView imageView = (ImageView) rememberButton.getGraphic();
			imageView.setImage(new Image(PathUtils.getImagePath(Images.CHECKED).toString()));
		} else {
			ImageView imageView = (ImageView) rememberButton.getGraphic();
			imageView.setImage(new Image(PathUtils.getImagePath(Images.LEARNING).toString()));
		}
	}

	private void nextWord() {
		if (words == null || words.size() <= 0) {
			return;
		}
		if (currentIndex + 1 >= words.size()) {
			currentIndex = 0;
		} else {
			currentIndex++;
		}
		updateUI();
	}

	private void previousWord() {
		if (words == null || words.size() <= 0) {
			return;
		}
		if (currentIndex - 1 < 0) {
			currentIndex = words.size() - 1;
		} else {
			currentIndex--;
		}
		updateUI();
	}

	private void changeRememberState() {
		if (words == null || words.size() <= 0) {
			return;
		}
		Word currentWord = words.get(currentIndex);
		currentWord.setRemembered(!currentWord.isRemembered());
		wordService.update(currentWord);
		updateUI();
	}

	public WordService getWordService() {
		return wordService;
	}

	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}

	@FXML
	private Label kanjiLabel;

	@FXML
	private Label hiraganaLabel;

	@FXML
	private Label meaningLabel;

	@FXML
	private Label amHanVietLabel;

	@FXML
	private Button rememberButton;

	@FXML
	private Button backwardButton;

	@FXML
	private Button forwardButton;

	@FXML
	private Label indexLabel;

	@FXML
	private Button modeButton;

	@FXML
	private Button randomButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		words = wordService.loadAll();
		initUI();
	}

	@FXML
	private void onMouseClickedShowHideDetailInfo(MouseEvent event) {
		setDetailInfoVisible(!isDetailShown);
	}

	@FXML
	private void onActionSetRemember() {
		changeRememberState();
	}

	@FXML
	private void onActionNextWord(ActionEvent e) {
		nextWord();
	}

	@FXML
	private void onActionPreviousWord(ActionEvent e) {
		previousWord();
	}

	@FXML
	private void onKeyPressed(KeyEvent event) {
		LOGGER.info("PRESSED KEY: " + event.getCode());
		if (event.getCode() == KeyCode.SPACE) {
			setDetailInfoVisible(!isDetailShown);
		}
	}

}
