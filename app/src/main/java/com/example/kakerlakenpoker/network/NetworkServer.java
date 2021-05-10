package com.example.kakerlakenpoker.network;

import com.example.kakerlakenpoker.network.dto.BaseMessage;

import java.io.IOException;

public interface NetworkServer {

    /**
     * Starts the Server.
     *
     * @throws IOException
     */
    void start() throws IOException;

    /**
     * Registers a callback which gets called if a message is received.
     *
     * @param callback
     */
    void registerCallback(Callback<BaseMessage> callback);

    /**
     * Sends a message to all clients.
     *
     * @param message
     */
    void broadcastMessage(BaseMessage message);

}