package com.example.minigame;

public class Counter {
    int winCounter;
    int lossCounter;
    int drawCounter;
    int winOutOf5;

    // holds all counts of turns
    public Counter() {
        this.winCounter = 0;
        this.lossCounter = 0;
        this.drawCounter = 0;
        this.winOutOf5 = 0;
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

    public int getWinOutOf5() {
        return winOutOf5;
    }

    public void setWinOutOf5(int winOutOf5) {
        this.winOutOf5 = winOutOf5;
    }
}
