package com.eringa.reversij;

import javafx.scene.shape.Circle;

public class Board {

    String hexcolorblue;
    String hexcolorred;
    String hexcolorblack;


    public Board(String hexcolorblue, String hexcolorred, String hexcolorblack) {
        //this.circles = circles;
        this.hexcolorblue = hexcolorblue;
        this.hexcolorred = hexcolorred;
        this.hexcolorblack = hexcolorblack;
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
