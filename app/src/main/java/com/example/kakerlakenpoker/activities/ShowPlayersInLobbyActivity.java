package com.example.kakerlakenpoker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.game.GameServer;
import java.util.ArrayList;

public class ShowPlayersInLobbyActivity extends AppCompatActivity {
    RecyclerView currentPlayersInLobby;
    GameClient client;
    GameServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players_in_lobby);
        currentPlayersInLobby = (RecyclerView) findViewById(R.id.RecyclerViewCurrentPlayersInLobby);
        client = GameClient.getInstance();
        PlayersInLobbyRecyclerViewAdapter adapter = new PlayersInLobbyRecyclerViewAdapter(client.getIpList());
        currentPlayersInLobby.setAdapter(adapter);
    }


}