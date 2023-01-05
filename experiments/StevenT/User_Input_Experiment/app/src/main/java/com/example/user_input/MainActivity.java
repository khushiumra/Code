package com.example.user_input;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterButton = findViewById(R.id.enter_button);
        EditText name = findViewById(R.id.name_input);


        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals(""))
                    ;
                else {
                    Intent i = new Intent(view.getContext(), HomePage.class);
                    i.putExtra("name",name.getText().toString());
                    startActivity(i);
                }
            }
        });


    }
}