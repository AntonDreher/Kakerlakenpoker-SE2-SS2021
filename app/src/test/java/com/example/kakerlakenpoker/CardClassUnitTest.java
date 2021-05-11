package com.example.kakerlakenpoker;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class CardClassUnitTest {
    private Card one;

    @Before
    public void setUp(){
        this.one = new Card(Type.KROETE);
    }
    @After
    public void tearDown(){
        this.one = null;
    }

    @Test
    public void testGetType(){
        Assertions.assertEquals(Type.KROETE,this.one.getType());
    }

    @Test
    public void testSetType(){
        this.one.setType(Type.FLEDERMAUS);
        Assertions.assertEquals(Type.FLEDERMAUS,this.one.getType());
    }
}
