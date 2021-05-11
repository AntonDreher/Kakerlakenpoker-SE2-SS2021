package com.example.kakerlakenpoker;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.player.HandDeck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class HandDeckUnitTest {

    HandDeck hand;
    ArrayList<Card> list = new ArrayList<>();

    @Before
    public void setUp(){
        this.hand = new HandDeck();

    }
    @After
    public void tearDown(){
        this.hand = null;
    }

    @Test
    public void testUseCard(){
        this.hand.addCard(new Card(Type.FLIEGE));
        this.hand.addCard(new Card(Type.KROETE));
        this.hand.addCard(new Card(Type.FLIEGE));
        this.hand.addCard(new Card(Type.SCORPION));
        this.hand.addCard(new Card(Type.STINKWANZE));
        this.hand.addCard(new Card(Type.FLEDERMAUS));

        hand.useCard(new Card(Type.FLEDERMAUS));

        Assertions.assertEquals(false, hand.getDeck().contains(new Card(Type.FLEDERMAUS)));

    }

    @Test
    public void testDisplayCard(){

        this.hand.setDeck(list);

        this.hand.addCard(new Card(Type.FLIEGE));
        this.hand.addCard(new Card(Type.KROETE));
        this.hand.addCard(new Card(Type.FLIEGE));
        this.hand.addCard(new Card(Type.SCORPION));
        this.hand.addCard(new Card(Type.STINKWANZE));
        this.hand.addCard(new Card(Type.FLEDERMAUS));

        hand.displayCard();

        Assertions.assertEquals(2, hand.getFliege());

    }


}
