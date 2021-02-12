package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    int min,sec,ts;
     public MediaPlayer alarm;
     public boolean panda ;

    public void timerset (int j){

        TextView textView = (TextView) findViewById(R.id.textView);
        TextView textView2 = (TextView) findViewById(R.id.textView2);

         ts=j;
         min = j/60;
         sec = j%60;
        textView.setText(String.valueOf(min));
        textView2.setText(String.valueOf(sec));



    }
    public void stop (View view) {
        Button start = (Button) findViewById(R.id.button);
        Button stop = (Button) findViewById(R.id.button3);

        panda = false;



            start.setVisibility(Button.VISIBLE);
            stop.setVisibility(Button.INVISIBLE);


        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setEnabled(true);

         alarm.stop();


    }

    public void pause (View view){

        if (panda)
        {
            panda = false ;
        }

        else {
            panda = true ;

        }

    }



    public void start ( View view ){
        Button start = (Button) findViewById(R.id.button);
        start.setVisibility(Button.INVISIBLE);
        Button pause = (Button) findViewById(R.id.button2);
        pause.setVisibility(Button.VISIBLE);
        panda = true ;

        countdown();


        final TextView textView = (TextView) findViewById(R.id.textView);
       final TextView textView2 = (TextView) findViewById(R.id.textView2);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setEnabled(false);


      }
public void countdown (){

    final TextView textView = (TextView) findViewById(R.id.textView);
    final TextView textView2 = (TextView) findViewById(R.id.textView2);
    alarm=MediaPlayer.create(this,R.raw.ring);


    new CountDownTimer(ts*1000, 1000) {
        public void onTick(long millis) {
            if ( panda) {
                if (sec == 0 && min == 0) {

                } else {


                    if (sec == 0) {
                        min = min - 1;
                        sec = 59;
                    } else {
                        sec = sec - 1;
                    }
                }
            }

            textView.setText(String.valueOf(min));
            textView2.setText(String.valueOf(sec));

        }

        public void onFinish() {
            SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
            seekBar.setEnabled(true);
            Button start = (Button) findViewById(R.id.button);
            Button stop = (Button) findViewById(R.id.button3);
            stop.setVisibility(Button.VISIBLE);
            Button pause = (Button) findViewById(R.id.button2);
            pause.setVisibility(Button.INVISIBLE);



            alarm.start();


        }

    }.start();
}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);






        seekBar.setMax(600);
        seekBar.setProgress(60);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                timerset(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}