package com.ulutsoft.mamtil.objects;

import java.util.List;

/**
 * Created by NURLAN on 27.02.2016.
 */

public class Alphabet {

    String CHAR;
    List<Word> words;

    public Alphabet() {}

    public Alphabet(String CHAR, List<Word> words) {
        this.CHAR = CHAR;
        this.words = words;
    }

    public String getCh() {
        return CHAR;
    }

    public void setCh(String CHAR) {
        this.CHAR = CHAR;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
