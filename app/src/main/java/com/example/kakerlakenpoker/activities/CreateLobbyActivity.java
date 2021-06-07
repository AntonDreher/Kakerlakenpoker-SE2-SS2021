package com.example.kakerlakenpoker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.R;

import com.example.server.dto.Lobby;
import com.example.server.dto.clienttomainserver.OpenLobby;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.game.GameServer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateLobbyActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private TextView inputLobbyName;
    private Button startBtn;
    private Intent intent;
    private GameServer server;
    private GameClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_lobbyname);
        inputLobbyName = findViewById(R.id.editTextTextPersonName2);
        floatingActionButton = findViewById(R.id.floatingActionButtonEnterName);
        floatingActionButton.setOnClickListener((View view)->goBack());
        startBtn = findViewById(R.id.startLobbyBtn);
        startBtn.setOnClickListener((View view)-> {
            try {
                startLobby();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Log.info(e.getMessage());
            }
        });

    }

    public void goBack(){
        intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void startLobby() throws InterruptedException {
        Thread connectClient = new Thread(() -> {
            Lobby lobby = new Lobby(inputLobbyName.getText().toString());
            client = GameClient.getInstance();
            client.getClient().sendMessage(new OpenLobby(lobby));
            client.setCurrentLobby(lobby);
        });

        connectClient.start();
        connectClient.join();
        intent = new Intent(this, ShowPlayersInLobbyActivity.class);
        startActivity(intent);
    }

}
