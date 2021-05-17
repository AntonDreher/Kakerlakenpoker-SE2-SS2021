package com.example.kakerlakenpoker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.dto.ClientJoined;
import com.example.kakerlakenpoker.network.dto.Lobby;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.OpenLobby;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.game.GameServer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateLobbyActivity extends AppCompatActivity {
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
        startBtn.setOnClickListener((View view)-> {
            try {
                startLobby();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    public void goBack(){
        intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void startLobby() throws InterruptedException {
        Thread startServer =
        new Thread(() -> {
            server = GameServer.getInstance();
            server.init(NetworkUtils.getIpAddressFromDevice(getApplicationContext()));
        });
        startServer.start();
        startServer.join();

        Thread connectClient = new Thread(() -> {
            Lobby lobby = new Lobby(inputLobbyName.getText().toString(),NetworkUtils.getIpAddressFromDevice(getApplicationContext()));
            GameClient client = GameClient.getInstance();
            client.getClient().sendMessage(new OpenLobby(lobby));
            client.connect("localhost");
            client.getClient().sendMessage(new ClientJoined(NetworkUtils.getIpAddressFromDevice(getApplicationContext())));
        });

        connectClient.start();
        connectClient.join();
        intent = new Intent(this, ShowPlayersInLobbyActivity.class);
        startActivity(intent);
    }

}
