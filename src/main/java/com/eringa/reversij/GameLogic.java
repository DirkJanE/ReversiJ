package com.eringa.reversij;

import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    public static void getNames(DialogPane dialog, Text request, TextField playername, Label player1name, Label player2name, Button dialogbutton, Player player1, Player player2, Text playerturn) {
        dialog.setVisible(true);
        request.setText("Please enter your name, player 1:");
        player1name.setText("Score " + player1.getPlayername() + ":");
        player2name.setText("Score " + player2.getPlayername() + ":");
        dialogbutton.setOnMouseClicked(e -> handleDialogClick(playername, player1, player2, request, dialog));
    }

    public static void handleDialogClick(TextField playername, Player player1, Player player2, Text request, DialogPane dialog) {
        if (playername.getCharacters().toString().length() > 0) {
            if (player1.getPlayername().equals("Player 1")) {
                player1.setPlayername(playername.getCharacters().toString());
                playername.setText("");
                request.setText("Please enter your name, player 2:");
            } else if (player2.getPlayername().equals("Player 2")) {
                player2.setPlayername(playername.getCharacters().toString());
                playername.setText("");
                dialog.setVisible(false);
            }
        }
    }

    public static void setPlayerTurn(Player player1, Player player2, Text playerturn) {
            if (player1.getPlayermove()) {
                playerturn.setText("It's your turn, " + player1.getPlayername() + "!");
                player1.setPlayermove(false);
            } else {
                playerturn.setText("It's your turn, " + player2.getPlayername() + "!");
                player1.setPlayermove(true);
            }
        }

    public static void coinFlip(Player player1, Player player2, Board board) {

        int num = (int)(Math.random() * 2) + 1;
        if (num == 1) {
            player1.setPlayermove(true);
            player1.setPlayercolor(board.getHexcolorred());
            player1.setPlayerposition("left");
        } else {
            player2.setPlayermove(true);
            player2.setPlayercolor(board.getHexcolorred());
            player2.setPlayerposition("left");
        }
    }

    public static void checkLines(Circle sourcecircle, Circle[] circles, Player player1, Player player2, Board board) {

        String circleid = sourcecircle.getId();

        String playercolor, opponentcolor;


        if (player1.getPlayermove()) {
            playercolor = player1.getPlayercolor();
            opponentcolor = player2.getPlayercolor();

        } else {
            playercolor = player2.getPlayercolor();
            opponentcolor = player1.getPlayercolor();
        }

        Integer id = Integer.valueOf(circleid);
        ArrayList<Integer> tempids = new ArrayList<>();

        List<Object> limitandoffsets = new ArrayList<>();
        List<Integer> offsets;

        int offset;
        String limit;
        int direction;
        boolean border = false;

        limitandoffsets = getOffsets(id, board, limitandoffsets);

        limit = (String) limitandoffsets.get(0);
        offsets = (List<Integer>) limitandoffsets.get(1);

        for (Integer integer : offsets) {

            if (board.getBoardlimits().contains(id)) {
                border = true;
            }

            if (circles[id].getFill().toString().equals(board.getHexcolorblue())) {
                tempids.add(id);

                for (int j = 1; j < 8; j++) {

                    offset = id + (integer * j);
                    direction = integer;

                    if (j == 1) {
                        if (circles[offset].getFill().toString().equals(opponentcolor)) {

                            if (!board.getBoardlimits().contains(offset)) {
                                tempids.add(offset);
                                border = false;
                            } else {
                                if (board.getBoardlimits().contains(offset) && border) {
                                    if (limit.equals("topleft") && (direction == 1 || direction == 8)) {
                                        tempids.add(offset);
                                    } else if (limit.equals("topright") && (direction == -1 || direction == 8)) {
                                        tempids.add(offset);
                                    } else if (limit.equals("bottomleft") && (direction == 1 || direction == -8)) {
                                        tempids.add(offset);
                                    } else if (limit.equals("bottomright") && (direction == -1 || direction == -8)) {
                                        tempids.add(offset);
                                    } else if (limit.equals("top") && offset > 72 && offset < 79) {
                                        tempids.add(offset);
                                    } else if (limit.equals("left") && offset == 80 || offset == 88 || offset == 96 || offset == 104 || offset == 112 || offset == 120) {
                                        tempids.add(offset);
                                    } else if (limit.equals("right") && offset == 87 || offset == 95 || offset == 103 || offset == 111 || offset == 119 || offset == 127) {
                                        tempids.add(offset);
                                    } else if (limit.equals("bottom") && offset > 128 && offset < 135) {
                                        tempids.add(offset);
                                    } else {
                                        border = false;
                                        tempids.clear();
                                        break;
                                    }
                                }
                            }
                        } else {
                            border = false;
                            tempids.clear();
                            break;
                        }

                    } else {

                        if (circles[offset].getFill().toString().equals(opponentcolor)) {
                            if (!border) {
                                if (board.getBoardlimits().contains(offset)) {
                                    border = false;
                                    tempids.clear();
                                    break;
                                } else {
                                    tempids.add(offset);
                                }
                            } else {
                                if (limit.equals("topleft") && (direction == 1 || direction == 8)) {
                                    tempids.add(offset);
                                } else if (limit.equals("topright") && (direction == -1 || direction == 8)) {
                                    tempids.add(offset);
                                } else if (limit.equals("bottomleft") && (direction == -8 || direction == 1)) {
                                    tempids.add(offset);
                                } else if (limit.equals("bottomright") && (direction == -8 || direction == -1)) {
                                    tempids.add(offset);
                                } else if (limit.equals("top") && (direction == -1 || direction == 1)) {
                                    if (offset != 72 && offset != 79) {
                                        tempids.add(offset);
                                    } else {
                                        border = false;
                                        tempids.clear();
                                        break;
                                    }
                                } else if (limit.equals("left") && (direction == -8 || direction == 8)) {
                                    if (offset != 72 && offset != 128) {
                                        tempids.add(offset);
                                    } else {
                                        border = false;
                                        tempids.clear();
                                        break;
                                    }
                                } else if (limit.equals("right") && (direction == -8 || direction == 8)) {
                                    if (offset != 79 && offset != 135) {
                                        tempids.add(offset);
                                    } else {
                                        border = false;
                                        tempids.clear();
                                        break;
                                    }
                                } else if (limit.equals("bottom") && (direction == -1 || direction == 1)) {
                                    if (offset != 128 && offset != 135) {
                                        tempids.add(offset);
                                    } else {
                                        border = false;
                                        tempids.clear();
                                        break;
                                    }
                                } else {
                                    border = false;
                                    tempids.clear();
                                    break;
                                }
                            }


                        } else if (circles[offset].getFill().toString().equals(board.getHexcolorblue())) {
                            border = false;
                            tempids.clear();
                            break;

                        } else if (circles[offset].getFill().toString().equals(playercolor)) {

                            for (Integer tempid : tempids) {
                                board.getChangeids().add(tempid);
                            }
                            border = false;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static List<Object> getOffsets(Integer id, Board board, List<Object> limitandoffsets) {

        String limit;
        List<Integer> offsets;

        if (board.getBoardlimits().contains(id)) {
            if (board.getTopleftlimit().contains(id)) {
                limit = "topleft";
                offsets = (List.of(1, 8, 9));
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (board.getToprightlimit().contains(id)) {
                limit = "topright";
                offsets = List.of(-1, 8, 7);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (board.getBottomleftlimit().contains(id)) {
                limit = "bottomleft";
                offsets = List.of(1, -8, -7);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (board.getBottomrightlimit().contains(id)) {
                limit = "bottomright";
                offsets = List.of(-1, -8, -9);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (board.getToplimits().contains(id)) {
                limit = "top";
                offsets = List.of(-1, 1, 7, 8, 9);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (board.getLeftlimits().contains(id)) {
                limit = "left";
                offsets = List.of(-8, -7, 1, 8, 9);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (board.getRightlimits().contains(id)) {
                limit = "right";
                offsets = List.of(-9, -8, -1, 7, 8);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (board.getBottomlimits().contains(id)) {
                limit = "bottom";
                offsets = List.of(-9, -8, -7, -1, 1);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            }
        } else {
            limit = "none";
            offsets = List.of(-9, -8, -7, -1, 1, 7, 8, 9);
            limitandoffsets.add(limit);
            limitandoffsets.add(offsets);
        }
        return limitandoffsets;
    }

    public static void updateBoard(Circle[] circles, Board board, Player player1, Player player2) {

        String playercolor;

        if (player1.getPlayermove()) {
            playercolor = player1.getPlayercolor();
        } else {
            playercolor = player2.getPlayercolor();
        }

        for (int count = 0; count < board.getChangeids().size(); count++) {
            for (int secondcount = 0; secondcount < circles.length; secondcount++) {
                if (board.getChangeids().get(count) == secondcount) {
                    circles[secondcount].setFill(Paint.valueOf(playercolor));
                }
            }
        }
        board.getChangeids().clear();
    }

    public static void updateScore(Circle[] circles, Text text1, Text text2, Board board, Player player1, Player player2) {

        int score1 = 0;
        int score2 = 0;
        for (int i = 72; i < 136; i++) {
            if (circles[i].getFill().toString().equals(board.getHexcolorred())) {
                score1++;
                text1.setText(String.valueOf(score1));
            } else if (circles[i].getFill().toString().equals(board.getHexcolorblack())) {
                score2++;
                text2.setText(String.valueOf(score2));
            }
        }
    }
}