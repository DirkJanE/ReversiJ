package com.eringa.reversij;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class GameLogic {

    //static List<Integer> offsets = new ArrayList<>(List.of(-9, -8, -7, -1, 1, 7, 8, 9));

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

    public static ArrayList<Integer> checkLine(String circleid, Circle[] circles, ArrayList<Integer> changeids, String playercolor, String opponentcolor) {

        Integer id;
        ArrayList<Integer> tempids = new ArrayList<>();

        if (circleid.length() == 8) {
            id = Integer.valueOf(circleid.substring(6, 8));
        }
        else {
            id = Integer.valueOf(circleid.substring(6, 9));
        }

        //System.out.println("id: " + id);

        String hexcolorblue = "0xadd8e6ff";

        List<Integer> offsets = new ArrayList<>();

        Integer offset = 0;
        Integer[] boardlimits = {72, 73, 74, 75, 76, 77, 78, 79, 80, 88, 96, 104, 112, 120, 87, 95, 103, 111, 119, 127, 128, 129, 130,
        131, 132, 133, 134, 135};

        offsets = getOffsets(id, boardlimits, offsets);

        /*

        for (int q = 0; q < offsets.size(); q++) {
            System.out.println("Print: " + offsets.get(q));
        }

         */

        for (int i = 0; i < offsets.size(); i++) {
            //System.out.println(i);

            if (circles[id].getFill().toString().equals(hexcolorblue)) {
                tempids.add(id);
                //System.out.println("Added id: " + id);

                for (int j = 1; j < 8; j++) {

                    offset = id + (offsets.get(i) * j);
                    //System.out.println(offset);

                    if (j == 1) {
                        if (circles[offset].getFill().toString().equals(opponentcolor)) {
                            System.out.println("j = 1, ID: " + id);
                            System.out.println("j = 1, Offset: " + offsets.get(i) * j);
                            System.out.println("j = 1, New id: " + (id + offsets.get(i) * j));
                            tempids.add(offset);
                            //System.out.println("j == 1, match opponent color");
                        } else {
                            tempids.clear();
                            break;
                        }
                    } else if (j > 1) {
                        System.out.println("j > 1, ID: " + id);
                        System.out.println("j > 1, Offset: " + offsets.get(i) * j);
                        System.out.println("j > 1, New id: " + (id + offsets.get(i) * j));

                        if (circles[offset].getFill().toString().equals(hexcolorblue)) {
                            System.out.println("First break");
                            tempids.clear();
                            break;
                        } else if (circles[offset].getFill().toString().equals(opponentcolor)) {
                            System.out.println("j > 1, match opponentcolor");

                            Integer finalOffset = offset;
                            if (!Arrays.stream(boardlimits).anyMatch(s -> s.equals(finalOffset))) {
                                tempids.add(offset);
                            } else {
                             System.out.println("Second break");
                             tempids.clear();
                             break;
                            }

                        } else if (circles[offset].getFill().toString().equals(playercolor)) {
                            //System.out.println("j > 1, match player color");
                            for (int k = 0; k < tempids.size(); k++) {
                                //System.out.println(tempids.get(k));
                                changeids.add(tempids.get(k));
                            }
                            break;
                        }
                    }
                }
            }
        }

        tempids.clear();

        return changeids;
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

    public static List<Integer> getOffsets(Integer id, Integer[] boardlimits, List<Integer> offsets) {
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
                offsets = List.of(1, 8, 9);
            } else if (Arrays.stream(toprightlimit).anyMatch(s -> s.equals(id))) {
                offsets = List.of(-1, 8, 7);
            } else if (Arrays.stream(bottomleftlimit).anyMatch(s -> s.equals(id))) {
                offsets = List.of(1, -8, -7);
            } else if (Arrays.stream(bottomrightlimit).anyMatch(s -> s.equals(id))) {
                offsets = List.of(-1, -8, -9);
            } else if (Arrays.stream(toplimits).anyMatch(s -> s.equals(id))) {
                offsets = List.of(-1, 1, 7, 8, 9);
            } else if (Arrays.stream(leftlimits).anyMatch(s -> s.equals(id))) {
                offsets = List.of(-8, -7, 1, 8, 9);
            } else if (Arrays.stream(rightlimits).anyMatch(s -> s.equals(id))) {
                offsets = List.of(-9, -8, -1, 7, 8);
            } else if (Arrays.stream(bottomlimits).anyMatch(s -> s.equals(id))) {
                offsets = List.of(-9, -8, -7, -1, 1);
            }
        } else {
            offsets = List.of(-9, -8, -7, -1, 1, 7, 8, 9);
        }
        return offsets;
    }

}
