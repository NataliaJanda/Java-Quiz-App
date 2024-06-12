package com.example.javaquizzapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaQuizzAppApplication extends Application {

	public static ConfigurableApplicationContext springContex; //doczytać co to jest <-- dodawanie modulow do apki
	private FXMLLoader fxmlLoader;

	public static ConfigurableApplicationContext getSpringContext() {
		return springContex;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		fxmlLoader.setLocation(getClass().getResource("/gui.fxml"));

		Parent root = fxmlLoader.load();

		stage.setTitle("Quizz");
		Scene scene = new Scene(root, 800,600);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void init() throws Exception {
		springContex = SpringApplication.run(JavaQuizzAppApplication.class); //adres springboot app
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(springContex::getBean); //odpowiedzialne za wczytywanie widokow z plikow xml
	}

	@Override
	public void stop() throws Exception {
		springContex.stop();
	}
}
