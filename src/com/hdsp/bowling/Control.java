package com.hdsp.bowling;

import java.util.ArrayList;
import java.util.List;

public class Control {
    private Match match;
    private List<PlayerGame> playerGameList;

    public Control(Match match, PlayerGame... games) {
        this.match = match;
        playerGameList = new ArrayList<>();
        for (PlayerGame game : games) {
            playerGameList.add(game);
        }
    }

    public void startGame(){
        int counter = 0;
        String actualPlayer= "";
        RollDialog rollDialog = new RollDialog();
        for (int i = 0; i < 10*playerGameList.size(); i++) {
            actualPlayer = match.getPlayers().get(counter).getId();
            match.addRolls(rollDialog.makeRoll(actualPlayer)).toPlayer(actualPlayer);
            if (playerGameList.get(counter).getFrames()[playerGameList.get(counter).getFrames().length-1].getScoring() != null || playerGameList.get(counter).getFrames()[playerGameList.get(counter).getFrames().length-1].isStrike() || playerGameList.get(counter).getFrames()[playerGameList.get(counter).getFrames().length-1].isSpare()){
                showScore(counter);
                counter = (counter + 1) % match.getPlayers().size();
            }
        }
    }

    private void showScore(int counter) {
        System.out.println("Jugador " + playerGameList.get(counter).getPlayerName() + ": ");
        for (PlayerFrame frame : playerGameList.get(counter).getFrames()) {
            System.out.print(frame.getScoring() != null ? frame.getScoring().getPoints() : "");
        }
        System.out.println();
    }

}
