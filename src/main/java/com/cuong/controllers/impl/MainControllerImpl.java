package com.cuong.controllers.impl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import com.cuong.service.WordService;
import com.cuong.service.impl.WordServiceImpl;
import com.cuong.utils.PathUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

public class MainControllerImpl extends BaseController implements Initializable {

	public static final Logger LOGGER = Logger.getLogger(MainControllerImpl.class.getName());

	public void setMainPane(String viewFileName, Object controller) {
		try {
			centerPane.getChildren().clear();
			FXMLLoader fxmlLoader = new FXMLLoader(PathUtils.getViewPath(viewFileName));
			fxmlLoader.setController(controller);
			Parent root = fxmlLoader.load();
			centerPane.getChildren().add(root);
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}
	}

	@FXML
	private MenuItem menuItemCard;

	@FXML
	private StackPane centerPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void onActionShowCard(ActionEvent event) {
		CardControllerImpl cardController = new CardControllerImpl();
		WordService wordService = new WordServiceImpl();
		cardController.setWordService(wordService);
		setMainPane("Card.fxml", cardController);
	}

}
