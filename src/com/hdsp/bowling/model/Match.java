package com.hdsp.bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Match {
    private final List<Player> players;
    private final List<Roll> rolls;

    public Match() {
        this.players = new ArrayList<>();
        this.rolls = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public Player getPlayerById(String name) {
        for (Player player : players)
            if (player.id().equals(name)) return player;
        return null;
    }

    public Roll[] getRollsByPlayer(String playerId){
        Player currentPlayer = getPlayerById(playerId);
        return rolls.stream().filter(roll -> currentPlayer == roll.getPlayer()).toArray(Roll[]::new);
    }

    public AddRollTask addRolls(final int... pinsOfRolls) {
        return player -> {
            for (int pinsOfRoll : pinsOfRolls) {
                rolls.add(new Roll(player, pinsOfRoll));
            }
        };
    }

    public List<Player> getPlayers() {
        return players;
    }

    public interface AddRollTask {
        void toPlayer(Player player);
    }
}











