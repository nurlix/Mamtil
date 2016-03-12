package com.ulutsoft.mamtil.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ulutsoft.mamtil.R;
import com.ulutsoft.mamtil.SpeechActivity;
import com.ulutsoft.mamtil.VocabularyActivity;
import com.ulutsoft.mamtil.objects.Alphabet;
import com.ulutsoft.mamtil.objects.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NURLAN on 27.02.2016.
 */

public class WordsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Alphabet> words;
    private ArrayList<Alphabet> filterData;
    private MediaPlayer mp;

    public WordsAdapter(Context context, List<Alphabet> words) {
        this.context = context;
        this.words = new ArrayList<>();
        this.words.addAll(words);
        this.filterData = new ArrayList<>();
        this.filterData.addAll(words);
    }

    private static class GroupViewHolder {
        TextView alpha;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if(convertView == null) {
            LayoutInflater groupinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = groupinflater.inflate(R.layout.alphabet, parent, false);
            holder = new GroupViewHolder();
            holder.alpha = (TextView)convertView.findViewById(R.id.alpha);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder)convertView.getTag();
        }

        holder.alpha.setText(filterData.get(groupPosition).getCh());
        return convertView;
    }

    private static class ChildViewHolder {
        TextView langFrom;
        TextView langTo;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;

        if(convertView == null) {
            LayoutInflater childinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = childinflater.inflate(R.layout.word, parent, false);
            holder = new ChildViewHolder();
            holder.langFrom = (TextView)convertView.findViewById(R.id.from_word);
            holder.langTo = (TextView)convertView.findViewById(R.id.to_word);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder)convertView.getTag();
        }

        holder.langFrom.setText(filterData.get(groupPosition).getWords().get(childPosition).getLangFrom());
        holder.langTo.setText(filterData.get(groupPosition).getWords().get(childPosition).getLangTo());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(context instanceof VocabularyActivity) {
                    if (mp != null)
                        mp.release();
                    int res = context.getResources().getIdentifier(filterData.get(groupPosition).getWords().get(childPosition).getAudio(), "raw", context.getPackageName());
                    mp = MediaPlayer.create(context, res);
                    mp.start();
                } else {
                    Intent intent = new Intent(context, SpeechActivity.class);
                    intent.putExtra("fromWord",filterData.get(groupPosition).getWords().get(childPosition).getLangFrom());
                    intent.putExtra("toWord", filterData.get(groupPosition).getWords().get(childPosition).getLangTo());
                    intent.putExtra("audio", filterData.get(groupPosition).getWords().get(childPosition).getAudio());
                    context.startActivity(intent);

                }
            }
        });
        return convertView;
    }

    @Override
    public int getGroupCount() {
        return filterData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return filterData.get(groupPosition).getWords().size();
    }

    @Override
    public Alphabet getGroup(int groupPosition) {
        return filterData.get(groupPosition);
    }

    @Override
    public Alphabet getChild(int groupPosition, int childPosition) {
        return filterData.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void filter(String query) {
        query = query.toLowerCase();
        filterData.clear();

        if(query.isEmpty()) {
            filterData.addAll(words);
        } else {
            for (Alphabet alphabet : words) {
                List<Word> newList = new ArrayList<Word>();
                for (Word w : alphabet.getWords()) {
                    if(w.getLangFrom().toLowerCase().contains(query)) {
                        newList.add(w);
                    }
                }
                if(newList.size() > 0) {
                    Alphabet a = new Alphabet(alphabet.getCh(), newList);
                    filterData.add(a);
                }
            }
        }
        notifyDataSetChanged();
    }
}
