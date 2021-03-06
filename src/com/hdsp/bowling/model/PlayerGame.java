
package com.hdsp.bowling.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerGame {

    private final Match match;
    private final Player player;

    public PlayerGame(Match match, Player player) {
        this.match = match;
        this.player = player;
    }

    public PlayerFrame[] getFrames() {
        return buildFrames(match.getRollsByPlayer(player.id())).toArray(new PlayerFrame[0]);
    }

    private List<PlayerFrame> buildFrames(Roll[] rolls) {
        return new FrameBuilder(rolls).build();
    }

    public Player getPlayer() {
        return player;
    }

    private class FrameBuilder {
        public static final int MaxNumberOfFrames = 10;
        private final Roll[] rolls;

        public FrameBuilder(Roll[] rolls) {
            this.rolls = rolls;
        }

        public List<PlayerFrame> build() {
            List<PlayerFrame> frames = new ArrayList<>();
            PlayerFrame frame = PlayerFrame.Empty;
            for (int index = 0; index < rolls.length; index += frame.getNumberOfRolls()) {
                frame = new PlayerFrame(frame, rollsFrom(index));
                frames.add(frame);
                if (frames.size() == MaxNumberOfFrames) break;
            }
            return frames;
        }

        private Roll[] rollsFrom(int index) {
            return Arrays.copyOfRange(rolls, index, rolls.length);
        }
    }


}
