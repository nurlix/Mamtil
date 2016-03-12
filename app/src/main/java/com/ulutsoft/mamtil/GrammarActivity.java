package com.ulutsoft.mamtil;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ulutsoft.mamtil.adapters.GrammarAdapter;
import com.ulutsoft.mamtil.utils.DBHelper;
import com.ulutsoft.mamtil.utils.MenuLayout;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GrammarActivity extends Activity {

    private MenuLayout menuLayout;
    private TextView from_word;
    private TextView to_word;
    private ListView grammar_list;
    private GrammarAdapter grammarAdapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuLayout = (MenuLayout) getLayoutInflater().inflate(R.layout.activity_grammar, null);
        setContentView(menuLayout);

        from_word = (TextView)findViewById(R.id.from_word);
        to_word = (TextView)findViewById(R.id.to_word);

        dbHelper = new DBHelper(getApplicationContext());
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

        grammarAdapter = new GrammarAdapter(this, dbHelper.GrammarList());
        grammar_list = (ListView)findViewById(R.id.grammar_list);
        grammar_list.setAdapter(grammarAdapter);
        grammar_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<String> strings = dbHelper.GrammarContent("ru", "kg", grammarAdapter.getItem(position).getId());
                from_word.setText(Html.fromHtml(strings.get(0)));
                to_word.setText(Html.fromHtml(strings.get(1)));
                menuLayout.toggleMenu();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(menuLayout.getMenuState() == MenuLayout.MenuState.OPEN) {
            menuLayout.toggleMenu();
        } else {
            finish();
            overridePendingTransition(R.anim.right_out, R.anim.right_out);
        }
    }
}
