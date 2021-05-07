package com.example.kakerlakenpoker;

import com.example.kakerlakenpoker.player.CollectedDeck;
import com.example.kakerlakenpoker.player.HandDeck;
import com.example.kakerlakenpoker.player.Player;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerClassUnitTest {
    HandDeck handDeck;
    CollectedDeck collectedDeck;
    Player player;

    @BeforeEach
    public void setUp(){
       this.handDeck = new HandDeck();
        this.collectedDeck = new CollectedDeck();
        this.player = new Player("Testi",this.handDeck,this.collectedDeck);
    }
    @AfterEach
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
