package com.eringa.reversij;

public class Player {

    private Boolean playermove;
    private String playercolor;
    private String playername;

    private Integer playerscore;

    public Player(Boolean playermove, String playercolor, String playername, Integer playerscore) {
        this.playermove = playermove;
        this.playercolor = playercolor;
        this.playername = playername;
        this.playerscore = playerscore;
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

    public Integer getPlayerscore() {
        return playerscore;
    }

    public void setPlayerscore(Integer playerscore) {
        this.playerscore = playerscore;
    }
}
