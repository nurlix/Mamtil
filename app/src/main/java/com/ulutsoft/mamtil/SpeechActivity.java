package com.ulutsoft.mamtil;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ulutsoft.mamtil.utils.Media;

import java.util.Map;

public class SpeechActivity extends Activity {

    Media media;
    MediaPlayer mp;

    TextView fromWord;
    TextView toWord;
    TextView tv_status;

    Button button_rp;
    Button button_try;

    LinearLayout card;

    Map<String, String> strings;
    App app;

    public enum AudioState { None, Recording, Recorded, Playing };

    AudioState audioState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        overridePendingTransition(R.anim.top_in, R.anim.top_in);
        app = (App)getApplicationContext();

        card = (LinearLayout)findViewById(R.id.card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayWord(false);
            }
        });

        strings = app.getStrings();

        audioState = AudioState.None;
        tv_status = (TextView)findViewById(R.id.tv_status);

        if (audioState == AudioState.None) {
            tv_status.setText(strings.get("text_click_record"));
        }

        fromWord = (TextView)findViewById(R.id.from_word);
        fromWord.setText(this.getIntent().getStringExtra("fromWord"));
        toWord = (TextView)findViewById(R.id.to_word);
        toWord.setText(this.getIntent().getStringExtra("toWord"));

        button_rp = (Button)findViewById(R.id.button_rp);
        button_rp.setText(strings.get("btn_record"));
        button_rp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audioState == AudioState.None) {
                    audioState = AudioState.Recording;
                    tv_status.setText(strings.get("text_recording"));
                    button_rp.setText(strings.get("btn_stop"));
                    media = new Media(SpeechActivity.this);
                    media.record();
                } else if (audioState == AudioState.Recording) {
                    audioState = AudioState.Recorded;
                    tv_status.setText(strings.get("text_record_ended"));
                    button_rp.setText(strings.get("btn_play"));
                    button_try.setEnabled(true);
                    media.stop();
                } else if (audioState == AudioState.Recorded) {
                    audioState = AudioState.Playing;
                    PlayWord(true);
                } else  if (audioState == AudioState.Playing) {
                    PlayWord(true);
                }
            }
        });

        //public enum AudioState { None, Recording, Recorded, Playing };

        button_try = (Button)findViewById(R.id.button_try);
        button_try.setText(strings.get("btn_tryAgain"));
        button_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioState = AudioState.None;
                tv_status.setText(strings.get("text_click_record"));
                button_rp.setText(strings.get("btn_record"));
                button_try.setEnabled(false);
            }
        });
    }

    void PlayWord(boolean play_record) {
        if(play_record) {
            media.play();
            MakePause(1500);
        }
        if (mp != null)
            mp.release();
        int res = getResources().getIdentifier(getIntent().getStringExtra("audio"), "raw", getPackageName());
        mp = MediaPlayer.create(SpeechActivity.this, res);
        mp.start();
    }

    void MakePause(int delay) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, delay);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.top_out, R.anim.top_out);
    }
}
