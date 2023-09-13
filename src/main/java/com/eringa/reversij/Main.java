package com.eringa.reversij;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        /*
        DialogPane dialog = new DialogPane();
        dialog.isVisible();
        Label label = new Label("This is a Popup");
        TextField input = new TextField();
        label.setStyle(" -fx-background-color: lightgreen;");
        dialog.setContent(label);
        */

    }

    public static void main(String[] args) {
        launch();
    }
}