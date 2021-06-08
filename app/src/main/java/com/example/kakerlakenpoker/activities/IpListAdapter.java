package com.example.kakerlakenpoker.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kakerlakenpoker.R;

public class IpListAdapter extends ArrayAdapter<String> {
    public IpListAdapter(Context context, List<String> ipList){
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
