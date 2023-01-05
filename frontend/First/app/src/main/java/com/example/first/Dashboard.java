package com.example.first;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String email = getIntent().getStringExtra("email");
        setContentView(R.layout.dashboard_activity);

        Button assignmentButton = findViewById(R.id.assignmentButton);
        Button noteButton = findViewById(R.id.notesButton);
        Button calendarButton = findViewById(R.id.calendarButton);
        Button chatRoom = findViewById(R.id.chatroom);
        Button appointment = findViewById(R.id.appointmentDash);

        assignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),Assignment.class));
            }
        });
        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),MainNotes.class));
            }
        });
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),Calendar.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        chatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),Chatroom.class));
            }
        });
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),Appointment.class));
            }
        });

    }
}
