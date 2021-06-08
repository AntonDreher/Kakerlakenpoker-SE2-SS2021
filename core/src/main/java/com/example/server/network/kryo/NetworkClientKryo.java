package com.example.server.network.kryo;

import com.esotericsoftware.kryonet.Client;
import com.example.server.network.Callback;
import com.example.server.network.NetworkClient;
import com.example.server.NetworkConstants;
import com.example.server.dto.BaseMessage;

import java.io.IOException;

public class NetworkClientKryo implements NetworkClient, KryoNetComponent {
    private Client client;
    private Callback<BaseMessage> callback;

    public NetworkClientKryo() {
        client = new Client();
        client.start();
    }

    public void registerClass(Class c) {
        client.getKryo().register(c);
    }

    public void connect(String host) throws IOException {
        client.connect(5000, host, NetworkConstants.TCP_PORT);
    }

    public void registerCallback(Callback<BaseMessage> callback) {
        this.callback = callback;
    }

    public void sendMessage(final BaseMessage message) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                client.sendTCP(message);
            }
        };
        thread.start();

    }

    public Client getClient() {
        return client;
    }
}