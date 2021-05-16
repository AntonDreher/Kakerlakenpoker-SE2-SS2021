package com.example.kakerlakenpoker.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.GetOpenLobbies;
import com.example.kakerlakenpoker.network.game.GameClient;

import java.util.concurrent.ExecutionException;

public class EnterUserNameActivity extends AppCompatActivity {
    ProgressDialog dialog;
    AlertDialog.Builder builder;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterusername);

        intent = new Intent(this, MainMenuActivity.class);
        Button goButton = (Button) findViewById(R.id.goBtn);
        goButton.setOnClickListener((View view) -> connectToMainServer());



    }

    public void connectToMainServer() {
        new InitClientAsyncTask().execute();
    }

    class InitClientAsyncTask extends AsyncTask<String, Integer, String>{

        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(EnterUserNameActivity.this);
            dialog.setMessage("Connecting to servers.");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            GameClient.getInstance().init(NetworkUtils.getIpAddressFromDevice(EnterUserNameActivity.this));
            return "Client initialized";
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            if (GameClient.getInstance().getClient().getClient().isConnected()) {
                startActivity(intent);
            } else {
                builder = new AlertDialog.Builder(EnterUserNameActivity.this);
                builder.setMessage("Could not connect to the servers. Please try again.")
                        .setTitle("Information");
                builder.show();
            }
        }
    }
}
