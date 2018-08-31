package com.cuong;

import java.util.logging.Logger;
import com.cuong.controllers.MainController;
import com.cuong.utils.PathUtils;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {

	public static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	public static final String APPLICATION_TITLE = "Card App | CuongGM | V1";

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(PathUtils.getViewPath("Main.fxml"));
		MainController mainController = new MainController();
		fxmlLoader.setController(mainController);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root, 400, 630);
		primaryStage.setScene(scene);
		primaryStage.setTitle(APPLICATION_TITLE);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
