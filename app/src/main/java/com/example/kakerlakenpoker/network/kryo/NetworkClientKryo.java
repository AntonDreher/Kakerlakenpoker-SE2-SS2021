package com.example.kakerlakenpoker.network.kryo;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.example.kakerlakenpoker.network.Callback;
import com.example.kakerlakenpoker.network.NetworkClient;
import com.example.kakerlakenpoker.network.dto.BaseMessage;

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
        client.connect(5000, host, 54555);

        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (callback != null && object instanceof BaseMessage)
                    callback.callback((BaseMessage) object);
            }
        });
    }

    public void registerCallback(Callback<BaseMessage> callback) {
        this.callback = callback;
    }

    public void sendMessage(BaseMessage message) {
        client.sendTCP(message);
    }

    public Client getClient() {
        return client;
    }
}