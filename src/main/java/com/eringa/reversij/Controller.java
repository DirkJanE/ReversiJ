package com.eringa.reversij;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static com.eringa.reversij.GameLogic.*;
import static javafx.scene.shape.StrokeType.INSIDE;

public class Controller implements Initializable {

    @FXML public GridPane grid;

    @FXML public DialogPane dialog;

    @FXML public Label player1name, player2name;

    @FXML public Text score1, score2, playerturn, request;

    @FXML public TextField playername;

    @FXML public Circle sourcecircle;

    @FXML public Button resetbutton, dialogbutton;

    Board board = new Board("0xadd8e6ff", "0xff0000ff", "0x000000ff");

    Circle[] circles = new Circle[208];

    Player player1 = new Player(false, board.getHexcolorblack(), "Player 1", 0, "");
    Player player2 = new Player(false, board.getHexcolorblack(), "Player 2", 0, "");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getNames(dialog, request, playername, player1name, player2name, dialogbutton, player1, player2, playerturn);

        coinFlip(player1, player2, board);

        setupBoard(player1name, player2name);

        updateScore(circles, score1, score2, board, player1, player2);

        resetbutton.setOnMouseClicked(e -> handleResetButtonClick());
        resetbutton.setText("Start");
        }

    public void handleBoardClick(MouseEvent e) {

        sourcecircle = (Circle) e.getSource();

        checkLines(sourcecircle, circles, player1, player2, board);

        if (board.getChangeids().size() > 0) {
            updateBoard(circles, board, player1, player2);

            setPlayerTurn(player1, player2, playerturn);

            updateScore(circles, score1, score2, board, player1, player2);
        }
    }

    public void handleResetButtonClick() {
        if (resetbutton.getText().equals("Start")) {
            setupBoard(player1name, player2name);

        } else {
            setPlayerTurn(player1, player2, playerturn);
        }
    }

    public void setupBoard(Label player1name, Label player2name) {

        for (int count = 0; count < 208; count++) {
            Circle circle = new Circle();
            circle.setOnMouseClicked(this::handleBoardClick);
            circle.setId(String.valueOf(count));
            circle.setFill(Color.LIGHTBLUE);
            circle.setStroke(Color.BLACK);
            circle.setStrokeType(INSIDE);
            circle.setRadius(23);


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
        if (player1.getPlayermove()) {
            player1name.setText(player1.getPlayername());
            player2name.setText(player2.getPlayername());
        } else {
            player2name.setText(player1.getPlayername());
            player1name.setText(player2.getPlayername());
        }
        updateScore(circles, score1, score2, board, player1, player2);
    }
}