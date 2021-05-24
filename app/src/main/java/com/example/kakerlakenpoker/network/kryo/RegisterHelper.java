package com.example.kakerlakenpoker.network.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.util.IntArray;
import com.example.kakerlakenpoker.card.GameDeck;
import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.dto.ClientJoined;
import com.example.kakerlakenpoker.network.dto.ClientsInLobby;
import com.example.kakerlakenpoker.network.dto.Lobby;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.GetOpenLobbies;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.OpenLobby;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.SendOpenLobbies;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.game.GameServer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RegisterHelper {
    private RegisterHelper(){}

    public static void registerClasses(Kryo kryo){

        kryo.register(BaseMessage.class);
        kryo.register(ClientJoined.class);
        kryo.register(ClientsInLobby.class);
        kryo.register(OpenLobby.class);
        kryo.register(Lobby.class);
        kryo.register(GetOpenLobbies.class);
        kryo.register(OpenLobby.class);
        kryo.register(GameClient.class);
        kryo.register(GameDeck.class);
        kryo.register(GameServer.class);
        kryo.register(SendOpenLobbies.class);

        kryo.register(Array.class);
        kryo.register(IntArray.class);
        kryo.register(ArrayList.class);
        kryo.register(String.class);
        kryo.register(int[].class);
        kryo.register(Object[].class);

    }
}
