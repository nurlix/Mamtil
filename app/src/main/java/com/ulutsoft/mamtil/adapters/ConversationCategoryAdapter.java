package com.ulutsoft.mamtil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulutsoft.mamtil.R;
import com.ulutsoft.mamtil.objects.ConversationCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NURLAN on 20.02.2016.
 */

public class ConversationCategoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ConversationCategory> categories;
    private ArrayList<ConversationCategory> filterData;
    private static LayoutInflater inflater = null;

    public ConversationCategoryAdapter(Context context, List<ConversationCategory> categories) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.categories = new ArrayList<>();
        this.categories.addAll(categories);
        this.filterData = new ArrayList<>();
        this.filterData.addAll(categories);
    }

    private static class ViewHolder {
        ImageView icon;
        TextView category;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.conversation_category, parent, false);
            holder = new ViewHolder();
            holder.icon = (ImageView)convertView.findViewById(R.id.icon);
            holder.category = (TextView)convertView.findViewById(R.id.category);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        int res = context.getResources().getIdentifier(getItem(position).getIcon(), "raw", context.getPackageName());
        holder.icon.setImageResource(res);
        holder.category.setText(filterData.get(position).getCategory());

        return convertView;
    }

    @Override
    public int getCount() {
        return filterData.size();
    }

    @Override
    public ConversationCategory getItem(int position) {
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
            filterData.addAll(categories);
        } else {
            for (ConversationCategory cc : categories) {
                if(cc.getCategory().toLowerCase().contains(query)) {
                    filterData.add(cc);
                }
            }
        }
        notifyDataSetChanged();
    }
}
