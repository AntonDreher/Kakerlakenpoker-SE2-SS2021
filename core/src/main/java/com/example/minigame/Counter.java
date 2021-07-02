package com.example.minigame;

public class Counter {
    int winCounter;
    int lossCounter;
    int drawCounter;
    int roundCounter;
    int winOutOf5Counter;

    // holds all counts of turns
    public Counter() {
        this.winCounter = 0;
        this.lossCounter = 0;
        this.drawCounter = 0;
        this.winOutOf5Counter = 0;
    }

    public int getWinCounter() {
        return winCounter;
    }

    public void setWinCounter(int winCounter) {
        this.winCounter = winCounter;
    }

    public int getLossCounter() {
        return lossCounter;
    }

    public void setLossCounter(int lossCounter) {
        this.lossCounter = lossCounter;
    }

    public int getDrawCounter() {
        return drawCounter;
    }

    public void setDrawCounter(int drawCounter) {
        this.drawCounter = drawCounter;
    }

    public int getRoundCounter() { return roundCounter; }

    public void setRoundCounter(int roundCounter) { this.roundCounter = roundCounter; }

    public int getWinOutOf5Counter() { return winOutOf5Counter; }

    public void setWinOutOf5Counter(int winOutOf5Counter) { this.winOutOf5Counter = winOutOf5Counter; }
}
