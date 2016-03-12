package com.ulutsoft.mamtil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ulutsoft.mamtil.R;
import com.ulutsoft.mamtil.objects.Grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NURLAN on 20.02.2016.
 */

public class GrammarAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Grammar> grammars;
    private ArrayList<Grammar> filterData;
    private static LayoutInflater inflater = null;

    public GrammarAdapter(Context context, List<Grammar> grammars) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.grammars = new ArrayList<>();
        this.grammars.addAll(grammars);
        this.filterData = new ArrayList<>();
        this.filterData.addAll(grammars);
    }

    private static class ViewHolder {
        TextView title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.grammar, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView)convertView.findViewById(R.id.from_word);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.title.setText(filterData.get(position).getTitle());
        return convertView;
    }

    @Override
    public int getCount() {
        return filterData.size();
    }

    @Override
    public Grammar getItem(int position) {
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
            filterData.addAll(grammars);
        } else {
            for (Grammar g : grammars) {
                if(g.getTitle().toLowerCase().contains(query)) {
                    filterData.add(g);
                }
            }
        }
        notifyDataSetChanged();
    }
}
