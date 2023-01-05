package com.example.androidstudiobasics;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Adder extends AppCompatActivity {

    EditText num1;
    EditText num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adder);

        num1 = (EditText) findViewById(R.id.Num1);
        num2 = (EditText) findViewById(R.id.Num2);



        Button backButton = findViewById(R.id.back);

        Button addButton = findViewById(R.id.add);


        TextView AdderText = findViewById(R.id.Adder_text);

        Integer[] sum = {0};

        addButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                int number1 = Integer.parseInt(num1.getText().toString());
                int number2 = Integer.parseInt(num2.getText().toString());
                sum[0] = number1 + number2;
                AdderText.setText(sum[0].toString());

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });



    }
}