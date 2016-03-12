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
public class ConversationAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Conversation> conversations;
    private ArrayList<Conversation> filterData;
    private static LayoutInflater inflater = null;

    public ConversationAdapter(Context context, List<Conversation> conversations) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.conversations = new ArrayList<>();
        this.conversations.addAll(conversations);
        this.filterData = new ArrayList<>();
        this.filterData.addAll(conversations);
    }

    private static class ViewHolder {
        TextView langFrom;
        TextView langTo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.conversation, parent, false);
            holder = new ViewHolder();
            holder.langFrom = (TextView)convertView.findViewById(R.id.from_word);
            holder.langTo = (TextView)convertView.findViewById(R.id.to_word);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.langFrom.setText(filterData.get(position).getLangFrom());
        holder.langTo.setText(filterData.get(position).getLangTo());

        return convertView;
    }

    @Override
    public int getCount() {
        return filterData.size();
    }

    @Override
    public Conversation getItem(int position) {
        return filterData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void filter(String query) {
        query = query.toLowerCase();
        filterData.clear();

        if(query.isEmpty()) {
            filterData.addAll(conversations);
        } else {
            for (Conversation c : conversations) {
                if(c.getLangFrom().toLowerCase().contains(query)) {
                    filterData.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }
}
