package com.ulutsoft.mamtil;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.ulutsoft.mamtil.adapters.ConversationCategoryAdapter;
import com.ulutsoft.mamtil.utils.DBHelper;

import java.io.IOException;
import java.sql.SQLException;

public class ConversationCategoryActivity extends Activity  implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private ListView listView;
    private SearchView search;
    private ConversationCategoryAdapter conversationCategoryAdapter;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_category);
        overridePendingTransition(R.anim.right_in, R.anim.right_in);

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

        conversationCategoryAdapter = new ConversationCategoryAdapter(this, dbHelper.Categories(app.getLang()));
        listView = (ListView)findViewById(R.id.conversation_category_list);
        listView.setAdapter(conversationCategoryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ConversationCategoryActivity.this, ConversationActivity.class);
                intent.putExtra("category", conversationCategoryAdapter.getItem(position).getId());
                intent.putExtra("title", conversationCategoryAdapter.getItem(position).getCategory());
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_in);
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
        conversationCategoryAdapter.filter("");
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        conversationCategoryAdapter.filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        conversationCategoryAdapter.filter(query);
        return false;
    }
}
