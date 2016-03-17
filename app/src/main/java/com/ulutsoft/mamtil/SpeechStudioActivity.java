package com.ulutsoft.mamtil;

import android.app.Activity;
import android.app.SearchManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import com.ulutsoft.mamtil.adapters.WordsAdapter;
import com.ulutsoft.mamtil.objects.Alphabet;
import com.ulutsoft.mamtil.objects.Word;
import com.ulutsoft.mamtil.utils.DBHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpeechStudioActivity extends Activity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    String[] en_alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String[] ru_alpha = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"};
    String[] chars;

    ExpandableListView words_list;
    WordsAdapter wordsAdapter;
    List<Alphabet> alphabets;
    SearchView search;
    int previousGroup = -1;

    App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_studio);
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

        alphabets = new ArrayList<>();

        if(app.getLang().equals("ru")) {
            chars = ru_alpha;
        } else {
            chars = en_alpha;
        }

        for(String ch : chars) {
            List<Word> wordlist = dbHelper.Words(app.getLang(), ch);
            if(wordlist.size() != 0) {
                Alphabet alphabet = new Alphabet();
                alphabet.setCh(ch);
                alphabet.setWords(wordlist);
                alphabets.add(alphabet);
            }
        }

        wordsAdapter = new WordsAdapter(SpeechStudioActivity.this, alphabets);
        words_list = (ExpandableListView)findViewById(R.id.words);
        words_list.setAdapter(wordsAdapter);
        words_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    words_list.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.right_out, R.anim.right_out);
    }

    @Override
    protected void onDestroy() {
        overridePendingTransition(R.anim.right_out, R.anim.right_out);
        super.onDestroy();
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
        wordsAdapter.filter("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        wordsAdapter.filter(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        wordsAdapter.filter(query);
        expandAll();
        return false;
    }

    private void expandAll() {
        int count = wordsAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            words_list.expandGroup(i);
        }
    }
}
