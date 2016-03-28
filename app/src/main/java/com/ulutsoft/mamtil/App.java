package com.ulutsoft.mamtil;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NURLAN on 20.02.2016.
 */

public class App extends Application {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Map<String, String> map_ru;
    Map<String, String> map_en;
    Map<String, String> map_tr;

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("lang","").isEmpty()) {
            editor = sharedPreferences.edit();
            editor.putString("lang", "ru");
            editor.putString("bgcolor", "orange");
            editor.commit();
        }

        initMaps();
    }

    public String getLang(){
        return sharedPreferences.getString("lang","ru");
    }

    public void setLang(String lang) {
        editor.putString("lang", lang);
        editor.commit();
    }

    public String getbgColor() {
        return sharedPreferences.getString("bgcolor","orange");
    }

    public void setbgColor(String bgcolor) {
        editor.putString("bgcolor", bgcolor);
        editor.commit();
    }

    private void initMaps() {

        map_ru = new HashMap<>();
        map_ru.put("btn_vocabulary", "Словарь");
        map_ru.put("btn_phrasebook", "Разгаворник");
        map_ru.put("btn_conversation", "Диалог");
        map_ru.put("btn_speech", "Запись");
        map_ru.put("btn_grammar", "Граматика");
        map_ru.put("btn_test", "Тест");
        map_ru.put("btn_record", "Запись");
        map_ru.put("btn_stop", "Стоп");
        map_ru.put("btn_play", "Воспроизвести");
        map_ru.put("btn_tryAgain", "Попробовать снова");
        map_ru.put("text_click_record", "Нажмите <Запись>");
        map_ru.put("text_recording", "Идет запись ...");
        map_ru.put("text_record_ended", "Запись завершен!\n Нажмите <Воспроизвести>");
        map_ru.put("guess_word", "Угадай Слово");
        map_ru.put("text_of", " из ");
        map_ru.put("title_settings", "Настройки");
        map_ru.put("title_home", "Начальная страница");
        map_ru.put("color_orange", " Оранжевый");
        map_ru.put("color_green", " Зеленный");
        map_ru.put("color_blue", " Синий");

        map_en = new HashMap<>();
        map_en.put("btn_vocabulary", "Vocabulary");
        map_en.put("btn_phrasebook", "Phrasebook");
        map_en.put("btn_conversation", "Conversation");
        map_en.put("btn_speech", "Speech");
        map_en.put("btn_grammar", "Grammar");
        map_en.put("btn_test", "Test");
        map_en.put("btn_record", "Record");
        map_en.put("btn_stop", "Stop");
        map_en.put("btn_play", "Play");
        map_en.put("btn_tryAgain", "Try again");
        map_en.put("text_click_record", "Click <Record>");
        map_en.put("text_recording", "Recording ...");
        map_en.put("text_record_ended", "Record ended!\nClick <Play>");
        map_en.put("guess_word", "Guess Word");
        map_en.put("text_of", " of ");
        map_en.put("title_settings", "Settings");
        map_en.put("title_home", "Home");
        map_en.put("color_orange", " Orange");
        map_en.put("color_green", " Green");
        map_en.put("color_blue", " Blue");

        map_tr = new HashMap<>();
        map_tr.put("btn_vocabulary", "Sözlük");
        map_tr.put("btn_phrasebook", "Разгаворник");
        map_tr.put("btn_conversation", "Diyalog");
        map_tr.put("btn_speech", "Kayıt");
        map_tr.put("btn_grammar", "Dilbilgisi");
        map_tr.put("btn_test", "Test");
        map_tr.put("btn_record", "Kayıt");
        map_tr.put("btn_stop", "Dur");
        map_tr.put("btn_play", "Oynat");
        map_tr.put("btn_tryAgain", "Tekrar deneyin");
        map_tr.put("text_click_record", "<Kayıtı> basın");
        map_tr.put("text_recording", "Kayıting ...");
        map_tr.put("text_record_ended", "Kayıt bitti!\n <Oynatı> basın");
        map_tr.put("guess_word", "Kelimeyi bul");
        map_tr.put("text_of", " из ");
        map_tr.put("title_settings", "Ayarlar");
        map_tr.put("title_home", "Ev");
        map_tr.put("color_orange", " Turuncu");
        map_tr.put("color_green", " Yeşil");
        map_tr.put("color_blue", " Mavi");
    }

    public Map<String, String> getStrings() {
        String lang = sharedPreferences.getString("lang", "ru");
        if(lang.equals("ru")) {
            return map_ru;
        } else if(lang.equals("en")) {
            return map_en;
        } else {
            return map_tr;
        }
    }
}
