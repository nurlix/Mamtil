package com.ulutsoft.mamtil.objects;

/**
 * Created by NURLAN on 20.02.2016.
 */

public class Conversation {

    int id;
    String langfrom;
    String langto;
    String audio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLangFrom() {
        return langfrom;
    }

    public void setLangFrom(String langfrom) {
        this.langfrom = langfrom;
    }

    public String getLangTo() {
        return langto;
    }

    public void setLangTo(String langto) {
        this.langto = langto;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
