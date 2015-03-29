package com.hdsp.bowling.model;

import org.junit.Test;

import com.hdsp.bowling.control.MatchController;
import static org.junit.Assert.*;


public class PlayerGameTest {

    private final Player one;
    private final Player two;
    private Match match;

    public PlayerGameTest() {
        match = new Match();
        one = new Player("one");
        match.addPlayer(one);
        two = new Player("two");
        match.addPlayer(two);
    }

    @Test
    public void withNoRolls() throws Exception {
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(0, playerGame.getFrames().length);
    }

    @Test
    public void withOneRoll() throws Exception {
        match.addRolls(4).toPlayer(one);
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(1, playerGame.getFrames().length);
        assertNull(playerGame.getFrames()[0].getScoring());
    }
    @Test
    public void withTwoRolls() throws Exception {
        match.addRolls(4, 5).toPlayer(one);
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(1, playerGame.getFrames().length);
        assertEquals(9,playerGame.getFrames()[0].getScoring().getPoints());
    }

    @Test
    public void withOtherTwoRolls() {
        match.addRolls(1, 4).toPlayer(one);
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(1, playerGame.getFrames().length);
        assertEquals(5, playerGame.getFrames()[0].getScoring().getPoints());
    }

    @Test
    public void withThreeRolls() {
        match.addRolls(1, 4, 3).toPlayer(one);
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(2, playerGame.getFrames().length);
        assertNotNull(playerGame.getFrames()[0].getScoring());
        assertEquals(5,playerGame.getFrames()[0].getScoring().getPoints());
        assertNull(playerGame.getFrames()[1].getScoring());
    }

    @Test
    public void withStrike() throws Exception {
        match.addRolls(10,5).toPlayer(one);
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(2, playerGame.getFrames().length);
        assertNull(playerGame.getFrames()[0].getScoring());
        assertNull(playerGame.getFrames()[1].getScoring());
    }

    @Test
    public void withOneStrike() throws Exception {
        match.addRolls(10).toPlayer(one);
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(1, playerGame.getFrames().length);
        assertNull(playerGame.getFrames()[0].getScoring());
        assertTrue(playerGame.getFrames()[0].isStrike());
    }

    @Test
    public void withStrikeAndTwoMoreRolls() throws Exception {
        match.addRolls(10,5,3).toPlayer(one);
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(2, playerGame.getFrames().length);
        assertEquals(18, playerGame.getFrames()[0].getScoring().getPoints());
        assertEquals(26, playerGame.getFrames()[1].getScoring().getPoints());
    }

    @Test
    public void withSpare() throws Exception {
        match.addRolls(5,5).toPlayer(one);
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(1, playerGame.getFrames().length);
        assertNull(playerGame.getFrames()[0].getScoring());
    }

    @Test
    public void withSpareAndOneMoreRoll() throws Exception {
        match.addRolls(5,5,5).toPlayer(one);
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(2, playerGame.getFrames().length);
        assertEquals(15, playerGame.getFrames()[0].getScoring().getPoints());
        assertNull(playerGame.getFrames()[1].getScoring());
    }

    @Test
    public void withTwelveStrikes() throws Exception{
        match.addRolls(10,10,10,10,10,10,10,10,10,10,10,10).toPlayer(one);
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(10,playerGame.getFrames().length);
        assertEquals(30, playerGame.getFrames()[0].getScoring().getPoints());
        assertEquals(60, playerGame.getFrames()[1].getScoring().getPoints());
        assertEquals(90, playerGame.getFrames()[2].getScoring().getPoints());
        assertEquals(120, playerGame.getFrames()[3].getScoring().getPoints());
        assertEquals(150, playerGame.getFrames()[4].getScoring().getPoints());
        assertEquals(180, playerGame.getFrames()[5].getScoring().getPoints());
        assertEquals(210, playerGame.getFrames()[6].getScoring().getPoints());
        assertEquals(240, playerGame.getFrames()[7].getScoring().getPoints());
        assertEquals(270, playerGame.getFrames()[8].getScoring().getPoints());
        assertEquals(300, playerGame.getFrames()[9].getScoring().getPoints());
    }

    @Test
    public void withTwoRollsEachPlayer() throws Exception {
        match.addRolls(4,5).toPlayer(one);
        PlayerGame playerOneGame = new PlayerGame(match, one);
        assertEquals(1, playerOneGame.getFrames().length);
        assertEquals(9, playerOneGame.getFrames()[0].getScoring().getPoints());
        match.addRolls(4,5).toPlayer(two);
        PlayerGame playerTwoGame = new PlayerGame(match, one);
        assertEquals(1, playerTwoGame.getFrames().length);
        assertEquals(9, playerTwoGame.getFrames()[0].getScoring().getPoints());

    }

    @Test
    public void rewardTest() throws Exception{
        match.addRolls(10,9,1,5,5,7,2,10,10,10,9,0,8,2,9,1,10).toPlayer(one);
        PlayerGame playerGame = new PlayerGame(match, one);
        assertEquals(10,playerGame.getFrames().length);
        assertEquals(20, playerGame.getFrames()[0].getScoring().getPoints());
        assertEquals(35, playerGame.getFrames()[1].getScoring().getPoints());
        assertEquals(52, playerGame.getFrames()[2].getScoring().getPoints());
        assertEquals(61, playerGame.getFrames()[3].getScoring().getPoints());
        assertEquals(91, playerGame.getFrames()[4].getScoring().getPoints());
        assertEquals(120, playerGame.getFrames()[5].getScoring().getPoints());
        assertEquals(139, playerGame.getFrames()[6].getScoring().getPoints());
        assertEquals(148, playerGame.getFrames()[7].getScoring().getPoints());
        assertEquals(167, playerGame.getFrames()[8].getScoring().getPoints());
        assertEquals(187, playerGame.getFrames()[9].getScoring().getPoints());
    }

}









