package com.ulutsoft.mamtil;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.ulutsoft.mamtil.utils.MenuLayout;

public class MainActivity extends Activity {

    MenuLayout menuLayout;
    Button button_vocabulary;
    Button button_conversation_group;
    Button button_dialog;
    Button button_speech_studio;
    Button button_grammar;
    Button button_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuLayout = (MenuLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(menuLayout);

        overridePendingTransition(R.anim.top_in, R.anim.top_in);

        button_vocabulary = (Button)findViewById(R.id.button_vocabulary);
        button_vocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_in);
            }
        });

        button_conversation_group = (Button)findViewById(R.id.button_conversation_group);
        button_conversation_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConversationCategoryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_in);
            }
        });

        button_dialog = (Button)findViewById(R.id.button_dialog);
        button_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogGroupActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_in);
            }
        });

        button_speech_studio = (Button)findViewById(R.id.button_speech_studio);
        button_speech_studio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpeechStudioActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_in);
            }
        });

        button_grammar = (Button)findViewById(R.id.button_grammar);
        button_grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GrammarActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_in);
            }
        });

        button_test = (Button)findViewById(R.id.button_test);
        button_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LesonActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_in);
            }
        });
    }

    public void toggleMenu(View v) {
        menuLayout.toggleMenu();
    }

    @Override
    public void onBackPressed() {
        if(menuLayout.getMenuState() == MenuLayout.MenuState.OPEN) {
            menuLayout.toggleMenu();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings :
                menuLayout.toggleMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
