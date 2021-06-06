package com.example.kakerlakenpoker.network.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.util.IntArray;
import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.GameDeck;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.game.Decision;
import com.example.kakerlakenpoker.game.GameState;
import com.example.kakerlakenpoker.game.Turn;
import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.ClientJoinedRequest;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.ClientJoinedResponse;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.DestroyLobby;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.ExitLobby;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.ExitLobbyResponse;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.GameOver;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.GameUpdate;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.InitGame;
import com.example.kakerlakenpoker.network.dto.Lobby;
import com.example.kakerlakenpoker.network.dto.clienttogameserver.MakeDecision;
import com.example.kakerlakenpoker.network.dto.clienttogameserver.MakeTurn;
import com.example.kakerlakenpoker.network.dto.PlayerReady;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.GetOpenLobbies;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.OpenLobby;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.SendOpenLobbies;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.StartUpGameServer;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.game.GameServer;
import com.example.kakerlakenpoker.player.CollectedDeck;
import com.example.kakerlakenpoker.player.HandDeck;
import com.example.kakerlakenpoker.player.Player;
import com.example.kakerlakenpoker.player.PlayerState;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RegisterHelper {
    private RegisterHelper(){}

    public static void registerClasses(Kryo kryo){
        kryo.register(BaseMessage.class);
        kryo.register(ClientJoinedRequest.class);
        kryo.register(ClientJoinedResponse.class);
        kryo.register(OpenLobby.class);
        kryo.register(Lobby.class);
        kryo.register(GetOpenLobbies.class);
        kryo.register(OpenLobby.class);
        kryo.register(GameClient.class);
        kryo.register(GameDeck.class);
        kryo.register(GameServer.class);
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

        kryo.register(Array.class);
        kryo.register(IntArray.class);
        kryo.register(ArrayList.class);
        kryo.register(String.class);
        kryo.register(int[].class);
        kryo.register(Object[].class);

    }
}
