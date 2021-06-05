package com.example.kakerlakenpoker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.IpListAdapter;
import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.dto.ClientJoinedRequest;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import androidx.appcompat.app.AppCompatActivity;

public class ShowPlayersInLobbyActivity extends AppCompatActivity implements Observer {
    private ListView currentPlayersInLobby;
    private FloatingActionButton exitLobbyBtn;
    private GameClient client;
    private Intent intent;
    private Handler mHanlder = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.info("ShowPlayersInLobby started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players_in_lobby);
        exitLobbyBtn = findViewById(R.id.exitLobbyBtn);
        exitLobbyBtn.setOnClickListener((View view)->exitLobby());
        client = GameClient.getInstance();
        client.addObserver(this);
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

    private void exitLobby(){
        GameClient.getInstance().exitLobby();
        intent = new Intent(ShowPlayersInLobbyActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void update(Observable observable, final Object data){
        if (observable instanceof GameClient){
            mHanlder.post(new Runnable(){
               @Override
               public void run(){
                   intent = new Intent(ShowPlayersInLobbyActivity.this, MainMenuActivity.class);
                   startActivity(intent);
               }
            });
        }
    }

}