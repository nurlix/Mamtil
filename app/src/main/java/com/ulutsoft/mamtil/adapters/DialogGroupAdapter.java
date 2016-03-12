package com.ulutsoft.mamtil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulutsoft.mamtil.R;
import com.ulutsoft.mamtil.objects.DialogGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NURLAN on 20.02.2016.
 */

public class DialogGroupAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DialogGroup> categories;
    private static LayoutInflater inflater = null;

    public DialogGroupAdapter(Context context, List<DialogGroup> categories) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.categories = new ArrayList<>();
        this.categories.addAll(categories);
    }

    private static class ViewHolder {
        ImageView icon;
        TextView category;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.dialog_category, parent, false);
            holder = new ViewHolder();
            holder.icon = (ImageView)convertView.findViewById(R.id.icon);
            holder.category = (TextView)convertView.findViewById(R.id.category);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        int res = context.getResources().getIdentifier(getItem(position).getIcon(), "raw", context.getPackageName());
        holder.icon.setImageResource(res);
        holder.category.setText(categories.get(position).getCategory());

        return convertView;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public DialogGroup getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
