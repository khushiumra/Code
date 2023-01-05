package com.example.user_input;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class Painter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PaintView paint = new PaintView(this);
        setContentView(paint);

    }
}