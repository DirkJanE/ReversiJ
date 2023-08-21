package com.eringa.reversij;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.eringa.reversij.GameLogic.*;
import static javafx.scene.shape.StrokeType.INSIDE;

public class Controller implements Initializable {

    @FXML public GridPane grid;
    @FXML public Text text1, text2, playerturn;
    @FXML public Circle sourcecircle;

    private String score;

    String hexcolorred = "0xff0000ff";
    String hexcolorblack = "0x000000ff";

    Circle[] circles = new Circle[208];
    ArrayList<Integer> changeids = new ArrayList<>();

    Boolean player1move = true;
    String playercolor;
    String opponentcolor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (int count = 0; count < 208; count++) {
            Circle circle = new Circle();
            circle.setId("circle" + count);
            circle.setFill(Color.LIGHTBLUE);
            circle.setStroke(Color.BLACK);
            circle.setStrokeType(INSIDE);
            circle.setRadius(23);
            circle.setOnMouseClicked(e -> handleMouseClick(e));

            if (count == 100 || count == 107) {
                circle.setFill(Color.RED);
            } else if (count == 108 || count == 99) {
                circle.setFill(Color.BLACK);
            }
            circles[count] = circle;
        }

        int cell = 72;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                grid.add(circles[cell], column, row);
                cell++;
            }
        }

    playerturn.setText("It's your turn, player 1!");
    updateScore(circles, text1, text2, hexcolorred, hexcolorblack);

    }

    public void handleMouseClick(MouseEvent e) {

        sourcecircle = (Circle) e.getSource();
        String circleid = sourcecircle.getId();

        if (player1move == true) {

            playercolor = hexcolorred;
            opponentcolor = hexcolorblack;
            checkLine(circleid, circles, changeids, playercolor, opponentcolor);
            if (changeids.size() > 0) {
                updateBoard(circles, changeids, playercolor);
                playerturn.setText("It's your turn, player 2!");
                player1move = false;
            }

        } else {

            playercolor = hexcolorblack;
            opponentcolor = hexcolorred;
            checkLine(circleid, circles, changeids, playercolor, opponentcolor);
            if (changeids.size() > 0) {
                updateBoard(circles, changeids, playercolor);
                playerturn.setText("It's your turn, player 1!");
                player1move = true;
            }
        }

        /*
        for (int i = 0; i < changeids.size(); i++) {
            System.out.println(changeids.get(i));
        }
        */
        changeids.clear();

        updateScore(circles, text1, text2, hexcolorred, hexcolorblack);
    }
}