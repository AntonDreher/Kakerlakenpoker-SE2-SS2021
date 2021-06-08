package com.example.server;

import com.esotericsoftware.minlog.Log;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            MainServer server = new MainServer();
        } catch (IOException e) {
            Log.trace("Starting the mainserver failed.", e);
            System.exit(1);
        }
    }
}
