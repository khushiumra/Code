package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainnotes);

        Button takeNotes = findViewById(R.id.button);
        Button uploadNotes = findViewById(R.id.button2);
        Button goBack = findViewById(R.id.button3);

        uploadNotes.setEnabled(true);

        uploadNotes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(view.getContext(), FileManager.class));
            }
        });

        takeNotes.setEnabled(true);

        takeNotes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(view.getContext(), TakeNotes.class));
            }
        });
goBack.setEnabled(true);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(view.getContext(), Dashboard.class));
            }
        });
    }
}

