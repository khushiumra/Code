package com.example.login_screen;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b,b1;
    EditText ed1,ed2;

    TextView tx;

    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);

        b1 = (Button) findViewById(R.id.button2);
        tx = (TextView) findViewById(R.id.textView);
        tx.setVisibility(View.GONE);

        b.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting..", Toast.LENGTH_SHORT).show();

                    tx.setVisibility(View.VISIBLE);
                    tx.setBackgroundColor(Color.RED);
                    counter--;
                    tx.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b.setEnabled(false);
                    }
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    }