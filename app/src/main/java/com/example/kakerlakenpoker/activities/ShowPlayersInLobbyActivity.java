package com.example.kakerlakenpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.game.GameServer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShowPlayersInLobbyActivity extends AppCompatActivity {
    ListView currentPlayersInLobby;
    GameClient client;
    GameServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players_in_lobby);
        currentPlayersInLobby = (ListView) findViewById(R.id.ListViewClientsInLobby);
        client = GameClient.getInstance();
        ArrayList<String> arrayList = client.getIpList();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayList);
        //currentPlayersInLobby.setAdapter(arrayAdapter);
    }


}