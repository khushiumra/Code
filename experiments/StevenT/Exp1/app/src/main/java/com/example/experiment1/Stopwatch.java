package com.example.experiment1;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

public class Stopwatch extends AppCompatActivity {
    private Chronometer timer;
    private long pauseOffset;
    private boolean on;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch);

        Button backButton = findViewById(R.id.back_button_stopwatch);
        Button startButton = findViewById(R.id.start_button);
        Button stopButton = findViewById(R.id.stop_button);
        Button resetButton = findViewById(R.id.reset_button);
        timer = findViewById(R.id.chronometer);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer(view);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer(view);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer(view);
            }
        });

    }

    public void startTimer(View view){
        if(!on){
            timer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            timer.start();
            on = true;
        }
    }

    public void stopTimer(View view){
        if(on){
            pauseOffset = SystemClock.elapsedRealtime() - timer.getBase();
            timer.stop();
            on = false;
        }
    }
    public void resetTimer(View v){
        if(on){
            timer.stop();
            on = false;
        }
        timer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}
