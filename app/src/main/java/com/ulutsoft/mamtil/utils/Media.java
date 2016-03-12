package com.ulutsoft.mamtil.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import java.io.IOException;

/**
 * Created by NURLAN on 22.02.2016.
 */

public class Media {

    MediaRecorder myAudioRecorder;
    private String outputFile = null;

    public Media(Context context) {
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        outputFile = context.getApplicationContext().getFilesDir() + "/recording.3gp";
        myAudioRecorder.setOutputFile(outputFile);
    }

    public void record() {
        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        MediaPlayer mp = new MediaPlayer();
        try {
            mp.setDataSource(outputFile);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }

    public void stop() {
        myAudioRecorder.stop();
        if(myAudioRecorder != null) {
            myAudioRecorder.release();
            myAudioRecorder  = null;
        }
    }
}
