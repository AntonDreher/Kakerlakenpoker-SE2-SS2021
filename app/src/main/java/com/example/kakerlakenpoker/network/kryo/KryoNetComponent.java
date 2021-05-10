package com.example.kakerlakenpoker.network.kryo;

public interface KryoNetComponent {

    /**
     * Register a class for serialization.
     *
     * @param c
     */
    void registerClass(Class c);

}