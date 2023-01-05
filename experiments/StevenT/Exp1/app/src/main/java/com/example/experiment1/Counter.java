package com.example.experiment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Counter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter);

        Button backButton = findViewById(R.id.back_button);
        Button increaseButton = findViewById(R.id.increase_button);
        Button decreaseButton = findViewById(R.id.decrease_button);
        TextView counterText = findViewById(R.id.counter_text);

        Integer[] count = {0};

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count[0]++;
                counterText.setText(count[0].toString());
            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count[0]--;
                counterText.setText(count[0].toString());
            }
        });

    }

}
