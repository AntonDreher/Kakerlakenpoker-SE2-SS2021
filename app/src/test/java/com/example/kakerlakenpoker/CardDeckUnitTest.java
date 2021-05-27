package com.example.kakerlakenpoker;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.player.HandDeck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import org.junit.jupiter.api.Assertions;


import java.util.ArrayList;

public class CardDeckUnitTest {
    HandDeck handDeck;
    int fledermauscount;
    int fliegecount;
    int rattecount;
    int scorpioncount ;
    int kakerlakecount;
    int kroetecount;
    int spinnecount;
    int stinkwanzecount;

    @Before
    public void setUp(){
       handDeck = new HandDeck();
        this.fledermauscount = 0;
        this.fliegecount = 0;
        this.kakerlakecount = 0;
        this.kroetecount = 0;
        this.rattecount = 0;
        this.scorpioncount = 0;
        this.spinnecount = 0;
        this.stinkwanzecount = 0;
    }

    @After
    public void tearDown(){
        handDeck = null;
        this.fledermauscount = 0;
        this.fliegecount = 0;
        this.kakerlakecount = 0;
        this.kroetecount = 0;
        this.rattecount = 0;
        this.scorpioncount = 0;
        this.spinnecount = 0;
        this.stinkwanzecount = 0;
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
        Card card = new Card(Type.FLIEGE);
        this.handDeck.addCard(new Card(Type.FLIEGE));
        this.handDeck.removeCard(card);
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

    @Test
    public void testSetFledermaus(){
        this.handDeck.setFledermaus(1);
        Assertions.assertEquals(1,this.handDeck.getFledermaus());
    }
    @Test
    public void testGetFledermaus(){
        this.handDeck.setFledermaus(2);
        this.handDeck.setFledermaus(6);
        Assertions.assertEquals(6,this.handDeck.getFledermaus());

    }
    @Test
    public void testSetFliege(){
        this.handDeck.setFliege(1);
        Assertions.assertEquals(1,this.handDeck.getFliege());
    }
    @Test
    public void testGetFiege(){
        this.handDeck.setFliege(2);
        this.handDeck.setFliege(6);
        Assertions.assertEquals(6,this.handDeck.getFliege());
    }
    @Test
    public void testGetRatte(){
        this.handDeck.setRatte(1);
        Assertions.assertEquals(1,this.handDeck.getRatte());
    }
    @Test
    public void testSetRatte(){
        this.handDeck.setRatte(2);
        this.handDeck.setRatte(6);
        Assertions.assertEquals(6,this.handDeck.getRatte());
    }
    @Test
    public void testGetScorpion(){
        this.handDeck.setScorpion(1);
        Assertions.assertEquals(1,this.handDeck.getScorpion());
    }
    @Test
    public void testSetScorpion(){
        this.handDeck.setScorpion(2);
        this.handDeck.setScorpion(6);
        Assertions.assertEquals(6,this.handDeck.getScorpion());
    }
    @Test
    public void testGetKakerlake(){
        this.handDeck.setKakerlake(1);
        Assertions.assertEquals(1,this.handDeck.getKakerlake());
    }
    @Test
    public void testSetKakerlake(){
        this.handDeck.setKakerlake(2);
        this.handDeck.setKakerlake(6);
        Assertions.assertEquals(6,this.handDeck.getKakerlake());
    }
    @Test
    public void testGetKroete(){
        this.handDeck.setKroete(1);
        Assertions.assertEquals(1,this.handDeck.getKroete());
    }
    @Test
    public void testSetKroete(){
        this.handDeck.setKroete(2);
        this.handDeck.setKroete(6);
        Assertions.assertEquals(6,this.handDeck.getKroete());
    }
    @Test
    public void testGetSpinne(){
        this.handDeck.setSpinne(1);
        Assertions.assertEquals(1,this.handDeck.getSpinne());
    }
    @Test
    public void testSetSpinne(){
        this.handDeck.setSpinne(2);
        this.handDeck.setSpinne(6);
        Assertions.assertEquals(6,this.handDeck.getSpinne());
    }
    @Test
    public void testGetStinkwanze(){
        this.handDeck.setStinkwanze(1);
        Assertions.assertEquals(1,this.handDeck.getStinkwanze());
    }
    @Test
    public void testSetStinkwanze(){
        this.handDeck.setStinkwanze(2);
        this.handDeck.setStinkwanze(6);
        Assertions.assertEquals(6,this.handDeck.getStinkwanze());
    }

}
