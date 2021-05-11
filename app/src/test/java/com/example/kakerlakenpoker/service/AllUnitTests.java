package com.example.kakerlakenpoker.service;

import com.example.kakerlakenpoker.BuildGameUnitTest;
import com.example.kakerlakenpoker.CardClassUnitTest;
import com.example.kakerlakenpoker.CardDeckUnitTest;
import com.example.kakerlakenpoker.CollectedDeckUnitTest;
import com.example.kakerlakenpoker.GameDeckUnitTest;
import com.example.kakerlakenpoker.HandDeckUnitTest;
import com.example.kakerlakenpoker.PlayerClassUnitTest;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BuildGameUnitTest.class, CardClassUnitTest.class, CardDeckUnitTest.class, CollectedDeckUnitTest.class,
        GameDeckUnitTest.class, HandDeckUnitTest.class, PlayerClassUnitTest.class})
public final class AllUnitTests {
}
