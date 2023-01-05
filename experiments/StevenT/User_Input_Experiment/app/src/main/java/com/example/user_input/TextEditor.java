package com.example.user_input;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TextEditor extends AppCompatActivity {
    private int size = 28;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_edit);


        Button increaseSize = findViewById(R.id.increase_button);
        Button decreaseSize = findViewById(R.id.decrease_button);
        Button boldText = findViewById(R.id.bold_Button);
        Button normalButton = findViewById(R.id.normal_button);
        Button italicText = findViewById(R.id.italic_button);
        EditText input = findViewById(R.id.input_text);


        increaseSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size++;
                input.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);

            }
        });

        decreaseSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size--;
                input.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);

            }
        });

        boldText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setTypeface(null, Typeface.BOLD);
            }
        });

        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setTypeface(null, Typeface.NORMAL);
            }
        });

        italicText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setTypeface(null, Typeface.ITALIC);
            }
        });


    }
}
