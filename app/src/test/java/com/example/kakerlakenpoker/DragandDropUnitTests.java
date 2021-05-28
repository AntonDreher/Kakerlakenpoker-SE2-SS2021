package com.example.kakerlakenpoker;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.player.HandDeck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class DragandDropUnitTests {


    //Test folgen, nachdem die visuelle Betrachtung der View passt und die Funktionalität bestätigt wurde.
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
    public void testremoveCardWithDragandDrop(){

        this.hand.setDeck(list);

        this.hand.addCard(new Card(Type.FLIEGE));
        this.hand.addCard(new Card(Type.KROETE));
        this.hand.addCard(new Card(Type.FLIEGE));
        this.hand.addCard(new Card(Type.SCORPION));
        this.hand.addCard(new Card(Type.STINKWANZE));
        this.hand.addCard(new Card(Type.FLEDERMAUS));

        hand.displayCard();


    }
}