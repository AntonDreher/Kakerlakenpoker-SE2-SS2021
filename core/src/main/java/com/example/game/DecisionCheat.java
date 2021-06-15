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


    public int getSecond() {
        return second;
    }



}