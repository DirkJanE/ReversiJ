package com.eringa.reversij;

import javafx.scene.shape.Circle;

public class GameLogic {

    public static Boolean checkLine(String circleid, Circle[] circles) {
        //System.out.print(circleid);

        Integer id;

        if (circleid.length() == 8) {
            id = Integer.valueOf(circleid.substring(6, 8));
        }
        else {
            id = Integer.valueOf(circleid.substring(6, 7));
        }

        String hexcolor = "0xadd8e6ff";

        for (int count = 1; count < 4; count++) {
            if (circles[id - count].getFill().toString().equals(hexcolor)) {

            } else {
                return false;
            }
        }
        return true;
    }

}
