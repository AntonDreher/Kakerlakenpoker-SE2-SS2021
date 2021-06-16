package com.example.kakerlakenpoker.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.R;
import com.example.server.NetworkConstants;


public class EnterUserNameActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private AlertDialog.Builder builder;
    private Intent intent;
    private EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterusername);

        intent = new Intent(this, MainMenuActivity.class);

        userName = findViewById(R.id.enterUsernameTextField);

        Button goButton = (Button) findViewById(R.id.goBtn);
        goButton.setOnClickListener((View view) -> connectToMainServer());

    }

    public void connectToMainServer() {
        String username = userName.getText().toString();
        if (username.matches("")) {
            Toast.makeText(this, "Please enter an username", Toast.LENGTH_SHORT).show();
            return;
        }
        GameClient.getInstance().setUserName(userName.getText().toString());
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
            GameClient.getInstance().init(NetworkConstants.MAIN_SERVER_IP);
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
