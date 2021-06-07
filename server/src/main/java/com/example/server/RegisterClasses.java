package com.example.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.util.IntArray;
import com.example.server.dto.BaseMessage;
import com.example.server.dto.Lobby;
import com.example.server.dto.clienttomainserver.ClientName;
import com.example.server.dto.clienttomainserver.ClientJoinedRequest;
import com.example.server.dto.clienttomainserver.ExitLobby;
import com.example.server.dto.clienttomainserver.GameServerReadyToConnect;
import com.example.server.dto.clienttomainserver.GetOpenLobbies;
import com.example.server.dto.clienttomainserver.OpenLobby;
import com.example.server.dto.mainservertoclient.ClientJoinedResponse;
import com.example.server.dto.mainservertoclient.ClientsToJoinGameServer;
import com.example.server.dto.mainservertoclient.DestroyLobby;
import com.example.server.dto.mainservertoclient.ExitLobbyResponse;
import com.example.server.dto.mainservertoclient.SendOpenLobbies;
import com.example.server.dto.mainservertoclient.StartUpGameServer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class RegisterClasses {
    private RegisterClasses(){}

    public static void registerClasses(Kryo kryo){
        kryo.register(BaseMessage.class);
        kryo.register(ClientName.class);
        kryo.register(ClientJoinedRequest.class);
        kryo.register(ClientJoinedResponse.class);
        kryo.register(OpenLobby.class);
        kryo.register(Lobby.class);
        kryo.register(GetOpenLobbies.class);
        kryo.register(OpenLobby.class);
        kryo.register(SendOpenLobbies.class);
        kryo.register(ExitLobby.class);
        kryo.register(ExitLobbyResponse.class);
        kryo.register(DestroyLobby.class);
        kryo.register(StartUpGameServer.class);
        kryo.register(GameServerReadyToConnect.class);
        kryo.register(ClientsToJoinGameServer.class);

        kryo.register(Array.class);
        kryo.register(IntArray.class);
        kryo.register(ArrayList.class);
        kryo.register(HashMap.class);
        kryo.register(String.class);
        kryo.register(int[].class);
        kryo.register(Object[].class);

    }
}
