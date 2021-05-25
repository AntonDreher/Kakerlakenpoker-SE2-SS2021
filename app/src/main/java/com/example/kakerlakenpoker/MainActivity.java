package com.example.kakerlakenpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.GetOpenLobbies;
import com.example.kakerlakenpoker.network.game.GameClient;

public class MainActivity extends AppCompatActivity {
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startview);

        Button createLobbyButton = (Button) findViewById(R.id.create_lobby_button);
        createLobbyButton.setOnClickListener((View view)-> createLobby());


        Button searchLobbyButton = (Button) findViewById(R.id.search_button);
        searchLobbyButton.setOnClickListener((View view)-> searchLobby());




    }

    public void searchLobby() {
        //GameClient.getInstance().getClient().sendMessage(new GetOpenLobbies());
        intent = new Intent(MainActivity.this, SearchLobbyActivity.class);
        startActivity(intent);
    }

    public void createLobby() {
        new Thread(() -> {
            GameClient client = GameClient.getInstance();
            client.init(NetworkUtils.getIpAddressFromDevice(getApplicationContext()));
        }).start();
        intent = new Intent(MainActivity.this, CreateLobbyActivity.class);
        startActivity(intent);
    }

}
