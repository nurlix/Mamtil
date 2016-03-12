package com.ulutsoft.mamtil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ulutsoft.mamtil.R;
import com.ulutsoft.mamtil.objects.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NURLAN on 20.02.2016.
 */
public class WordAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Word> words;
    private static LayoutInflater inflater = null;

    public WordAdapter(Context context, List<Word> words) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.words = new ArrayList<>();
        this.words.addAll(words);
    }

    private static class ViewHolder {
        TextView langFrom;
        TextView langTo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.word, parent, false);
            holder = new ViewHolder();
            holder.langFrom = (TextView)convertView.findViewById(R.id.from_word);
            holder.langTo = (TextView)convertView.findViewById(R.id.to_word);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.langFrom.setText(words.get(position).getLangFrom());
        holder.langTo.setText(words.get(position).getLangTo());

        return convertView;
    }

    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public Word getItem(int position) {
        return words.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
