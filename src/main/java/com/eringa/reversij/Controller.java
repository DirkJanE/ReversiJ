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
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.eringa.reversij.GameLogic.*;
import static javafx.scene.shape.StrokeType.INSIDE;

public class Controller implements Initializable {

    @FXML public GridPane grid;

    @FXML public DialogPane dialog;

    @FXML public Label leftname, rightname;

    @FXML public Text leftscore, rightscore, playerturn, request;

    @FXML public TextField playername;

    @FXML public Circle sourcecircle;

    @FXML public Button resetbutton, dialogbutton;

    Board board = new Board("0xadd8e6ff", "0xff0000ff", "0x000000ff");

    Circle[] circles = new Circle[208];

    Player player1 = new Player(false, board.getHexcolorblack(), "Player 1", 0, "");
    Player player2 = new Player(false, board.getHexcolorblack(), "Computer", 0, "");

    Boolean computerplayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //getNames(dialog, request, playername, player1name, player2name, dialogbutton, player1, player2, playerturn);

        dialog.setVisible(false);

        setupBoard();

        resetbutton.setOnMouseClicked(e -> handleResetButtonClick());

        computerplayer = player2.getPlayername().equals("Computer");
    }

    public void handleBoardClick(MouseEvent e) {

        String circleid;

        int maxloops = 1;
        int secondmaxloops = 1;

        ArrayList<Object> possiblecomputermoves = new ArrayList<>();

        if (computerplayer) {
            maxloops = 2;
        }

        for (int count = 0; count < maxloops; count++) {

            if (computerplayer && count == 1) {
                getPossibleComputerMoves(circles, board, possiblecomputermoves);
                secondmaxloops = possiblecomputermoves.size();
            }

            for (int i = 0; i < secondmaxloops; i++) {

                if (i == 0) {
                    sourcecircle = (Circle) e.getSource();
                     circleid = sourcecircle.getId();
                } else {
                    circleid = String.valueOf(possiblecomputermoves.get(i));
                }

                checkLines(circleid, circles, player1, player2, board);

                if (board.getChangeids().size() > 0) {
                    updateBoard(circles, board, player1, player2);

                    setPlayerTurn(false, player1, player2, playerturn);

                    updatePlayerScore(circles, board, player1, player2);

                    setBoardInfo(player1, player2, leftname, rightname, leftscore, rightscore);

                    if (i > 0) {
                        break;
                    }
                }
            }
        }
    }


    public void handleResetButtonClick() {
        setupBoard();
    }

    public void setupBoard() {

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

        coinFlip(player1, player2, board);
        setPlayerTurn(true, player1, player2, playerturn);
        updatePlayerScore(circles, board, player1, player2);
        setBoardInfo(player1, player2, leftname, rightname, leftscore, rightscore);
    }
}