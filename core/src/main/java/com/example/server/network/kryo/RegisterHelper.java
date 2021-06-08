package com.example.server.network.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.util.IntArray;
import com.example.game.card.Card;
import com.example.game.card.GameDeck;
import com.example.game.card.Type;
import com.example.game.Decision;
import com.example.game.GameState;
import com.example.game.Turn;
import com.example.server.dto.Lobby;
import com.example.server.dto.clienttomainserver.ClientName;
import com.example.server.dto.clienttomainserver.ClientJoinedRequest;
import com.example.server.dto.clienttomainserver.GameServerReadyToConnect;
import com.example.server.dto.mainservertoclient.ClientJoinedResponse;
import com.example.server.dto.mainservertoclient.ClientsToJoinGameServer;
import com.example.server.dto.mainservertoclient.DestroyLobby;
import com.example.server.dto.clienttomainserver.ExitLobby;
import com.example.server.dto.mainservertoclient.ExitLobbyResponse;
import com.example.server.network.dto.gameservertoclient.GameOver;
import com.example.server.network.dto.gameservertoclient.GameUpdate;
import com.example.server.network.dto.gameservertoclient.InitGame;
import com.example.server.dto.BaseMessage;
import com.example.server.network.dto.clienttogameserver.MakeDecision;
import com.example.server.network.dto.clienttogameserver.MakeTurn;
import com.example.server.network.dto.PlayerReady;
import com.example.server.dto.clienttomainserver.GetOpenLobbies;
import com.example.server.dto.clienttomainserver.OpenLobby;
import com.example.server.dto.mainservertoclient.SendOpenLobbies;
import com.example.server.dto.mainservertoclient.StartUpGameServer;
import com.example.server.network.game.GameData;
import com.example.game.player.CollectedDeck;
import com.example.game.player.HandDeck;
import com.example.game.player.Player;
import com.example.game.player.PlayerState;

import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class RegisterHelper {
    private RegisterHelper(){}

    public static void registerClasses(Kryo kryo){
        kryo.register(BaseMessage.class);
        kryo.register(ClientName.class);
        kryo.register(InetSocketAddress.class);
        kryo.register(ClientJoinedRequest.class);
        kryo.register(ClientJoinedResponse.class);
        kryo.register(OpenLobby.class);
        kryo.register(Lobby.class);
        kryo.register(GetOpenLobbies.class);
        kryo.register(OpenLobby.class);
        kryo.register(GameDeck.class);
        kryo.register(GameData.class);
        kryo.register(SendOpenLobbies.class);
        kryo.register(PlayerReady.class);
        kryo.register(Player.class);
        kryo.register(GameUpdate.class);
        kryo.register(MakeTurn.class);
        kryo.register(MakeDecision.class);
        kryo.register(PlayerState.class);
        kryo.register(HandDeck.class);
        kryo.register(CollectedDeck.class);
        kryo.register(Card.class);
        kryo.register(Type.class);
        kryo.register(Decision.class);
        kryo.register(GameState.class);
        kryo.register(Turn.class);
        kryo.register(InitGame.class);
        kryo.register(GameOver.class);
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
