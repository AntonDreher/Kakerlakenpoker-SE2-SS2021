package com.example.kakerlakenpoker;

import com.example.kakerlakenpoker.player.CollectedDeck;
import com.example.kakerlakenpoker.player.HandDeck;
import com.example.kakerlakenpoker.player.Player;

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
        Assertions.assertEquals("Testi",this.player.getID());
    }

    @Test
    public void testSetName(){
        this.player.setID("Testu");
        Assertions.assertEquals("Testu",this.player.getID());
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
