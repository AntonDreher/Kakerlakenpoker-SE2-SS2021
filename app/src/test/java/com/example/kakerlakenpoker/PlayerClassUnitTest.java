package com.example.kakerlakenpoker;

import com.example.kakerlakenpoker.game.player.CollectedDeck;
import com.example.kakerlakenpoker.game.player.HandDeck;
import com.example.kakerlakenpoker.game.player.Player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PlayerClassUnitTest {
    HandDeck handDeck;
    CollectedDeck collectedDeck;
    Player player;

    @Before
    public void setUp(){
       this.handDeck = new HandDeck();
        this.collectedDeck = new CollectedDeck();
        this.player = new Player("Testi",this.handDeck,this.collectedDeck);
    }
    @After
    public void tearDown(){
        this.handDeck = null;
        this.collectedDeck = null;
        this.player = null;
    }

    @Test
    public void testGetName(){
        Assertions.assertEquals("Testi",this.player.getName());
    }

    @Test
    public void testSetName(){
        this.player.setName("Testu");
        Assertions.assertEquals("Testu",this.player.getName());
    }

    @Test
    public void testGetHandDeck(){
        Assertions.assertEquals(handDeck,this.player.getHandDeck());
    }
    @Test
    public void testSetHandDeck(){
        HandDeck newHandDeck = new HandDeck();
        player.setHandDeck(newHandDeck);
        Assertions.assertEquals(newHandDeck,player.getHandDeck());
    }
    @Test
    public void testGetCollectedDeck(){
        Assertions.assertEquals(collectedDeck,this.player.getCollectedDeck());
    }
    @Test
    public void testSetCollectedDeck(){
        CollectedDeck newCollDeck = new CollectedDeck();
        player.setCollectedDeck(newCollDeck);
        Assertions.assertEquals(newCollDeck,this.player.getCollectedDeck());
    }


}
