package com.example.androiddemo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    protected EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);


    }

    private void write(String w){
        String old = display.getText().toString();
        if(getString(R.string.display).equals((display.getText().toString()))){
            display.setText(w);
        }
        else{
            display.setText(old + w);
        }
    }

    public void zero(View view){
        write("0");
    }

    public void one(View view){
        write("1");
    }
    public void two(View view){
        write("2");
    }
    public void three(View view){
        write("3");
    }
    public void four(View view){
        write("4");
    }
    public void five(View view){
        write("5");
    }
    public void six(View view){
        write("6");
    }
    public void seven(View view){
        write("7");
    }

    public void eight(View view){
        write("8");
    }

    public void nine(View view){
        write("9");
    }

    public void clear(View view){
        display.setText("");
    }

    public void plus(View view){
        write("+");
    }

    public void minus(View view){
        write("-");
    }

    public void multiply(View view){
        write("*");
    }

    public void divide(View view){
        write("/");
    }

    public void mod(View view){
        write("%");
    }

    public void dot(View view){
        write(".");
    }

    public void rand(View view){
        Random rand = new Random();
        display.setText(String.valueOf(rand.nextInt()));
    }

    public void equal(View view) throws IOException {
        //File file = new File("equals.java");
        //FileWriter writer = new FileWriter(file);
        /**
         * writer.write("package com.example.androiddemo;\n" +
         *                 "\n" +
         *                 "public class equals {\n" +
         *                 "\n" +
         *                 "    public static String calc(){\n" +
         *                 "\n" +
         *                 "        return String.valueOf("+ display.getText() +");\n" +
         *                 "    }\n" +
         *                 "\n" +
         *                 "}\n");
         */

        display.setText(equals.calc());
        //writer.close();
    }


    public void delete(View view){
        String input = display.getText().toString();
        String output = input.substring(0, input.length() - 1);
        display.setText(output);
    }


}