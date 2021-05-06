package com.example.kakerlakenpoker;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.player.HandDeck;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class CardDeckUnitTest {
    HandDeck handDeck;

    @BeforeEach
    public void setUp(){
        this.handDeck = new HandDeck();
    }

    @AfterEach
    public void tearDown(){
        this.handDeck = null;
    }
    @Test
    public void testSetDeckAndGetDeck(){
        ArrayList<Card> list = new ArrayList<>();
        this.handDeck.setDeck(list);
        Assertions.assertEquals(list,this.handDeck.getDeck());
    }

    @Test
    public void testAddCard(){
        this.handDeck.addCard(new Card(Type.FLIEGE));
        Assertions.assertEquals(Type.FLIEGE,this.handDeck.getDeck().get(0).getType());
    }

    @Test
    public void testSize(){
        this.handDeck.addCard(new Card(Type.FLIEGE));
        this.handDeck.addCard(new Card(Type.KROETE));
        this.handDeck.addCard(new Card(Type.KAKERLAKE));
        Assertions.assertEquals(3,this.handDeck.size());
    }

    @Test
    public void testRemoveCard(){
        this.handDeck.addCard(new Card(Type.FLIEGE));
        this.handDeck.removeCard(Type.FLIEGE);
        Assertions.assertEquals(0,this.handDeck.size());
    }

    @Test
    public void findCardFound(){
        Card search = new Card(Type.FLIEGE);
        this.handDeck.addCard(search);
        this.handDeck.addCard(new Card(Type.KROETE));
        Assertions.assertEquals(search,this.handDeck.findCard(Type.FLIEGE));
    }

    @Test
    public void findCardNotFound(){
        this.handDeck.addCard(new Card(Type.KROETE));
        Assertions.assertNull(this.handDeck.findCard(Type.KAKERLAKE));
    }
}
