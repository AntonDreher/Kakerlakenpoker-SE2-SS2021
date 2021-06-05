package com.example.kakerlakenpoker.activities;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.widget.ListView;

import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.IpListAdapter;
import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.dto.ClientJoinedRequest;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ShowPlayersInLobbyActivity extends AppCompatActivity {
    private ListView currentPlayersInLobby;
    private FloatingActionButton floatingActionButton;
    private GameClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.info("ShowPlayersInLobby started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players_in_lobby);
        client = GameClient.getInstance();
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
            Log.info(e.getMessage());
        }

    }


}