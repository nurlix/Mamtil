package com.ulutsoft.mamtil;

import android.app.Activity;
import android.os.Bundle;

import com.ulutsoft.mamtil.objects.Tamga;
import com.ulutsoft.mamtil.objects.Word;
import com.ulutsoft.mamtil.utils.DBHelper;
import com.ulutsoft.mamtil.utils.FlowLayout;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TestActivity extends Activity {

    String[] ru_alpha = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"};

    FlowLayout fl;
    FlowLayout.LayoutParams flowLP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        fl = (FlowLayout)findViewById(R.id.fl);
        flowLP = new FlowLayout.LayoutParams(0, 0);

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

        for(String ch : ru_alpha) {
            List<Word> wordlist = dbHelper.Words("ru", "en", ch);
            if(wordlist.size() != 0) {
                Tamga tamga = new Tamga(TestActivity.this, ch);
                fl.addView(tamga);
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.right_out, R.anim.right_out);
    }
}
