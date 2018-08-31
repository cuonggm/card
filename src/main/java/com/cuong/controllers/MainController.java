package com.cuong.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import com.cuong.service.WordService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;

public class MainController extends BaseController implements Initializable {

	public static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

	private WordService wordService = new WordService();

	@FXML
	private MenuItem menuItemCard;

	@FXML
	private Label labelContent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@FXML
	private void onActionShowCard(ActionEvent event) {
		CardController cardController = new CardController();
		cardController.setWordList(wordService.loadAll());
		showModal("Card.fxml", cardController, Modality.NONE, "Card");
	}

}
