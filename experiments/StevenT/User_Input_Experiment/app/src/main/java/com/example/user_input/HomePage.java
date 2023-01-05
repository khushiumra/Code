package com.example.user_input;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        TextView welcomeMessage = findViewById(R.id.welcome_text);
        Button drawButton = findViewById(R.id.drawing_button);
        Button textEditButton = findViewById(R.id.text_edit_button);


        welcomeMessage.setText("Welcome "+getIntent().getStringExtra("name"));
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Painter.class);
                intent.putExtra("named",HomePage.class);
                startActivity(intent);
            }
        });

        textEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), TextEditor.class));
            }
        });

    }
}