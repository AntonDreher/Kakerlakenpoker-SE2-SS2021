package com.example.kakerlakenpoker;

import com.example.minigame.Counter;
import com.example.minigame.Hand;
import com.example.minigame.MiniGame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MiniGameUnitTest {
    Counter testCounter;
    Hand testHand;
    MiniGame testMiniGame;

    @Before
    public void setup() {
        testCounter = new Counter();
        testHand = new Hand("Rock");
        testMiniGame = new MiniGame(testHand, 2);
    }

    @After
    public void tearDown() {
        testCounter = null;
        testHand = null;
        testMiniGame = null;
    }

    @Test
    public void gameLogicCompWinTest(){
        testHand.getOptions().clear();
        testHand.getOptions().add("Paper");
        testHand.setPlay("Rock");
        assertEquals("Computer played: Paper<br/><br/>You played: Rock<br/><br/><b>COMPUTER WINS.", testMiniGame.game(testCounter));
    }

    @Test
    public void gameLogicPlayerWinTest(){
        testHand.getOptions().clear();
        testHand.getOptions().add("Scissors");
        testHand.setPlay("Rock");
        assertEquals("Computer played: Scissors<br/><br/>You played: Rock<br/><br/><b>YOU WIN.", testMiniGame.game(testCounter));
    }

    @Test
    public void gameLogicDrawTest(){
        testHand.getOptions().clear();
        testHand.getOptions().add("Spock");
        testHand.setPlay("Spock");
        assertEquals("Computer played: Spock<br/><br/>You played: Spock<br/><br/><b>IT'S A DRAW.", testMiniGame.game(testCounter));
    }

    @Test
    public void lossCounterTest(){
        testHand.getOptions().clear();
        testHand.getOptions().add("Paper");
        testHand.setPlay("Rock");
        testMiniGame.game(testCounter);
        testMiniGame.game(testCounter);
        assertEquals(2, testCounter.getLossCounter());
    }

    @Test
    public void winOutOf5CounterTest(){
        testHand.getOptions().clear();
        testHand.getOptions().add("Scissors");
        testHand.setPlay("Rock");
        testMiniGame.game(testCounter);
        testMiniGame.game(testCounter);
        testMiniGame.game(testCounter);
        testMiniGame.game(testCounter);
        testMiniGame.game(testCounter);
        assertEquals(1, testCounter.getWinOutOf5Counter());
    }

    @Test
    public void roundCounterTest(){
        testHand.getOptions().clear();
        testHand.getOptions().add("Paper");
        testHand.setPlay("Rock");
        testMiniGame.game(testCounter);
        assertEquals(1, testCounter.getRoundCounter());
    }

    @Test
    public void computerHandTest(){
        assertNotNull(testHand.computerPlay());
    }

    @Test
    public void playersHandTest(){
        assertEquals("Rock", testHand.getPlay());
    }
}
