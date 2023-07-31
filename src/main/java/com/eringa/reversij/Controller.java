package com.eringa.reversij;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static com.eringa.reversij.GameLogic.checkHorizontalLine;
import static javafx.scene.shape.StrokeType.INSIDE;

public class Controller implements Initializable {

    @FXML public GridPane grid;
    @FXML public Text text, text1;
    @FXML public Circle sourcecircle;

    private String score;

    Circle[] circles = new Circle[64];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        score = "1";
        text.setText(score);

        for (int count = 0; count < 64; count++) {
            Circle circle = new Circle();
            circle.setId("circle" + count);
            circle.setFill(Color.LIGHTBLUE);
            circle.setStroke(Color.BLACK);
            circle.setStrokeType(INSIDE);
            circle.setRadius(23);
            circle.setOnMouseClicked(e -> handleMouseClick(e));

            if (count == 28 || count == 35) {
                circle.setFill(Color.RED);
            } else if (count == 36 || count == 27) {
                circle.setFill(Color.BLACK);
            }
            circles[count] = circle;
        }

        int cell  = 0;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                    grid.add(circles[cell], column, row);
                    cell++;
                }
        }
    }

    public void setTextArea() {
        System.out.println("Text Clicked!");
        text.setText("2");
        text1.setText("2");
    }

    public void handleMouseClick(MouseEvent e) {
        System.out.println("Circle clicked!");
        sourcecircle = (Circle) e.getSource();
        String circleid = sourcecircle.getId();
        checkHorizontalLine(circleid, circles);
    }
}