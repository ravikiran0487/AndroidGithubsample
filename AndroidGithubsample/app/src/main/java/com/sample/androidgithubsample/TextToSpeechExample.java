package com.sample.androidgithubsample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by KRV6User on 12/5/2015.
 */
public class TextToSpeechExample extends AppCompatActivity {

    Button btnspeak;
    Handler handler;
    TextToSpeech tts=null;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texttospeechexample);
        btnspeak = (Button) this.findViewById(R.id.btnspeak);
        handler = new Handler();
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
        new myThread().start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (tts!=null&&tts.isSpeaking()) {
            tts.stop();
            tts.shutdown();
        }
    }

    public class myThread extends Thread implements Runnable {
        @Override
        public void run() {
            super.run();
            tts = new TextToSpeech(TextToSpeechExample.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        tts.setPitch(1.0f);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (pd.isShowing()) {
                                    pd.dismiss();
                                }
                                tts.speak("Welcome to android", TextToSpeech.QUEUE_ADD, null);
                            }
                        });
                    }
                }
            });
        }
    }


}
