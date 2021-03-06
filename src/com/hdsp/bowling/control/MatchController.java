package com.hdsp.bowling.control;

import java.util.ArrayList;
import java.util.List;

import com.hdsp.bowling.model.Match;
import com.hdsp.bowling.model.Player;
import com.hdsp.bowling.model.PlayerFrame;
import com.hdsp.bowling.model.PlayerGame;

public class MatchController {


    private List<PlayerGame> playerGames;
    private Match currentMatch;
    private int currentPlayerIndexCount = 0;

    public MatchController(Match currentMatch) {
        this.playerGames = new ArrayList<>();
        this.currentMatch = currentMatch;
    }


    public void addPlayers(Player... players) {
        for (Player player : players) {
            currentMatch.addPlayer(player);
            playerGames.add(new PlayerGame(currentMatch, player));
        }
    }

    public Player getPlayerTurn() {
        return playerGames.get(currentPlayerIndex()).getPlayer();
    }


    public Match getMatch() {
        return currentMatch;
    }

    public void addRoll(int pins) {
        currentMatch.addRolls(pins).toPlayer(getPlayerTurn());
        if(isLastFrameOfMatch() && isStrikeOrSpare() && playerCanRollAgain()) return;
        if(isLastFrameCompleted() || isStrikeOrSpare()) currentPlayerIndexCount++;
    }



    private boolean isStrikeOrSpare() {
        return isSpare() || isStrike();
    }

    private boolean playerCanRollAgain() {
        return currentPlayerLastFrame().getNumberOfRollsInLastFrame() < 3;
    }

    private boolean isLastFrameOfMatch() {
        return playerGames.get(currentPlayerIndex()).getFrames().length == 10;
    }

    private boolean isSpare() {
        return currentPlayerLastFrame().isSpare();
    }

    private boolean isStrike() {
        return currentPlayerLastFrame().isStrike();
    }

    private boolean isLastFrameCompleted() {
        return getCurrentPlayerLastFrameScoring() != null;
    }

    private PlayerFrame.Scoring getCurrentPlayerLastFrameScoring() {
        return currentPlayerLastFrame().getScoring();
    }

    private PlayerFrame currentPlayerLastFrame() {
        PlayerFrame[] frames = playerGames.get(currentPlayerIndex()).getFrames();
        return frames[frames.length - 1];
    }

    private int currentPlayerIndex() {
        return currentPlayerIndexCount % playerGames.size();
    }

    public List<Player> players() {
        return currentMatch.getPlayers();
    }
}
