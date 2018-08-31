package com.cuong.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainController implements Initializable {

	public static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

	@FXML
	private Label lblWelcome;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOGGER.info("Started Main Controller");
		lblWelcome.setText("Welcome to Card App");
	}

}
