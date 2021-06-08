package com.example.server.network;

/**
 * Used for callbacks.
 */
public interface Callback<T> {

    void callback(T argument);

}