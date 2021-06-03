package com.example.kakerlakenpoker;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.player.CollectedDeck;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CollectedDeckUnitTest {
    CollectedDeck collectedDeck;

    @Before
    public void setup() {
        this.collectedDeck = new CollectedDeck();

    }

    @After
    public void teardown() {
        collectedDeck = null;
    }


    //lostGame methode tests

    @Test
    public void testLostGameFalse() {
        collectedDeck.addCard(new Card(Type.FLEDERMAUS));
        Assert.assertFalse(collectedDeck.hasLost());
    }

    @Test
    public void testLostGameSimilarCards() {
        collectedDeck.addCard(new Card(Type.FLEDERMAUS));
        collectedDeck.addCard(new Card(Type.FLEDERMAUS));
        collectedDeck.addCard(new Card(Type.FLEDERMAUS));
        collectedDeck.addCard(new Card(Type.FLEDERMAUS));
        Assert.assertTrue(collectedDeck.hasLost());
    }

    @Test
    public void testLostGameOneEach() {
        collectedDeck.addCard(new Card(Type.FLEDERMAUS));
        collectedDeck.addCard(new Card(Type.FLIEGE));
        collectedDeck.addCard(new Card(Type.RATTE));
        collectedDeck.addCard(new Card(Type.SCORPION));
        collectedDeck.addCard(new Card(Type.KAKERLAKE));
        collectedDeck.addCard(new Card(Type.KROETE));
        collectedDeck.addCard(new Card(Type.SPINNE));
        collectedDeck.addCard(new Card(Type.STINKWANZE));
        Assert.assertTrue(collectedDeck.hasLost());
    }
}
