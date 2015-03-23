package com.hdsp.bowling.model;

public class Roll {
    private Player player;
    private final int pins;

    public Roll(Player player, int pins) {
        this.player = player;
        this.pins = pins;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPins() {
        return pins;
    }

    public Player getPlayer() {
        return player;
    }
}
