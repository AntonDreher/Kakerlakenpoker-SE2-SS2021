package com.example.game;

public class DecisionCheat {
    int first;
    int second;

    public void createCalc(){
        first = (int)(Math.random() * 99) + 1;
        second = (int)(Math.random() * 99) + 1;
    }

    public boolean checkCalc(int check){
        int sum = first + second;
        return sum == check;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

}