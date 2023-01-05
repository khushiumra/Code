package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class createNotes extends AppCompatActivity {

    private EditText inputTitle, noteSubtitle, inputNote;
    private TextView textDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);


        ArrayList<String> temp = new ArrayList<>();
        boolean first = true;

        ImageView imageSave = findViewById(R.id.imageSave);

        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                inputTitle.setVisibility(View.VISIBLE);
                textDateTime.setVisibility(View.VISIBLE);
                startActivity(new Intent(getApplicationContext(), TakeNotes.class));
            }
        });

        ImageView imageBack = findViewById(R.id.imageback);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                onBackPressed();
            }
        });

        inputTitle = findViewById(R.id.inputTitle);
        noteSubtitle = findViewById(R.id.noteSubtitle);
        inputNote = findViewById(R.id.inputNote);
        textDateTime = findViewById(R.id.textDateTime);

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())
        );

    }

    private void sendData(String title, String date, String text)
    {
        String url = "http://coms-309-021.class.las.iastate.edu:8080";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JSONObject obj = new JSONObject();
        try{
            obj.put("title",title);
            obj.put("date",date);
            obj.put("note",text);
        }
        catch(Exception e)
        {

        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjReq);
    }

    private Context getActivity() {
        return null;
    }

    private void saveNotes()
    {
        if(inputTitle.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this, "Note title can't be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        else if(noteSubtitle.getText().toString().trim().isEmpty()
                && inputNote.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this, "Note can't be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        setTitle(inputTitle.getText().toString());
        // setSubtitle(noteSubtitle.getText().toString());
       //  setNoteText(inputNote.getText().toString());
       // setDateTime(textDateTime.getText().toString());

    }
}