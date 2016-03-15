package com.ulutsoft.mamtil;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ulutsoft.mamtil.adapters.DialogAdapter;
import com.ulutsoft.mamtil.utils.DBHelper;

import java.io.IOException;
import java.sql.SQLException;

public class DialogActivity extends Activity {

    ListView conversation_list;
    DialogAdapter dialogAdapter;
    MediaPlayer mp = null;

    App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

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

        int category = this.getIntent().getIntExtra("category", 0);
        //int group = this.getIntent().getIntExtra("category", 0);

        dialogAdapter = new DialogAdapter(this, dbHelper.ConversationGroup(app.getLang(), category, 1));

        conversation_list = (ListView)findViewById(R.id.conversation_list);
        conversation_list.setAdapter(dialogAdapter);
        conversation_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mp != null)
                    mp.release();
                int res = getResources().getIdentifier(dialogAdapter.getItem(position).getAudio(), "raw", getPackageName());
                mp = MediaPlayer.create(DialogActivity.this, res);
                mp.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.right_out, R.anim.right_out);
    }
}