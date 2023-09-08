package com.eringa.reversij;

public class Player {

    Boolean playermove;
    String playercolor;
    String playername;

    public Player(Boolean playermove, String playercolor) {
        this.playermove = playermove;
        this.playercolor = playercolor;
        //this.playername = playername;
    }

    public Boolean getPlayermove() {
        return playermove;
    }

    public void setPlayermove(Boolean playermove) {
        this.playermove = playermove;
    }

    public String getPlayercolor() {
        return playercolor;
    }

    public void setPlayercolor(String playercolor) {
        this.playercolor = playercolor;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }
}
