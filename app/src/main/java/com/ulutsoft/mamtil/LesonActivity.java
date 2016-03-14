package com.ulutsoft.mamtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LesonActivity extends Activity {

    Button GuesWord;
    Button FinishSentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leson);

        GuesWord = (Button)findViewById(R.id.lesson_words);
        GuesWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LesonActivity.this, FindWordActivity.class);
                startActivity(intent);
            }
        });

        FinishSentence = (Button)findViewById(R.id.lesson_complete_sentence);
        FinishSentence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LesonActivity.this, FinishSentenceActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.right_out, R.anim.right_out);
    }
}
