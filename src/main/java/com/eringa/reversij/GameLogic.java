package com.eringa.reversij;

import javafx.scene.shape.Circle;



public class GameLogic {

    public static Integer checkHorizontalLine(String circleid, Circle[] circles) {
        //System.out.print(circleid);

        Integer id;

        if (circleid.length() == 8) {
            id = Integer.valueOf(circleid.substring(6, 8));
        }
        else {
            id = Integer.valueOf(circleid.substring(6, 7));
        }
        System.out.println(circles[id]);

        //if (circles[id - 1].getFill() == "LIGHTBLUE")

        return 0;
    }

}
