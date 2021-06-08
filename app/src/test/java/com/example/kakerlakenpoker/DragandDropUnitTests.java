package com.example.kakerlakenpoker;

import com.example.game.card.Card;
import com.example.game.card.Type;
import com.example.game.player.HandDeck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DragandDropUnitTests {


    //Test folgen, nachdem die visuelle Betrachtung der View passt (Nikita).
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

        this.hand.addCard(new Card(Type.FLIEGE));
        this.hand.addCard(new Card(Type.KROETE));
        this.hand.addCard(new Card(Type.FLIEGE));
        this.hand.addCard(new Card(Type.SCORPION));
        this.hand.addCard(new Card(Type.STINKWANZE));
        this.hand.addCard(new Card(Type.FLEDERMAUS));



    }
}
