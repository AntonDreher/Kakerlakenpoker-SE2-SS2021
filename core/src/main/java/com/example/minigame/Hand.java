package com.example.minigame;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    ArrayList<String> options;
    String play;

    // Hand options in RPSLS
    public Hand(String play){
        this.play = play;
        this.options = new ArrayList<>();
        options.add("Rock");
        options.add("Paper");
        options.add("Scissors");
        options.add("Lizard");
        options.add("Spock");
    }

    // Players hand
    public String getPlay() {
        return play;
    }

    // Computers hand
    public String computerPlay(){
        Collections.shuffle(options);
        return options.get(0);
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public ArrayList<String> getOptions() {
        return options;
    }
}
