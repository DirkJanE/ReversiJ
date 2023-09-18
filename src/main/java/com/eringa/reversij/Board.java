package com.eringa.reversij;

import java.util.ArrayList;
import java.util.List;

public class Board {

    final ArrayList<Integer> changeids = new ArrayList<>();

    final ArrayList<Integer> boardlimits = new ArrayList<>(List.of(72, 73, 74, 75, 76, 77, 78, 79, 80, 88, 96, 104, 112, 120, 87, 95, 103, 111, 119, 127, 128, 129, 130,
            131, 132, 133, 134, 135));

    final ArrayList<Integer> topleftlimit = new ArrayList<>(boardlimits.subList(0, 1));

    final ArrayList<Integer> toprightlimit = new ArrayList<>(boardlimits.subList(7, 8));

    final ArrayList<Integer> bottomleftlimit = new ArrayList<>(boardlimits.subList(20, 21));

    final ArrayList<Integer> bottomrightlimit = new ArrayList<>(boardlimits.subList(27, 28));

    final ArrayList<Integer> toplimits = new ArrayList<>(boardlimits.subList(1, 7));

    final ArrayList<Integer> leftlimits = new ArrayList<>(boardlimits.subList(8, 14));

    final ArrayList<Integer> rightlimits = new ArrayList<>(boardlimits.subList(14, 20));

    final ArrayList<Integer> bottomlimits = new ArrayList<>(boardlimits.subList(21, 27));

    final String hexcolorblue;
    final String hexcolorred;
    final String hexcolorblack;

    public Board(String hexcolorblue, String hexcolorred, String hexcolorblack) {
        this.hexcolorblue = hexcolorblue;
        this.hexcolorred = hexcolorred;
        this.hexcolorblack = hexcolorblack;
    }

    public ArrayList<Integer> getChangeids() {
        return changeids;
    }

    public ArrayList<Integer> getBoardlimits() {
        return boardlimits;
    }

    public ArrayList<Integer> getTopleftlimit() {
        return topleftlimit;
    }

    public ArrayList<Integer> getToprightlimit() {
        return toprightlimit;
    }

    public ArrayList<Integer> getBottomleftlimit() {
        return bottomleftlimit;
    }

    public ArrayList<Integer> getBottomrightlimit() {
        return bottomrightlimit;
    }

    public ArrayList<Integer> getToplimits() {
        return toplimits;
    }

    public ArrayList<Integer> getLeftlimits() {
        return leftlimits;
    }

    public ArrayList<Integer> getRightlimits() {
        return rightlimits;
    }

    public ArrayList<Integer> getBottomlimits() {
        return bottomlimits;
    }

    public String getHexcolorblue() {
        return hexcolorblue;
    }

    public String getHexcolorred() {
        return hexcolorred;
    }

    public String getHexcolorblack() {
        return hexcolorblack;
    }
}
