package com.eringa.reversij;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image image = new Image(Main.class.getResource("BarIcon.jpg").toURI().toString());

        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}