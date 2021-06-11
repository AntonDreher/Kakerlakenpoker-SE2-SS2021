package com.example.kakerlakenpoker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kakerlakenpoker.R;
import com.example.server.dto.clienttomainserver.ClientJoinedRequest;
import com.example.server.dto.clienttomainserver.GetOpenLobbies;
import com.example.server.network.NetworkUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchLobbyActivity extends AppCompatActivity {
    private Intent intent;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlobby);

        floatingActionButton = findViewById(R.id.floatingActionButtonSearchLobbyView);
        floatingActionButton.setOnClickListener((View view)->goBack());
        GameClient.getInstance().setLobbyAdapter(new LobbyAdapter(this));
        Thread thread = new Thread(){
            @Override
            public void run() {
                GameClient.getInstance().getClient().sendMessage(new GetOpenLobbies());
            }
        };

        thread.start();
        initRecyclerView();

    }

    public void goBack(){
        intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void initRecyclerView(){
        Context context = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView = findViewById(R.id.lobbiesRecyclerView);
                Log.e("LobbyCount: ", String.valueOf(GameClient.getInstance().getOpenLobbies().size()));
                LobbiesRecyclerViewAdapter adapter = new LobbiesRecyclerViewAdapter(GameClient.getInstance().getOpenLobbies());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
        });



    }


}
