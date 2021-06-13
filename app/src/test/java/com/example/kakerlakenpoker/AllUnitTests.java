package com.example.kakerlakenpoker;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BuildGameUnitTest.class, CardClassUnitTest.class, CardDeckUnitTest.class, CollectedDeckUnitTest.class,
        GameDeckUnitTest.class, HandDeckUnitTest.class, PlayerClassUnitTest.class})
public final class AllUnitTests {
}
