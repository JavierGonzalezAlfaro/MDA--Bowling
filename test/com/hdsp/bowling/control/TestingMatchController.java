package com.hdsp.bowling.control;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hdsp.bowling.model.Player;
import com.hdsp.bowling.model.PlayerFrame;
import com.hdsp.bowling.model.PlayerGame;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestingMatchController {

    private MatchController matchController;

    @Before
    public void setUp() throws Exception {
        matchController = new MatchController();
        addPlayers();
    }

    @Test
    public void should_return_player_one_when_ask_player_on_first_turn() throws Exception{
        assertThat(matchController.getPlayerTurn().id(), is("One"));
    }

    @Test
    public void should_return_player_two_when_player_one_has_rolled_two_times_no_last_frame() throws Exception{
        assertThat(matchController.getPlayerTurn().id(), is("One"));
        matchController.addRoll(5);
        matchController.addRoll(3);
        assertThat(matchController.getPlayerTurn().id(), is("Two"));
    }

    @Test
    public void should_return_player_one_when_player_one_and_two_have_rolled_two_times_no_last_frame() throws Exception{
        assertThat(matchController.getPlayerTurn().id(), is("One"));
        matchController.addRoll(5);
        matchController.addRoll(3);
        assertThat(matchController.getPlayerTurn().id(), is("Two"));
        matchController.addRoll(5);
        matchController.addRoll(3);
        assertThat(matchController.getPlayerTurn().id(), is("One"));
    }


    @Test
    public void should_return_player_two_when_player_one_has_rolled_once_and_strike_no_last_frame() throws Exception{
        assertThat(matchController.getPlayerTurn().id(), is("One"));
        matchController.addRoll(10);
        assertThat(matchController.getPlayerTurn().id(), is("Two"));
    }


    @Test
    public void should_return_player_two_when_player_one_has_rolled_two_times_and_spare_no_last_frame() throws Exception{
        assertThat(matchController.getPlayerTurn().id(), is("One"));
        matchController.addRoll(3);
        matchController.addRoll(7);
        assertThat(matchController.getPlayerTurn().id(), is("Two"));
    }

    @Test
    public void should_return_player_one_when_player_one_has_rolled_a_spare_on_last_frame() throws Exception{
        for (int i = 0; i < 18; i++) {
            matchController.addRoll(3);
            matchController.addRoll(4);
        }
        assertThat(matchController.getPlayerTurn().id(), is("One"));
        matchController.addRoll(3);
        matchController.addRoll(7);
        assertThat(matchController.getPlayerTurn().id(), is("One"));
    }

    @Test
    public void should_return_player_two_when_player_one_has_rolled_a_spare_on_last_frame() throws Exception{
        for (int i = 0; i < 18; i++) {
            matchController.addRoll(3);
            matchController.addRoll(4);
        }
        assertThat(matchController.getPlayerTurn().id(), is("One"));
        matchController.addRoll(3);
        matchController.addRoll(7);
        assertThat(matchController.getPlayerTurn().id(), is("One"));
    }




    private void addPlayers() {
        matchController.addPlayers(new Player("One"), new Player("Two"));
    }






}
