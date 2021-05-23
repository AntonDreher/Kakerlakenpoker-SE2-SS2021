package com.example.kakerlakenpoker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class IpListAdapter extends ArrayAdapter<String> {
    public IpListAdapter(Context context, ArrayList<String> ipList){
        super(context, 0, ipList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String ip = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.singleplayerinlist, parent, false);
        }
        TextView tvIp = convertView.findViewById(R.id.playerID);
        tvIp.setText(ip);
        return convertView;
    }
}
