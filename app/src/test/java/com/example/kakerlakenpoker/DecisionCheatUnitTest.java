package com.example.kakerlakenpoker;

import com.example.game.DecisionCheat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DecisionCheatUnitTest {
    DecisionCheat decisionCheat;
    @Before
    public void setUp(){
         decisionCheat = new DecisionCheat();
    }

    @After
    public void tearDown(){
        decisionCheat = null;
    }

    @Test
    public void testCreateCalc(){
        int high = 100;
        int low = 1;
        decisionCheat.createCalc();
        Assert.assertTrue(high >= decisionCheat.getFirst() && high >= decisionCheat.getSecond());
        Assert.assertTrue(low <= decisionCheat.getFirst() && low <= decisionCheat.getSecond());
    }

    @Test
    public void checkTestCalcTrue(){
        decisionCheat.createCalc();
        int check = decisionCheat.getFirst() + decisionCheat.getSecond();
        Assert.assertTrue(decisionCheat.checkCalc(check));
    }

    @Test
    public void checkTestCalcFalse(){
        decisionCheat.createCalc();
        Assert.assertFalse(decisionCheat.checkCalc(1000));
    }

}
