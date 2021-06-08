package com.example.kakerlakenpoker;


import com.example.game.card.GameDeck;
import com.example.game.BuildGame;
import com.example.game.Game;
import com.example.game.player.CollectedDeck;
import com.example.game.player.HandDeck;
import com.example.game.player.Player;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class BuildGameUnitTest {
    int one;
    int two;
    int three;
    int four;
    BuildGame buildGame;
    ArrayList<Player> list;
    GameDeck gameDeck;
    @Before
    public void setUp(){
        one = 1;
        two = 2;
        three = 3;
        four = 4;
        buildGame = new BuildGame();
        list = new ArrayList<>();
        gameDeck = new GameDeck();
    }

    @After
    public void tearDown() {
        buildGame = null;
        list = null;
        gameDeck = null;
    }
    @Test
    public void testAddServerPlayer(){
        buildGame.addServerPlayer(one);
        Assert.assertEquals("one",buildGame.getPlayers().get(0).getId());

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
    @Test
    public void testsetPlayer(){
        list.add(new Player(1,new HandDeck(),new CollectedDeck()));
        buildGame.setPlayers(list);
        Assert.assertEquals(1,buildGame.getPlayers().size());
    }
}
