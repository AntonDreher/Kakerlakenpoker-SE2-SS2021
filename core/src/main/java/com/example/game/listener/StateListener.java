package com.example.game.listener;

abstract public class StateListener {

    public void stateChanged(){
        inform();
    }

    abstract public void inform();
}
