package com.ulutsoft.mamtil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.ulutsoft.mamtil.adapters.WordAdapter;
import com.ulutsoft.mamtil.utils.DBHelper;

import java.io.IOException;
import java.sql.SQLException;

public class WordsActivity extends Activity {

    WordAdapter wordAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

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

        String ch = this.getIntent().getStringExtra("char");
        wordAdapter = new WordAdapter(this, dbHelper.Words("ru", "en", ch));
        listView = (ListView)findViewById(R.id.words_activity_listview);
        listView.setAdapter(wordAdapter);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.right_out, R.anim.right_out);
    }
}
