package com.ulutsoft.mamtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ulutsoft.mamtil.utils.MenuLayout;

import java.util.Map;

public class MainActivity extends Activity {

    MenuLayout menuLayout;
    Button button_vocabulary;
    Button button_phrasebook;
    Button button_conversation;
    Button button_speech_studio;
    Button button_grammar;
    Button button_test;

    RadioButton lang_ru;
    RadioButton lang_en;
    RadioButton lang_tr;
    RadioGroup lang_group;

    RadioButton color_orange;
    RadioButton color_green;
    RadioButton color_blue;
    RadioGroup color_group;

    LinearLayout layout_home;

    App app;
    Map<String, String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuLayout = (MenuLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(menuLayout);

        layout_home = (LinearLayout)findViewById(R.id.layout_home);

        app = (App)getApplicationContext();
        strings = app.getStrings();

        String colorstr = app.getbgColor();
        int color = R.color.clorange;

        if(colorstr.equals("orange")) {
            color = R.color.clorange;
        } else if(colorstr.equals("green")) {
            color = R.color.clgreen;
        } else {
            color = R.color.clblue;
        }

        menuLayout.setBackgroundColor(getResources().getColor(color));
        layout_home.setBackgroundColor(getResources().getColor(color));

        button_vocabulary = (Button)findViewById(R.id.button_vocabulary);
        button_vocabulary.setText(strings.get("btn_vocabulary"));
        button_vocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                startActivity(intent);
            }
        });

        button_phrasebook = (Button)findViewById(R.id.button_phrasebook);
        button_phrasebook.setText(strings.get("btn_phrasebook"));
        button_phrasebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConversationCategoryActivity.class);
                startActivity(intent);
            }
        });

        button_conversation = (Button)findViewById(R.id.button_conversation);
        button_conversation.setText(strings.get("btn_conversation"));
        button_conversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogGroupActivity.class);
                startActivity(intent);
            }
        });

        button_speech_studio = (Button)findViewById(R.id.button_speech_studio);
        button_speech_studio.setText(strings.get("btn_speech"));
        button_speech_studio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpeechStudioActivity.class);
                startActivity(intent);
            }
        });

        button_grammar = (Button)findViewById(R.id.button_grammar);
        button_grammar.setText(strings.get("btn_grammar"));
        button_grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GrammarActivity.class);
                startActivity(intent);
            }
        });

        button_test = (Button)findViewById(R.id.button_test);
        button_test.setText(strings.get("btn_test"));
        button_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LesonActivity.class);
                startActivity(intent);
            }
        });

        lang_ru = (RadioButton)findViewById(R.id.lang_ru);
        lang_en = (RadioButton)findViewById(R.id.lang_en);
        lang_tr = (RadioButton)findViewById(R.id.lang_tr);

        setLangs();

        lang_group = (RadioGroup)findViewById(R.id.lang_group);
        lang_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String languageToLoad = "ru";

                switch (checkedId) {
                    case -1:
                        break;
                    case R.id.lang_ru:
                        languageToLoad = "ru";
                        app.setLang("ru");
                        break;
                    case R.id.lang_en:
                        languageToLoad = "en";
                        app.setLang("en");
                        break;
                    case R.id.lang_tr:
                        languageToLoad = "en";
                        app.setLang("en");
                        break;
                    default:
                        break;
                }
                changeInterface(languageToLoad);

                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        color_orange = (RadioButton)findViewById(R.id.color_orange);
        color_green = (RadioButton)findViewById(R.id.color_green);
        color_blue = (RadioButton)findViewById(R.id.color_blue);

        setColors();

        color_group = (RadioGroup)findViewById(R.id.bgcolor_group);
        color_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        break;
                    case R.id.color_orange:
                        app.setbgColor("orange");
                        break;
                    case R.id.color_green:
                        app.setbgColor("green");
                        break;
                    case R.id.color_blue:
                        app.setbgColor("blue");
                        break;
                    default:
                        break;
                }

                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    private void changeInterface(String languageToLoad) {

    }

    void setLangs() {
        String lang = app.getLang();

        if(lang.equals("ru")) {
            lang_ru.setChecked(true);
        }

        if(lang.equals("en")) {
            lang_en.setChecked(true);
        }

        if(lang.equals("tr")) {
            lang_tr.setChecked(true);
        }
    }

    void setColors() {

        String color = app.getbgColor();
        if(color.equals("orange")) {
            color_orange.setChecked(true);
        }

        if(color.equals("green")) {
            color_green.setChecked(true);
        }

        if(color.equals("blue")) {
            color_blue.setChecked(true);
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
                    //setTitle(titleHome);
                    //changeInterface();
                } else {
                    //setTitle(titleMenu);
                }
                menuLayout.toggleMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if(menuLayout.getMenuState() == MenuLayout.MenuState.OPEN) {
            menuLayout.toggleMenu();
            //setTitle(titleHome);
            //changeInterface();
        } else {
            finish();
        }
    }
}
