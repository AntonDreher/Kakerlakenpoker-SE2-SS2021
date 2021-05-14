package com.example.kakerlakenpoker;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.GameDeck;
import com.example.kakerlakenpoker.card.Type;

import org.junit.Test;
import org.junit.After;

import org.junit.jupiter.api.Assertions;

public class GameDeckUnitTest {
    GameDeck gameDeck;


    @After
    public void tearDown(){
        this.gameDeck = null;
    }
    @Test
    public void testCreateDeck(){
        gameDeck = new GameDeck();
        Assertions.assertEquals(64,this.gameDeck.size());
    }
    @Test
    public void testAddCardException(){
        gameDeck = new GameDeck();
        IllegalArgumentException message= Assertions.assertThrows(IllegalArgumentException.class, () -> this.gameDeck.addCard(new Card(Type.KAKERLAKE)));
        Assertions.assertEquals("java.lang.IllegalArgumentException: Cannot add",message.toString());
    }

}
