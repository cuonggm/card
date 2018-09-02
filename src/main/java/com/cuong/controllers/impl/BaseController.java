package com.cuong.controllers.impl;

import java.io.IOException;
import java.util.logging.Logger;

import com.cuong.utils.PathUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BaseController {

	private static final Logger LOGGER = Logger.getLogger(BaseController.class.getName());

	protected void showModal(String viewFileName, Object controller, Modality modality, String title) {
		try {
			Stage stage = new Stage();
			stage.initModality(modality);
			FXMLLoader fxmlLoader = new FXMLLoader(PathUtils.getViewPath(viewFileName));
			fxmlLoader.setController(controller);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle(title);
			stage.showAndWait();
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}
	}

}
