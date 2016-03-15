package com.ulutsoft.mamtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ulutsoft.mamtil.objects.Word;
import com.ulutsoft.mamtil.utils.DBHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FindWordActivity extends Activity {

    TextView from_word;
    TextView hidden_word;
    Button button_next;
    List<Word> wordlist;
    LinearLayout word;
    int index;

    App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_word);

        overridePendingTransition(R.anim.top_in, R.anim.top_in);

        app = (App)getApplicationContext();

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        try {
            dbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            dbHelper.openDataBase();
        }catch(SQLException e){
            e.printStackTrace();
        }

        wordlist = dbHelper.LessonWords(app.getLang());
        index = 0;

        hidden_word = (TextView)findViewById(R.id.hidden_word);

        from_word = (TextView)findViewById(R.id.from_word);
        from_word.setText(wordlist.get(index).getLangTo());

        button_next = (Button)findViewById(R.id.button_next);
        button_next.setText((index + 1) + "  из 10");
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < 9) {
                    ++index;
                } else {
                    index = 0;
                }
                from_word.setText(wordlist.get(index).getLangTo());
                button_next.setText((index + 1) + "  из 10");
                hidden_word.setVisibility(View.GONE);
            }
        });

        word = (LinearLayout)findViewById(R.id.word);
        word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hidden_word.getVisibility() != View.VISIBLE) {
                    hidden_word.setText(wordlist.get(index).getLangFrom());
                    hidden_word.setVisibility(View.VISIBLE);
                    Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                    hidden_word.startAnimation(animation1);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.right_out, R.anim.right_out);
    }
}
