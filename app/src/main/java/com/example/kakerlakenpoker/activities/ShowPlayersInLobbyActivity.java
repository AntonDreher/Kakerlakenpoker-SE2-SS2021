package com.example.kakerlakenpoker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.network.dto.PlayersReady;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.game.GameServer;
import java.util.ArrayList;

public class ShowPlayersInLobbyActivity extends AppCompatActivity {
    ListView currentPlayersInLobby;
    GameClient client;
    GameServer server;
    private Button readyState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players_in_lobby);
        currentPlayersInLobby = (ListView) findViewById(R.id.ListViewClientsInLobby);
        client = GameClient.getInstance();
        ArrayList<String> arrayList = client.getIpList();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayList);
        //currentPlayersInLobby.setAdapter(arrayAdapter);


        readyState = (Button) findViewById(R.id.readyState);
        readyState.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                client.getClient().sendMessage(new PlayersReady());
                readyState.setEnabled(false);

                //openGame();
            }

        });
    }

    /* public void openGame(){
            Intent startGame = new Intent(this, GameActivity.class); //open new activity GameActivity
            startActivity(startGame);

    } */


}