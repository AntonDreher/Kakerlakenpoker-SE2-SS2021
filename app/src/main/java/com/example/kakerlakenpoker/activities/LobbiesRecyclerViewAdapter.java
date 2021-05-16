package com.example.kakerlakenpoker.activities;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.network.dto.Lobby;
import com.example.kakerlakenpoker.network.game.GameClient;

import java.util.ArrayList;

public class LobbiesRecyclerViewAdapter extends RecyclerView.Adapter<LobbiesRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Lobby> lobbies;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView lobbyID;
        private final TextView playerCount;
        private final Button joinUp;
        private final Context context;


        public ViewHolder(View view) {
            super(view);

            lobbyID = view.findViewById(R.id.lobbyID);
            playerCount = view.findViewById(R.id.playerCount);
            joinUp = view.findViewById(R.id.joinButton);
            context = view.getContext();
        }

        public TextView getLobbyID() {
            return lobbyID;
        }

        public TextView getPlayerCount() {
            return playerCount;
        }

        public Button getJoinUp() {
            return joinUp;
        }

        public Context getContext(){
            return context;
        }
    }

    public LobbiesRecyclerViewAdapter(ArrayList<Lobby> lobbies) {
        this.lobbies = lobbies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singlelobby, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewholder, final int position) {
        viewholder.getPlayerCount().setText(String.valueOf(lobbies.get(position).getPlayerCount()));
        viewholder.getLobbyID().setText(lobbies.get(position).getName());
        viewholder.getJoinUp().setOnClickListener((View view) -> {
            joinUp(viewholder, position);

        });
    }

    public void joinUp(ViewHolder viewHolder, int position) {
        new Thread(() -> {
            GameClient client = GameClient.getInstance();
            client.reConnect(lobbies.get(position).getHostIP());
            Intent intent = new Intent(viewHolder.context, ShowPlayersInLobbyActivity.class);
            viewHolder.context.startActivity(intent);
        }).start();
    }
    @Override
    public int getItemCount() {
        return lobbies.size();
    }

}
