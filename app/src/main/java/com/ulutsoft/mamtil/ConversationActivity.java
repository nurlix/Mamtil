package com.ulutsoft.mamtil;

import android.app.Activity;
import android.app.SearchManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.ulutsoft.mamtil.adapters.ConversationAdapter;
import com.ulutsoft.mamtil.utils.DBHelper;

import java.io.IOException;
import java.sql.SQLException;

public class ConversationActivity extends Activity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private ListView listView;
    private SearchView search;
    private ConversationAdapter conversationAdapter;
    private MediaPlayer mp;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

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
        conversationAdapter = new ConversationAdapter(this, dbHelper.Conversations(app.getLang(), category));
        listView = (ListView)findViewById(R.id.conversation_list);
        listView.setAdapter(conversationAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mp != null)
                    mp.release();
                int res = getResources().getIdentifier(conversationAdapter.getItem(position).getAudio(), "raw", getPackageName());
                mp = MediaPlayer.create(ConversationActivity.this, res);
                mp.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.right_out, R.anim.right_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);

        search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onClose() {
        conversationAdapter.filter("");
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        conversationAdapter.filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        conversationAdapter.filter(query);
        return false;
    }
}
