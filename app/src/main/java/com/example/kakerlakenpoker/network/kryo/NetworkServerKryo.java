package com.example.kakerlakenpoker.network.kryo;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.example.kakerlakenpoker.network.Callback;
import com.example.kakerlakenpoker.network.NetworkServer;
import com.example.kakerlakenpoker.network.dto.BaseMessage;

import java.io.IOException;

public class NetworkServerKryo implements NetworkServer, KryoNetComponent {
    private Server server;
    private Callback<BaseMessage> messageCallback;

    public NetworkServerKryo() {
        server = new Server();
        this.registerClasses();
    }

    public void registerClass(Class c) {
        server.getKryo().register(c);
    }

    public void start() throws IOException {
        server.start();
        server.bind(NetworkConstants.TCP_PORT);

        /*server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (messageCallback != null && object instanceof BaseMessage)
                    messageCallback.callback((BaseMessage) object);
            }
        });*/
    }


    public void registerCallback(Callback<BaseMessage> callback) {
        this.messageCallback = callback;
    }

    public void broadcastMessage(BaseMessage message) {
        for (Connection connection : server.getConnections()) {
            connection.sendTCP(message);
        }
    }

    private void registerClasses(){
        this.registerClass(BaseMessage.class);
    }

    public Connection[] getConnections() {
        return server.getConnections();
    }

    public Server getServer() {
        return server;
    }
}