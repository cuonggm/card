package com.cuong;

import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	public static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	public static final String APPLICATION_TITLE = "Card";

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle(APPLICATION_TITLE);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
