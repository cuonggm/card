package com.cuong.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import com.cuong.models.Word;
import com.cuong.service.WordService;
import com.cuong.service.impl.WordServiceImpl;
import com.cuong.utils.Constant;
import com.cuong.utils.ImageNames;
import com.cuong.utils.PathUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class CardController implements Initializable {

	private static final Logger LOGGER = Logger.getLogger(CardController.class.getName());

	private enum OrderMode {
		CREATED_TIME, RANDOM
	}

	private enum Mode {
		TESTING, LEARNING
	}

	private enum ListType {
		ALL, REMEMBERED, LEARNING
	}

	private WordService wordService;

	boolean isDetailShown;
	private List<Word> originalWords;
	private List<Word> displayWords;
	private int currentIndex;
	private Mode mode;
	private ListType listType;
	private OrderMode orderMode;

	public CardController() {
		wordService = new WordServiceImpl();
		this.isDetailShown = true;
		this.currentIndex = 0;
		this.mode = Mode.LEARNING;
		this.listType = ListType.LEARNING;
		this.orderMode = OrderMode.CREATED_TIME;
		loadWords();
	}

	private void initUI() {
		initImages();
		updateUI();
	}

	private void initImages() {
		ImageView imageView = (ImageView) backwardButton.getGraphic();
		imageView.setImage(new Image(PathUtils.getImagePath(ImageNames.BACKWARD).toString()));

		imageView = (ImageView) forwardButton.getGraphic();
		imageView.setImage(new Image(PathUtils.getImagePath(ImageNames.FORWARD).toString()));

		imageView = (ImageView) rememberButton.getGraphic();
		imageView.setImage(new Image(PathUtils.getImagePath(ImageNames.LEARNING).toString()));
	}

	private void setDetailInfoVisible(boolean isShown) {
		hiraganaLabel.setVisible(isShown);
		meaningLabel.setVisible(isShown);
		amHanVietLabel.setVisible(isShown);

		isDetailShown = isShown;
	}

	private void updateUI() {
		// list type button
		switch (listType) {
		case ALL:
			listTypeButton.setText(Constant.TITLE_LIST_TYPE_ALL);
			break;
		case LEARNING:
			listTypeButton.setText(Constant.TITLE_LIST_TYPE_LEARNING);
			break;
		case REMEMBERED:
			listTypeButton.setText(Constant.TITLE_LIST_TYPE_REMEMBERED);
			break;
		default:
			break;
		}

		// mode button
		switch (mode) {
		case LEARNING:
			modeButton.setText(Constant.TITLE_LEARNING_MODE);
			isDetailShown = true;
			break;
		case TESTING:
			modeButton.setText(Constant.TITLE_TESTING_MODE);
			isDetailShown = false;
			break;
		default:
			break;
		}

		// order mode button
		switch (orderMode) {
		case CREATED_TIME:
			randomButton.setText(Constant.TITLE_ORDER_MODE_CREATED_TIME);
			break;
		case RANDOM:
			randomButton.setText(Constant.TITLE_ORDER_MODE_RANDOM);
			break;
		default:
			break;
		}

		// is Detail visible
		setDetailInfoVisible(isDetailShown);

		// related words
		if (displayWords == null || displayWords.size() <= 0) {
			indexField.setText("0" + "/" + "0");
			kanjiLabel.setText("");
			hiraganaLabel.setText("");
			meaningLabel.setText("");
			amHanVietLabel.setText("");
			ImageView imageView = (ImageView) rememberButton.getGraphic();
			imageView.setImage(new Image(PathUtils.getImagePath(ImageNames.LEARNING).toString()));
		} else {
			Word currentWord = displayWords.get(currentIndex);
			// index label
			indexField.setText((currentIndex + 1) + "/" + displayWords.size());
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
				imageView.setImage(new Image(PathUtils.getImagePath(ImageNames.CHECKED).toString()));
			} else {
				ImageView imageView = (ImageView) rememberButton.getGraphic();
				imageView.setImage(new Image(PathUtils.getImagePath(ImageNames.LEARNING).toString()));
			}
		}
	}

	private void nextWord() {
		if (displayWords == null || displayWords.size() <= 0) {
			return;
		}
		if (currentIndex + 1 >= displayWords.size()) {
			currentIndex = 0;
		} else {
			currentIndex++;
		}
		switch (mode) {
		case TESTING:
			isDetailShown = false;
			break;
		case LEARNING:
			isDetailShown = true;
			break;
		default:
			break;
		}
		updateUI();
	}

	private void previousWord() {
		if (displayWords == null || displayWords.size() <= 0) {
			return;
		}
		if (currentIndex - 1 < 0) {
			currentIndex = displayWords.size() - 1;
		} else {
			currentIndex--;
		}
		switch (mode) {
		case TESTING:
			isDetailShown = false;
			break;
		case LEARNING:
			isDetailShown = true;
			break;
		default:
			break;
		}
		updateUI();
	}

	private void changeRememberState() {
		if (displayWords == null || displayWords.size() <= 0) {
			return;
		}
		Word currentWord = displayWords.get(currentIndex);
		currentWord.setRemembered(!currentWord.isRemembered());
		wordService.update(currentWord);
		// remove if not in mode display all
		if (listType != ListType.ALL) {
			displayWords.remove(currentIndex);
			if ((currentIndex > displayWords.size() - 1) && (currentIndex > 0)) {
				currentIndex--;
			}
		}
		updateUI();
	}

	private void changeListType() {
		switch (listType) {
		case ALL:
			LOGGER.info("listType changed from ALL to LEARNING");
			listType = ListType.LEARNING;
			loadWords();
			updateUI();
			break;
		case LEARNING:
			LOGGER.info("listType changed from LEARNING to REMEMBERED");
			listType = ListType.REMEMBERED;
			loadWords();
			updateUI();
			break;
		case REMEMBERED:
			LOGGER.info("listType changed from REMEMBERED to ALL");
			listType = ListType.ALL;
			loadWords();
			updateUI();
			break;
		default:
			break;
		}
	}

	private void changeMode() {
		switch (mode) {
		case LEARNING:
			mode = Mode.TESTING;
			updateUI();
			break;
		case TESTING:
			mode = Mode.LEARNING;
			updateUI();
			break;
		default:
			break;
		}
	}

	private void random() {
		Collections.shuffle(displayWords);
		currentIndex = 0;
	}

	private void loadWords() {
		switch (listType) {
		case ALL:
			originalWords = wordService.loadAll();
			break;
		case LEARNING:
			originalWords = wordService.loadLearningWords();
			break;
		case REMEMBERED:
			originalWords = wordService.loadRememberedWords();
			break;
		default:
			break;
		}
		displayWords = new ArrayList<>(originalWords);
		if (orderMode == OrderMode.RANDOM) {
			random();
		}
		currentIndex = 0;
	}

	private void changeOrderMode() {
		switch (orderMode) {
		case CREATED_TIME:
			orderMode = OrderMode.RANDOM;
			random();
			updateUI();
			break;
		case RANDOM:
			orderMode = OrderMode.CREATED_TIME;
			loadWords();
			updateUI();
			break;
		default:
			break;
		}
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
	private TextField indexField;

	@FXML
	private Button modeButton;

	@FXML
	private Button randomButton;

	@FXML
	private Button listTypeButton;

	@FXML
	private BorderPane mainPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initUI();
	}

	@FXML
	private void onMouseClickedShowHideDetailInfo(MouseEvent event) {
		setDetailInfoVisible(!isDetailShown);
	}

	@FXML
	private void onActionSetRemember(ActionEvent event) {
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
	void onActionChangeListType(ActionEvent e) {
		changeListType();
	}

	@FXML
	void onActionChangeMode(ActionEvent e) {
		changeMode();
	}

	@FXML
	void onActionRandom(ActionEvent e) {
		changeOrderMode();
	}

	@FXML
	private void onKeyPressed(KeyEvent event) {
		LOGGER.info("PRESSED KEY: " + event.getCode());
		switch (event.getCode()) {
		case SPACE:
			setDetailInfoVisible(!isDetailShown);
			break;
		case LEFT:
			previousWord();
			break;
		case RIGHT:
			nextWord();
			break;
		case DOWN:
			changeRememberState();
			break;
		case UP:
			changeListType();
			break;
		case ENTER:
			if (mainPane.isFocused()) {
				indexField.requestFocus();
			} else {
				mainPane.requestFocus();
				try {

					int index = Integer.parseInt(indexField.getText()) - 1;
					if (index >= 0 && index <= displayWords.size() - 1) {
						currentIndex = index;
					}
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
				}
				updateUI();
			}
			break;
		case M:
			changeMode();
			break;
		case R:
			changeOrderMode();
			break;
		default:
			break;
		}
	}

}
