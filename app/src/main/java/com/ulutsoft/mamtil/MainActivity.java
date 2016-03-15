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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    RadioButton lang_ru;
    RadioButton lang_en;
    RadioGroup lang_group;

    App app;

    String[] ru_titles = { "Словарь", "Разгаворник", "Диалог", "Запись", "Граматика", "Тест" };
    String[] en_titles = { "Vocabulary", "Phrasebook", "Conversation", "Speech", "Grammar", "Test" };
    String[] titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuLayout = (MenuLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(menuLayout);

        app = (App)getApplicationContext();

        overridePendingTransition(R.anim.top_in, R.anim.top_in);

        lang_ru = (RadioButton)findViewById(R.id.lang_ru);
        lang_en = (RadioButton)findViewById(R.id.lang_en);
        lang_group = (RadioGroup)findViewById(R.id.lang_group);
        lang_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        break;
                    case R.id.lang_ru:
                        app.setLang("ru");
                        break;
                    case R.id.lang_en:
                        app.setLang("en");
                        break;
                    default:
                        break;
                }
            }
        });

        if(app.getLang() == "ru") {
            titles = ru_titles;
        } else {
            titles = en_titles;
        }

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

        setLang();
    }

    void changeInterface() {
        button_vocabulary.setText(titles[0]);
        button_conversation_group.setText(titles[1]);
        button_dialog.setText(titles[2]);
        button_speech_studio.setText(titles[3]);
        button_grammar.setText(titles[4]);
        button_test.setText(titles[5]);
    }

    void setLang() {
        if(app.getLang() == "ru") {
            lang_ru.setChecked(true);
        } else {
            lang_en.setChecked(true);
        }
        changeInterface();
    }

    @Override
    public void onBackPressed() {
        if(menuLayout.getMenuState() == MenuLayout.MenuState.OPEN) {
            menuLayout.toggleMenu();
            setTitle("Башкы бет");
            changeInterface();
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
                if(menuLayout.getMenuState() == MenuLayout.MenuState.OPEN) {
                    setTitle("Башкы бет");
                } else {
                    setTitle("Настройкалар");
                }
                menuLayout.toggleMenu();
                changeInterface();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
