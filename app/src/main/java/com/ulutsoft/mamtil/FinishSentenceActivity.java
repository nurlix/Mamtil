package com.ulutsoft.mamtil;

import android.app.Activity;
import android.os.Bundle;

public class FinishSentenceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_sentence);

        overridePendingTransition(R.anim.top_in, R.anim.top_in);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.right_out, R.anim.right_out);
    }
}
