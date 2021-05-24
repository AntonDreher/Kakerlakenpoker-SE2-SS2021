package com.example.kakerlakenpoker.activities;

import android.os.Bundle;
import android.widget.ListView;

import com.example.kakerlakenpoker.IpListAdapter;
import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.network.game.GameClient;

import androidx.appcompat.app.AppCompatActivity;

public class ShowPlayersInLobbyActivity extends AppCompatActivity {
    ListView currentPlayersInLobby;
    GameClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players_in_lobby);
        client = GameClient.getInstance();
        IpListAdapter listAdapter = new IpListAdapter(this, client.getIpList());
        currentPlayersInLobby = findViewById(R.id.ListViewCurrentPlayersInLobby);
        currentPlayersInLobby.setAdapter(listAdapter);
    }


}