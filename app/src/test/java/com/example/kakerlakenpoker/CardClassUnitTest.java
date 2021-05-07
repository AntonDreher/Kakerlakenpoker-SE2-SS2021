package com.example.kakerlakenpoker;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class CardClassUnitTest {
    private Card one;

    @BeforeEach
    public void setUp(){
        this.one = new Card(Type.KROETE);
    }
    @AfterEach
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
