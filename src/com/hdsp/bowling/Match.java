package com.hdsp.bowling;

import java.util.ArrayList;
import java.util.List;

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

    public Player getPlayer(String name) {
        for (Player player : players)
            if (player.getId().equals(name)) return player;
        return null;
    }

    public Roll[] getRolls(String playerName){
        Player player = getPlayer(playerName);
        List<Roll> rolls = new ArrayList<>();
        for (Roll roll : this.rolls)
            if (roll.getPlayer() == player)
                rolls.add(roll);
        return this.rolls.toArray(new Roll[this.rolls.size()]);
    }

    public AddRollTask addRolls(final int... pinsOfRolls) {
        return new AddRollTask() {
            @Override
            public void toPlayer(String name) {
                for (int pinsOfRoll : pinsOfRolls) {
                    rolls.add(new Roll(getPlayer(name), pinsOfRoll));
                }
            }
        };
    }

    public List<Player> getPlayers() {
        return players;
    }

    public interface AddRollTask {
        void toPlayer(String name);
    }
}











