package com.example.kakerlakenpoker.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kakerlakenpoker.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class PlayersInLobbyRecyclerViewAdapter extends RecyclerView.Adapter<PlayersInLobbyRecyclerViewAdapter.ViewHolder>{
    private ArrayList<String> playersInLobby;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView playerName;

        public ViewHolder(View view){
            super(view);
            playerName = view.findViewById(R.id.playerID);
        }

        public TextView getPlayerName(){ return playerName;}
    }

    public PlayersInLobbyRecyclerViewAdapter(ArrayList<String> playersInLobby){
        this.playersInLobby = playersInLobby;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleplayerinlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayersInLobbyRecyclerViewAdapter.ViewHolder viewholder, final int position) {
        viewholder.getPlayerName().setText(String.valueOf(playersInLobby.get(position)));
    }

    @Override
    public int getItemCount() {
        return playersInLobby.size();
    }

}
