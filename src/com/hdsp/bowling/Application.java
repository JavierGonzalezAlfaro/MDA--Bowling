package com.hdsp.bowling;

public class Application {

    public static void main(String[] args) {
        Match match = new Match();
        match.addPlayer(new Player("Javi"));
        match.addPlayer(new Player("Carlos"));
        PlayerGame javiGame = new PlayerGame(match,"Javi");
        PlayerGame carlosGame = new PlayerGame(match,"Carlos");
        Control control = new Control(match, javiGame, carlosGame);
        control.startGame();
    }
}
