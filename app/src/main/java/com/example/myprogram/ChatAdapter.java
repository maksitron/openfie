package com.example.myprogram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//import androidx.annotation.NonNull;

public class ChatAdapter extends ArrayAdapter {
    public ChatAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_massenger, null);

            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
        }
    else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.username = (TextView) convertView.findViewById(R.id.nic_user);
        viewHolder.message = (TextView) convertView.findViewById(R.id.masegge_text);
        viewHolder.messagetime = (TextView) convertView.findViewById(R.id.maseggatime);

        ChatMesagge item = (ChatMesagge) getItem(position);
        if(item != null) {
            viewHolder.username.setText(item.getMesaggeUser());
            viewHolder.message.setText(item.getMesaggeText());
            viewHolder.messagetime.setText(item.getMesaggeTime());
        }


        return super.getView(position, convertView, parent);
    }


    private static class ViewHolder{
        TextView username;
        TextView message;
        TextView messagetime;
    }
}
