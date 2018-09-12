package com.cuong.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.cuong.importers.TextFileImporter;
import com.cuong.service.WordService;
import com.cuong.service.impl.WordServiceImpl;
import com.cuong.utils.Constant;
import com.cuong.utils.PathUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.Stage;

public class MainController extends BaseController implements Initializable {

	public static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

	private Stage primaryStage;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

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
	private StackPane centerPane;

	@FXML
	private MenuItem menuItemCard;

	@FXML
	private MenuItem menuItemTextFileImporter;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void onActionShowCard(ActionEvent event) {
		CardController cardController = new CardController();
		setMainPane("Card.fxml", cardController);
	}

	@FXML
	private void onActionImportTextFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(Constant.TITLE_CHOOSER_TEXT_FILE);
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
		List<File> files = fileChooser.showOpenMultipleDialog(primaryStage);
		if (files != null) {
			TextFileImporter importer = new TextFileImporter();
			for (File file : files) {
				importer.importData(file);
			}
		}
	}

}
