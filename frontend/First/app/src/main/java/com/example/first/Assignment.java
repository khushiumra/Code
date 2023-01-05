package com.example.first;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assignment extends AppCompatActivity {
    static JSONArray array;
    static String[] names;
    static String[] dates;
    static String[] course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_activity);
        ArrayList<String> temp = new ArrayList<>();
        boolean first = true;
        TextView assignmentOne = findViewById(R.id.assignment1);
        TextView assignmentTwo = findViewById(R.id.assignment2);
        TextView assignmentThree = findViewById(R.id.assignment3);
        TextView assignmentFour = findViewById(R.id.assignment4);
        TextView dueOne = findViewById(R.id.dueDate1);
        TextView dueTwo = findViewById(R.id.dueDate2);
        TextView dueThree = findViewById(R.id.dueDate3);
        TextView dueFour = findViewById(R.id.dueDate4);
        EditText assignmentName = findViewById(R.id.AssignmentNameText);
        EditText assignmentDate = findViewById(R.id.AssignmentDateText);
        Button createButton = findViewById(R.id.createAssignmentButton);
        Button finalCreation = findViewById(R.id.finalAssignmentButton);
        Button prevButton = findViewById(R.id.assignmentPrevButton);
        Button nextButton = findViewById(R.id.assignmentNextButton);
        String url = "http://coms-309-021.class.las.iastate.edu:8080/assignments";
        final int[] counter = {0};
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter[0] >= 4){
                    try{
                        assignmentOne.setText(temp.get((counter[0] -4)));
                        counter[0]--;
                        assignmentTwo.setText(temp.get((counter[0] -4)));
                        counter[0]--;
                        assignmentThree.setText(temp.get((counter[0] -4)));
                        counter[0]--;
                        assignmentFour.setText(temp.get((counter[0] -4)));
                        counter[0]--;
                    }catch(ArrayIndexOutOfBoundsException e){

                    }

                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter[0] >=temp.size()){
                    try{
                        assignmentOne.setText(names[counter[0]] +1);
                        dueOne.setText(dates[counter[0]+1]);
                        counter[0]++;
                        assignmentTwo.setText(names[counter[0]] +1);
                        dueTwo.setText(dates[counter[0]+1]);
                        counter[0]++;
                        assignmentThree.setText(names[counter[0]] +1);
                        dueThree.setText(dates[counter[0]+1]);
                        counter[0]++;
                        assignmentFour.setText(names[counter[0]] +1);
                        dueFour.setText(dates[counter[0]+1]);
                        counter[0]++;
                    }catch(ArrayIndexOutOfBoundsException e){

                    }
                }
            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignmentName.setVisibility(View.VISIBLE);
                assignmentDate.setVisibility(View.VISIBLE);
                finalCreation.setVisibility(View.VISIBLE);
            }
        });
        finalCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignmentName.setVisibility(View.GONE);
                assignmentDate.setVisibility(View.GONE);
                sendAssignment(url,assignmentName.getText().toString(),assignmentDate.getText().toString());
                finalCreation.setVisibility(View.GONE);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(Assignment.this);
        List<Assignments> list = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET,url,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(Assignment.this,"hi",Toast.LENGTH_SHORT);
                    array = new JSONArray(response);
                    names = new String[array.length()];
                    dates = new String[array.length()];
                    course = new String[array.length()];
                    for(int i = 0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        names[i] = object.getString("name");
                        dates[i] = object.getString("date");
                        course[i] = object.getString("course");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    assignmentOne.setText(names[counter[0]]);
                    dueOne.setText(dates[counter[0]]);
                    counter[0]++;
                    assignmentTwo.setText(names[counter[0]]);
                    dueTwo.setText(dates[counter[0]]);
                    counter[0]++;
                    assignmentThree.setText(names[counter[0]]);
                    dueThree.setText(dates[counter[0]]);
                    counter[0]++;
                    assignmentFour.setText(names[counter[0]]);
                    dueFour.setText(dates[counter[0]]);
                    counter[0]++;

                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                if (error instanceof ServerError) {
                    Toast.makeText(Assignment.this, "Network", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(Assignment.this, "Server", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(Assignment.this, "Auth", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(Assignment.this, "Parse", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(Assignment.this, "No Connection", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(Assignment.this, "T/O", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Assignment.this, "Unknown", Toast.LENGTH_SHORT).show();
                }
            }
        });
        queue.add(request);
    }

    private void sendAssignment(String url,String name,String date){
        RequestQueue queue = Volley.newRequestQueue(Assignment.this);
        JSONObject obj = new JSONObject();
        try {


            obj.put("name", name);
            obj.put("date",date);
            obj.put("course", "COMS309");
        }catch(Exception e){

        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, obj,
                new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject response) {
                        Toast.makeText(Assignment.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof ServerError) {
                    Toast.makeText(Assignment.this, "Server", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(Assignment.this, "Network", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(Assignment.this, "Auth", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(Assignment.this, "Parse", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(Assignment.this, "No Connection", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(Assignment.this, "T/O", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Assignment.this, "Unknown", Toast.LENGTH_SHORT).show();

                }
            }

        });
        queue.add(jsonObjReq);
    }
    public class Assignments{
        String name;
        public Assignments(String name){
            this.name=name;
        }
    }
}
