package com.eringa.reversij;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameLogic {

    public static void coinFlip(Player player1, Player player2, Board board, Text playerturn) {

        int num = (int)(Math.random() * 2) + 1;
        if (num == 1) {
            player1.setPlayermove(true);
            player1.setPlayercolor(board.getHexcolorred());
            playerturn.setText("It's your turn, Player 1!");
        } else {
            player2.setPlayermove(true);
            player2.setPlayercolor(board.getHexcolorred());
            playerturn.setText("It's your turn, Player 2!");
        }
    }

    public static ArrayList<Integer> checkLines(String circleid, Circle[] circles, ArrayList<Integer> changeids, String playercolor, String opponentcolor, Board board) {

        Integer id = Integer.valueOf(circleid);
        ArrayList<Integer> tempids = new ArrayList<>();

        List<Object> limitandoffsets = new ArrayList<>();
        List<Integer> offsets;

        Integer offset;
        Integer[] boardlimits = {72, 73, 74, 75, 76, 77, 78, 79, 80, 88, 96, 104, 112, 120, 87, 95, 103, 111, 119, 127, 128, 129, 130,
                131, 132, 133, 134, 135};
        String limit;
        Integer direction;
        Boolean border = false;

        limitandoffsets = getOffsets(id, boardlimits, limitandoffsets);

        limit = (String) limitandoffsets.get(0);
        offsets = (List<Integer>) limitandoffsets.get(1);

        for (int i = 0; i < offsets.size(); i++) {

            if (Arrays.stream(boardlimits).anyMatch(s -> s.equals(id))) {
                border = true;
            }

            if (circles[id].getFill().toString().equals(board.getHexcolorblue())) {
                tempids.add(id);

                for (int j = 1; j < 8; j++) {

                    offset = id + (offsets.get(i) * j);
                    direction = offsets.get(i);

                    Integer finalOffset = offset;

                    if (j == 1) {

                        if (circles[offset].getFill().toString().equals(opponentcolor)) {
                            if (!Arrays.stream(boardlimits).anyMatch(s -> s.equals(finalOffset))) {
                                tempids.add(offset);
                                border = false;
                            } else {
                                if (Arrays.stream(boardlimits).anyMatch(s -> s.equals(finalOffset)) && border) {
                                    if (limit == "topleft" && (direction == 1 || direction == 8)) {
                                        tempids.add(offset);
                                    } else if (limit == "topright" && (direction == -1 || direction == 8)) {
                                        tempids.add(offset);
                                    } else if (limit == "bottomleft" && (direction == 1 || direction == -8)) {
                                        tempids.add(offset);
                                    } else if (limit == "bottomright" && (direction == -1 || direction == -8)) {
                                        tempids.add(offset);
                                    } else if (limit == "top" && offset > 72 && offset < 79) {
                                        tempids.add(offset);
                                    } else if (limit == "left" && offset == 80 || offset == 88 || offset == 96 || offset == 104 || offset == 112 || offset == 120) {
                                        tempids.add(offset);
                                    } else if (limit == "right" && offset == 87 || offset == 95 || offset == 103 || offset == 111 || offset == 119 || offset == 127) {
                                        tempids.add(offset);
                                    } else if (limit == "bottom" && offset > 128 && offset < 135) {
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

                    } else if (j > 1) {

                        if (circles[offset].getFill().toString().equals(opponentcolor)) {
                            if (!border) {
                                if (Arrays.stream(boardlimits).anyMatch(s -> s.equals(finalOffset))) {
                                    border = false;
                                    tempids.clear();
                                    break;
                                } else {
                                    tempids.add(offset);
                                }
                            } else {
                                if (limit == "topleft" && (direction == 1 || direction == 8)) {
                                    tempids.add(offset);
                                } else if (limit == "topright" && (direction == -1 || direction == 8)) {
                                    tempids.add(offset);
                                } else if (limit == "bottomleft" && (direction == -8 || direction == 1)) {
                                    tempids.add(offset);
                                } else if (limit == "bottomright" && (direction == -8 || direction == -1)) {
                                    tempids.add(offset);
                                } else if (limit == "top" && (direction == -1 || direction == 1)) {
                                    if (offset != 72 && offset != 79) {
                                        tempids.add(offset);
                                    } else {
                                        border = false;
                                        tempids.clear();
                                        break;
                                    }
                                } else if (limit == "left" && (direction == -8 || direction == 8)) {
                                    if (offset != 72 && offset != 128) {
                                        tempids.add(offset);
                                    } else {
                                        border = false;
                                        tempids.clear();
                                        break;
                                    }
                                } else if (limit == "right" && (direction == -8 || direction == 8)) {
                                    if (offset != 79 && offset != 135) {
                                        tempids.add(offset);
                                    } else {
                                        border = false;
                                        tempids.clear();
                                        break;
                                    }
                                } else if (limit == "bottom" && (direction == -1 || direction == 1)) {
                                    if (offset != 128 && offset != 135) {
                                        tempids.add(offset);
                                    } else {
                                        border = false;
                                        tempids.clear();
                                        break;
                                    }
                                }
                                else {
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
                            for (int k = 0; k < tempids.size(); k++) {
                                //System.out.println(tempids.get(k));
                                changeids.add(tempids.get(k));
                            }
                            border = false;
                            break;
                        }
                    }
                }
            }
        }
        return changeids;
        }


    public static List<Object> getOffsets(Integer id, Integer[] boardlimits, List<Object> limitandoffsets) {

        String limit;
        List<Integer> offsets;

        Integer[] topleftlimit = Arrays.copyOfRange(boardlimits, 0, 1);
        Integer[] toprightlimit = Arrays.copyOfRange(boardlimits, 7, 8);
        Integer[] bottomleftlimit = Arrays.copyOfRange(boardlimits, 20, 21);
        Integer[] bottomrightlimit = Arrays.copyOfRange(boardlimits, 27, 28);
        Integer[] toplimits = Arrays.copyOfRange(boardlimits, 1, 7);
        Integer[] leftlimits = Arrays.copyOfRange(boardlimits, 8, 14);
        Integer[] rightlimits = Arrays.copyOfRange(boardlimits, 14, 20);
        Integer[] bottomlimits = Arrays.copyOfRange(boardlimits, 21, 27);

        if (Arrays.stream(boardlimits).anyMatch(s -> s.equals(id))) {
            if (Arrays.stream(topleftlimit).anyMatch(s -> s.equals(id))) {
                limit = "topleft";
                offsets = (List.of(1, 8, 9));
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (Arrays.stream(toprightlimit).anyMatch(s -> s.equals(id))) {
                limit = "topright";
                offsets = List.of(-1, 8, 7);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (Arrays.stream(bottomleftlimit).anyMatch(s -> s.equals(id))) {
                limit = "bottomleft";
                offsets = List.of(1, -8, -7);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (Arrays.stream(bottomrightlimit).anyMatch(s -> s.equals(id))) {
                limit = "bottomright";
                offsets = List.of(-1, -8, -9);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (Arrays.stream(toplimits).anyMatch(s -> s.equals(id))) {
                limit = "top";
                offsets = List.of(-1, 1, 7, 8, 9);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (Arrays.stream(leftlimits).anyMatch(s -> s.equals(id))) {
                limit = "left";
                offsets = List.of(-8, -7, 1, 8, 9);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (Arrays.stream(rightlimits).anyMatch(s -> s.equals(id))) {
                limit = "right";
                offsets = List.of(-9, -8, -1, 7, 8);
                limitandoffsets.add(limit);
                limitandoffsets.add(offsets);
            } else if (Arrays.stream(bottomlimits).anyMatch(s -> s.equals(id))) {
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

    public static void updateBoard(Circle[] circles, ArrayList<Integer> changeids, String playercolor) {

        for (int count = 0; count < changeids.size(); count++) {
            for (int secondcount = 0; secondcount < circles.length; secondcount++) {
                if (changeids.get(count) == secondcount) {
                    circles[secondcount].setFill(Paint.valueOf(playercolor));
                }

            }
        }
        changeids.clear();
    }

    public static void updateScore(Circle[] circles, Text text1, Text text2, String color1, String color2) {

        int score1 = 0;
        int score2 = 0;
        for (int i = 72; i < 136; i++) {
            if (circles[i].getFill().toString().equals(color1)) {
                score1++;
                text1.setText(String.valueOf(score1));
            } else if (circles[i].getFill().toString().equals(color2)) {
                score2++;
                text2.setText(String.valueOf(score2));
            }
        }
        //System.out.println(score);
    }
}
