package com.example.kakerlakenpoker.server;

import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.kryo.NetworkServerKryo;

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
