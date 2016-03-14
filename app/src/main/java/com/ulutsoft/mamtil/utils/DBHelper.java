package com.ulutsoft.mamtil.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.ulutsoft.mamtil.objects.Conversation;
import com.ulutsoft.mamtil.objects.ConversationCategory;
import com.ulutsoft.mamtil.objects.Grammar;
import com.ulutsoft.mamtil.objects.Word;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NURLAN on 19.02.2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final Context context;
    private SQLiteDatabase myDataBase;
    private String DB_PATH;
    private static String DB_NAME = "Database.db";

    public List<Word> LessonWords(String to) {
        List<Word> wordlist = new ArrayList<Word>();
        String selectQuery = "SELECT _id, kg, " + to + ", audio text FROM Words ORDER BY RANDOM() limit 10";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Word word = new Word();
                word.setId(cursor.getInt(0));
                word.setLangFrom(cursor.getString(1));
                word.setLangTo(cursor.getString(2));
                word.setAudio(cursor.getString(3));
                wordlist.add(word);
            } while (cursor.moveToNext());
        }
        return wordlist;
    }

    public List<Word> Words(String from, String to, String ch) {
        List<Word> wordlist = new ArrayList<Word>();
        String selectQuery = "SELECT _id, " + from + ", " + to + ", audio text FROM Words where UPPER(" + from + ") like UPPER('" + ch + "%')";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Word word = new Word();
                word.setId(cursor.getInt(0));
                word.setLangFrom(cursor.getString(1));
                word.setLangTo(cursor.getString(2));
                word.setAudio(cursor.getString(3));
                wordlist.add(word);
            } while (cursor.moveToNext());
        }
        return wordlist;
    }

    public List<ConversationCategory> Categories(String lang) {
        List<ConversationCategory> categories = new ArrayList<ConversationCategory>();
        String selectQuery = "SELECT _id, icon, " + lang + " FROM categories";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ConversationCategory category = new ConversationCategory();
                category.setId(cursor.getInt(0));
                category.setIcon(cursor.getString(1));
                category.setCategory(cursor.getString(2));
                categories.add(category);
            } while (cursor.moveToNext());
        }
        return categories;
    }

    public List<Conversation> ConversationGroup(String from, String to, int category, int group) {
        List<Conversation> conversations = new ArrayList<Conversation>();
        String selectQuery = "SELECT _id, " + from + ", " + to + ", audio text FROM conversations where category = " + category + " and conversation_group = " + group + " order by conversation_order";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Conversation conversation = new Conversation();
                conversation.setId(cursor.getInt(0));
                conversation.setLangFrom(cursor.getString(1));
                conversation.setLangTo(cursor.getString(2));
                conversation.setAudio(cursor.getString(3));
                conversations.add(conversation);
            } while (cursor.moveToNext());
        }
        return conversations;
    }

    public List<Conversation> Conversations(String from, String to, int category) {
        List<Conversation> conversations = new ArrayList<Conversation>();
        String selectQuery = "SELECT _id, " + from + ", " + to + ", audio text FROM conversations where category = " + category;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Conversation conversation = new Conversation();
                conversation.setId(cursor.getInt(0));
                conversation.setLangFrom(cursor.getString(1));
                conversation.setLangTo(cursor.getString(2));
                conversation.setAudio(cursor.getString(3));
                conversations.add(conversation);
            } while (cursor.moveToNext());
        }
        return conversations;
    }

    public List<Grammar> GrammarList() {
        List<Grammar> grammarList = new ArrayList<Grammar>();
        String selectQuery = "SELECT _id, title FROM grammar";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Grammar grammar = new Grammar();
                grammar.setId(cursor.getInt(0));
                grammar.setTitle(cursor.getString(1));
                grammarList.add(grammar);
            } while (cursor.moveToNext());
        }
        return grammarList;
    }

    public List<String> GrammarContent(String from, String to, int id) {
        List<String> content = new ArrayList<String>();
        String selectQuery = "SELECT " + from + ", " + to + " FROM grammar where _id = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                content.add(cursor.getString(0));
                content.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return content;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist){ } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){  }

        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}
