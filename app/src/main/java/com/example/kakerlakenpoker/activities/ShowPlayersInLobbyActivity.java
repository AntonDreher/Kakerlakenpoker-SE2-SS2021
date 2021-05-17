package com.example.kakerlakenpoker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.network.game.GameClient;

public class ShowPlayersInLobbyActivity extends AppCompatActivity {
    RecyclerView currentPlayersInLobby;
    GameClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players_in_lobby);
        currentPlayersInLobby = (RecyclerView) findViewById(R.id.RecyclerViewCurrentPlayersInLobby);
        currentPlayersInLobby.setLayoutManager(new LinearLayoutManager(this));
        client = GameClient.getInstance();
        PlayersInLobbyRecyclerViewAdapter adapter = new PlayersInLobbyRecyclerViewAdapter(client.getIpList());
        currentPlayersInLobby.setAdapter(adapter);
    }


}