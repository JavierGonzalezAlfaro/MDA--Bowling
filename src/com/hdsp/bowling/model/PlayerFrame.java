package com.hdsp.bowling.model;

public class PlayerFrame {

    public static final PlayerFrame Empty = emptyFrame();

    private PlayerFrame previousFrame;
    private final Roll[] rolls;

    public PlayerFrame(PlayerFrame previousFrame, Roll[] rolls) {
        this.previousFrame = previousFrame;
        this.rolls = rolls;
    }

    public int getNumberOfRolls() {
        return isStrike() ? 1 : 2;
    }

    public int getNumberOfRollsInLastFrame(){
        return rolls.length;
    }

    public boolean isStrike() {
        return firstRollPins() == 10;
    }

    public boolean isSpare() {
        return hasTwoRolls() && pinsInTwoRolls() == 10;
    }

    public Scoring getScoring() {
        return isComplete() ? new Scoring(points()) : null;
    }

    private int points() {
        return pointsOfRolls() + pointsOfPreviousFrame();
    }

    private int pointsOfRolls() {
        return isStrike() || isSpare() ? pinsInThreeRolls() : pinsInTwoRolls();
    }

    private int pointsOfPreviousFrame() {
        return previousFrame.getScoring().getPoints();
    }

    private int pinsInTwoRolls() {
        return firstRollPins() + secondRollPins();
    }

    private int pinsInThreeRolls() {
        return pinsInTwoRolls() + thirdRollPins();
    }

    private int firstRollPins() {
        return getPinsOfRoll(0);
    }

    private int secondRollPins() {
        return getPinsOfRoll(1);
    }

    private int thirdRollPins() {
        return getPinsOfRoll(2);
    }

    private boolean isComplete() {
        return hasRequiredRolls(toCompleteFrame());
    }

    private boolean hasTwoRolls() {
        return hasRequiredRolls(2);
    }

    private int toCompleteFrame() {
        return isStrike() || isSpare() ? 3 : 2;
    }

    private boolean hasRequiredRolls(int number) {
        return number <= rolls.length;
    }

    private int getPinsOfRoll(int index) {
        return rolls[index].getPins();
    }

    public class Scoring {

        private int points;

        public Scoring(int points) {
            this.points = points;
        }

        public int getPoints() {
            return points;
        }
    }

    private static PlayerFrame emptyFrame() {
        return new PlayerFrame(null, new Roll[0]) {
            @Override
            public Scoring getScoring() {
                return new Scoring(0);
            }
        };
    }
}
