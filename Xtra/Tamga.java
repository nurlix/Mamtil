package com.ulutsoft.mamtil.objects;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ulutsoft.mamtil.R;
import com.ulutsoft.mamtil.WordsActivity;

/**
 * Created by NURLAN on 03.03.2016.
 */
public class Tamga extends LinearLayout {

    Context context;
    LinearLayout tview;
    String ch;
    TextView tv_char;

    public Tamga(Context context, String ch) {
        super(context);
        this.context = context;
        this.ch = ch;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tamga, this);

        tv_char = (TextView)findViewById(R.id.tv_char);
        tv_char.setText(ch);
        tview = (LinearLayout)findViewById(R.id.tview);
        tview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WordsActivity.class);
                intent.putExtra("char", tv_char.getText());
                getContext().startActivity(intent);
            }
        });
    }
}
