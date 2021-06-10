package com.example.kakerlakenpoker.activities;

public class LobbyAdapter {
    private SearchLobbyActivity searchLobbyActivity;

    public LobbyAdapter(SearchLobbyActivity activity){
        searchLobbyActivity=activity;
    }

    public void notifyAdapter(){
        searchLobbyActivity.initRecyclerView();
    }
}
