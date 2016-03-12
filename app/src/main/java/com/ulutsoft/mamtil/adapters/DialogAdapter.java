package com.ulutsoft.mamtil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ulutsoft.mamtil.R;
import com.ulutsoft.mamtil.objects.Conversation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NURLAN on 20.02.2016.
 */

public class DialogAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Conversation> conversations;
    private static LayoutInflater inflater = null;

    public DialogAdapter(Context context, List<Conversation> conversations) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.conversations = new ArrayList<>();
        this.conversations.addAll(conversations);
    }

    private static class ViewHolder {
        TextView langFrom;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.dialog, parent, false);
            holder = new ViewHolder();
            holder.langFrom = (TextView)convertView.findViewById(R.id.from_word);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.langFrom.setText(conversations.get(position).getLangFrom());

        if ((position % 2) == 0) {
            convertView.setBackgroundResource(R.drawable.patch_1);
        }
        else {
            convertView.setBackgroundResource(R.drawable.patch_2);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return conversations.size();
    }

    @Override
    public Conversation getItem(int position) {
        return conversations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
