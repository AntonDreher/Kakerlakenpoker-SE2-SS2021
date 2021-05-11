package com.example.kakerlakenpoker;


import com.example.kakerlakenpoker.game.BuildGame;
import com.example.kakerlakenpoker.game.Game;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class BuildGameUnitTest {
    String one;
    String two;
    String three;
    String four;
    BuildGame buildGame;
    @Before
    public void setUp(){
        one = "one";
        two = "two";
        three = "three";
        four = "four";
        buildGame = new BuildGame();
    }

    @After
    public void tearDown() {
        one = null;
        two = null;
        three = null;
        four = null;
        buildGame = null;
    }
    @Test
    public void testAddServerPlayer(){
        buildGame.addServerPlayer(one);
        Assert.assertEquals("one",buildGame.getPlayers().get(0).getName());
        buildGame.addServerPlayer(three);
        buildGame.addServerPlayer(two);
        buildGame.addServerPlayer(four);
        Assert.assertEquals(4,buildGame.getPlayers().size());
    }

    @Test
    public void testDistributeCards(){
        buildGame.addServerPlayer(one);
        buildGame.addServerPlayer(two);
        buildGame.addServerPlayer(three);
        buildGame.addServerPlayer(four);
        buildGame.distributeCards();
        for (int i = 0; i < buildGame.getPlayers().size(); i++) {
            Assert.assertEquals(16,buildGame.getPlayers().get(i).getHandDeck().size());
        }
    }
    @Test
    public void testBuildGame(){
        buildGame.addServerPlayer(one);
        buildGame.addServerPlayer(two);
        buildGame.addServerPlayer(three);
        buildGame.addServerPlayer(four);
        Game game = buildGame.buildGame();
        Assert.assertEquals(4,game.getPlayers().size());
    }

    @Test
    public void testBuildGameException(){
        buildGame.addServerPlayer(one);
        IllegalArgumentException message= Assertions.assertThrows(IllegalArgumentException.class, () -> buildGame.buildGame());
        Assert.assertEquals("java.lang.IllegalArgumentException: Need more players",message.toString());

    }
}
