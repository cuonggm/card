package com.cuong;

import java.util.logging.Logger;

import com.cuong.models.Word;
import com.cuong.service.WordService;

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
		LOGGER.info("Create word service");
		WordService wordService = new WordService();
//		LOGGER.info("Create word instance");
//		Word w = new Word();
//		LOGGER.info("Set kanji for word");
//		w.setKanji("ABC");
//		LOGGER.info("word service save new instance");
//		wordService.saveOrUpdate(w);
//		LOGGER.info("END");

		Word word = wordService.findById(Long.valueOf(2));
		LOGGER.info(word.toString());
		launch(args);
	}

}
