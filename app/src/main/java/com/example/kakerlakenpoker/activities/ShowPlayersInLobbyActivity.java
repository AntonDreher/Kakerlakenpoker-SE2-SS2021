package com.example.kakerlakenpoker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.esotericsoftware.minlog.Log;
import com.example.game.Game;
import com.example.kakerlakenpoker.R;
import com.example.server.network.NetworkUtils;
import com.example.server.dto.clienttomainserver.ClientJoinedRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ShowPlayersInLobbyActivity extends AppCompatActivity {
    private ListView currentPlayersInLobby;
    private FloatingActionButton exitLobbyBtn;
    private GameClient client;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.info("ShowPlayersInLobby started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players_in_lobby);
        exitLobbyBtn = findViewById(R.id.exitLobbyBtn);
        exitLobbyBtn.setOnClickListener((View view) -> exitLobby());
        client = GameClient.getInstance();
        client.setActivity(this);
        IpListAdapter listAdapter = new IpListAdapter(this, new ArrayList<String>());
        currentPlayersInLobby = findViewById(R.id.ListViewCurrentPlayersInLobby);
        currentPlayersInLobby.setAdapter(listAdapter);
        client.setListAdapter(listAdapter);
        Thread clientJoined = new Thread(() ->
                client.getClient().sendMessage(new ClientJoinedRequest(client.getCurrentLobby().getName(), NetworkUtils.getIpAddressFromDevice()))
        );
        clientJoined.start();
        try {
            clientJoined.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.info(e.getMessage());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        GameClient.getInstance().setActivity(null);
    }

    private void exitLobby() {
        GameClient.getInstance().exitLobby();
        intent = new Intent(ShowPlayersInLobbyActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void showGame() {
        Intent intent = new Intent(this, PlayerIngameMainActivity.class);
        startActivity(intent);
    }


}