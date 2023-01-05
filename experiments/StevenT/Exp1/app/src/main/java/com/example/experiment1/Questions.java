package com.example.experiment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);


        final int[] total = {0};
        final int[] correct = {0};
        int num1 = (int) (Math.random() *100)+1;
        int num2 = (int) (Math.random() *100)+1;
        final int[] correctAnswer = {(int) (Math.random() * 4)};

        TextView scoreText = findViewById(R.id.score_text);
        TextView resultText = findViewById(R.id.result_text);
        TextView questionText = findViewById(R.id.question_text);
        questionText.setText("What is "+num1+" + "+num2+"?");

        Button backButton = findViewById(R.id.back_button_question);
        Button submitButton = findViewById(R.id.submit_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

        RadioButton[] choices = new RadioButton[4];

        choices[0] = (RadioButton) findViewById(R.id.answer_a);
        choices[1] = (RadioButton) findViewById(R.id.answer_b);
        choices[2] = (RadioButton) findViewById(R.id.answer_c);
        choices[3] = (RadioButton) findViewById(R.id.answer_d);

        for(int i = 0;i<4;i++) {
            if(i== correctAnswer[0])
                choices[i].setText(Integer.toString(num1+num2));
            else
                choices[i].setText(((Integer.toString((int) (Math.random() * 200) + 1))));
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choices[correctAnswer[0]].isChecked()) {
                    resultText.setText("Correct");
                    resultText.setTextColor(-16711936);
                    correct[0]++;
                }
                else {
                    resultText.setText("Incorrect");
                    resultText.setTextColor(-65536);
                }
                total[0]++;
                for(int l = 0;l<4;l++)
                    choices[l].setChecked(false);
                correctAnswer[0] = questionSetup(choices);
                scoreText.setText(correct[0]+" / "+total[0]);

            }
        });


    }
    public int questionSetup(RadioButton[] choices)
    {
        int num1 = (int) (Math.random() *100)+1;
        int num2 = (int) (Math.random() *100)+1;
        int correct = (int)(Math.random()*4);
        TextView questionText = findViewById(R.id.question_text);
        questionText.setText("What is "+num1+" + "+num2+"?");


        choices = new RadioButton[4];

        choices[0] = (RadioButton) findViewById(R.id.answer_a);
        choices[1] = (RadioButton) findViewById(R.id.answer_b);
        choices[2] = (RadioButton) findViewById(R.id.answer_c);
        choices[3] = (RadioButton) findViewById(R.id.answer_d);

        for(int i = 0;i<4;i++) {
            if (i == correct)
                choices[i].setText(Integer.toString(num1 + num2));
            else
                choices[i].setText(((Integer.toString((int) (Math.random() * 200) + 1))));
        }
        return correct;
    }
}
