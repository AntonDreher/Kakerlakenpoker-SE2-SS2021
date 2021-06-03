package com.example.kakerlakenpoker.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.game.GameServer;
import com.example.kakerlakenpoker.server.Main;

public class TestClientServerConnection extends AppCompatActivity {
    private Button makeServer;
    private Button makeClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testclientserver);

        makeServer = findViewById(R.id.btnmakeServer);
        makeClient = findViewById(R.id.btnmakeClient);

        makeClient.setOnClickListener(v->makeClient());

    }


    public void makeClient(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                GameClient.getInstance().init("");
                GameClient.getInstance().connect(NetworkUtils.getIpAddressFromDevice());
            }
        };

        thread.start();
    }

    public static void main(String[] args) {
        GameServer.getInstance().init();
    }

}
