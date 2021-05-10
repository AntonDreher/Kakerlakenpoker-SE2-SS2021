package com.example.kakerlakenpoker;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.game.GameServer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class EnterLobbyNameActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    TextView inputLobbyName;
    Button startBtn;
    Intent intent;
    GameServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_lobbyname);
        inputLobbyName = findViewById(R.id.editTextTextPersonName2);
        floatingActionButton = findViewById(R.id.floatingActionButtonEnterName);
        floatingActionButton.setOnClickListener((View view)->goBack());
        startBtn = findViewById(R.id.startLobbyBtn);
        startBtn.setOnClickListener((View view)->startLobby());

        String ip = NetworkUtils.getIpAddressFromDevice(getApplicationContext());
        inputLobbyName.setText(ip);
    }

    public void goBack(){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startLobby() {
        new Thread(() -> {
            server = GameServer.getInstance();
            server.init(NetworkUtils.getIpAddressFromDevice(getApplicationContext()));
        }).start();

        new Thread(() -> {
            GameClient client = GameClient.getInstance();
            client.init("localhost", NetworkUtils.getIpAddressFromDevice(getApplicationContext()));
        }).start();

        intent = new Intent(this, ShowPlayersInLobbyActivity.class);
        startActivity(intent);
    }

}
