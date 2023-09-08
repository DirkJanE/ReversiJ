package com.eringa.reversij;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.eringa.reversij.GameLogic.*;
import static javafx.scene.shape.StrokeType.INSIDE;

public class Controller implements Initializable {

    @FXML public GridPane grid;
    @FXML public Text text1, text2, playerturn;
    @FXML public Circle sourcecircle;

    @FXML public Button button;

    ArrayList<Integer> changeids = new ArrayList<>();

    Board board = new Board("0xadd8e6ff", "0xff0000ff", "0x000000ff");

    Circle[] circles = new Circle[208];

    Player player1 = new Player(false, board.getHexcolorblack());
    Player player2 = new Player(false, board.getHexcolorblack());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        coinFlip(player1, player2, board, playerturn);
        setupBoard();
        updateScore(circles, text1, text2, board.getHexcolorred(), board.getHexcolorblack());
        button.setOnMouseClicked(e -> handleResetButtonClick());
        }

    public void setupBoard() {
        for (int count = 0; count < 208; count++) {
            Circle circle = new Circle();
            circle.setId(String.valueOf(count));
            circle.setFill(Color.LIGHTBLUE);
            circle.setStroke(Color.BLACK);
            circle.setStrokeType(INSIDE);
            circle.setRadius(23);
            circle.setOnMouseClicked(e -> handleBoardClick(e));

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
        updateScore(circles, text1, text2, board.hexcolorred, board.getHexcolorblack());
    }

    public void handleBoardClick(MouseEvent e) {

        sourcecircle = (Circle) e.getSource();
        String circleid = sourcecircle.getId();

        if (player1.getPlayermove() == true) {

            checkLines(circleid, circles, changeids, player1.getPlayercolor(), player2.getPlayercolor(), board);
            if (changeids.size() > 0) {
                updateBoard(circles, changeids, player1.getPlayercolor());
                playerturn.setText("It's your turn, player 2!");
                player1.setPlayermove(false);
            }

        } else {

            checkLines(circleid, circles, changeids, player2.getPlayercolor(), player1.getPlayercolor(), board);
            if (changeids.size() > 0) {
                updateBoard(circles, changeids, player2.getPlayercolor());
                playerturn.setText("It's your turn, player 1!");
                player1.setPlayermove(true);
            }
        }

        changeids.clear();

        updateScore(circles, text1, text2, board.hexcolorred, board.getHexcolorblack());
    }

    public void handleResetButtonClick() {
        setupBoard();
    }
}