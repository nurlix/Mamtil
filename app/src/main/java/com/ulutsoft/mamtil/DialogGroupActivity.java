package com.ulutsoft.mamtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ulutsoft.mamtil.adapters.ConversationCategoryAdapter;
import com.ulutsoft.mamtil.utils.DBHelper;

import java.io.IOException;
import java.sql.SQLException;

public class DialogGroupActivity extends Activity {

    private ListView listView;
    private ConversationCategoryAdapter conversationCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_group);

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

        conversationCategoryAdapter = new ConversationCategoryAdapter(this, dbHelper.Categories("category_ru"));
        listView = (ListView)findViewById(R.id.dialog_category_list);
        listView.setAdapter(conversationCategoryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DialogGroupActivity.this, DialogActivity.class);
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
}
