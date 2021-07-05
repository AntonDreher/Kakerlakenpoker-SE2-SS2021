package com.example.server.dto.mainservertoclient;

public class RandomNumberResponse {
    //Random number from server for minigame
    int randomNumber;

    public RandomNumberResponse(int randomNumber){ this.randomNumber = randomNumber; }

    public RandomNumberResponse(){}

    public int getRandomNumber() { return randomNumber; }
}
