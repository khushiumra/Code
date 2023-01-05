package com.example.first;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;

public class Dashboard2 extends AppCompatActivity {
    static JSONArray array;
    static String[] names;
    boolean if3was=false,if4was=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String email = getIntent().getStringExtra("email");
        setContentView(R.layout.dashboard2_activity);
        Button groupOne = findViewById(R.id.GroupOne);
        Button groupTwo = findViewById(R.id.GroupTwo);
        Button groupThree = findViewById(R.id.groupThree);
        Button groupFour = findViewById(R.id.groupFour);
        Button groupFive = findViewById(R.id.groupFive);
        Button groupSix = findViewById(R.id.groupSix);
        Button createButton = findViewById(R.id.newGroup);
        Button finalButton = findViewById(R.id.finalGroupButton);
        EditText groupText = findViewById(R.id.createGroupText);

        groupText.setVisibility(View.GONE);
        finalButton.setVisibility(View.GONE);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupText.setVisibility(View.VISIBLE);
                finalButton.setVisibility(View.VISIBLE);
                groupThree.setVisibility(View.INVISIBLE);
                groupFour.setVisibility(View.INVISIBLE);
                groupThree.setEnabled(false);
                groupFour.setEnabled(false);
            }
        });

        finalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData(groupText.getText().toString(),email);
                groupText.setVisibility(View.GONE);
                groupThree.setVisibility(View.VISIBLE);
                groupFour.setVisibility(View.VISIBLE);
                if(if3was)
                groupThree.setEnabled(true);
                if(if4was)
                groupFour.setEnabled(true);
                finalButton.setVisibility(View.GONE);
                groupText.setText("");
    

            }
        });

        groupOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Dashboard.class);
                intent.putExtra("group",groupOne.getText().toString());
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        groupTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Dashboard.class);
                intent.putExtra("group",groupTwo.getText().toString());
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        groupThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Dashboard.class);
                intent.putExtra("group",groupThree.getText().toString());
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        groupFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Dashboard.class);
                intent.putExtra("group",groupFour.getText().toString());
                intent.putExtra("email",email);
                startActivity(intent);

            }
        });
        groupFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Dashboard.class);
                intent.putExtra("group",groupFive.getText().toString());
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        groupSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Dashboard.class);
                intent.putExtra("group",groupSix.getText().toString());
                intent.putExtra("email",email);
                startActivity(intent);

            }
        });

        String url = "http://coms-309-021.class.las.iastate.edu:8080/groups/"+email;
        RequestQueue queue = Volley.newRequestQueue(Dashboard2.this);
        List<Assignment.Assignments> list = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET,url,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(Dashboard2.this,"hi",Toast.LENGTH_SHORT);
                    array = new JSONArray(response);
                    names = new String[array.length()];
                    for(int i = 0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        names[i] = object.getString("name");
                    }
                        try{
                            groupOne.setText(names[0]);
                            groupOne.setEnabled(true);
                            groupTwo.setText(names[1]);
                            groupTwo.setEnabled(true);
                            groupThree.setText(names[2]);
                            groupThree.setEnabled(true);
                            if3was=true;
                            groupFour.setText(names[3]);
                            groupFour.setEnabled(true);
                            if4was=true;
                            groupFive.setText(names[4]);
                            groupFive.setEnabled(true);
                            groupSix.setText(names[5]);
                            groupSix.setEnabled(true);

                        }catch(ArrayIndexOutOfBoundsException e){


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                if (error instanceof ServerError) {
                    Toast.makeText(Dashboard2.this, "Server", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(Dashboard2.this, "Network", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(Dashboard2.this, "Auth", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(Dashboard2.this, "Parse", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(Dashboard2.this, "No Connection", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(Dashboard2.this, "T/O", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Dashboard2.this, "Unknown", Toast.LENGTH_SHORT).show();
                }
            }
        });
        queue.add(request);

    }
    public void sendData(String name, String email){
        String postUrl = "http://coms-309-021.class.las.iastate.edu:8080/groups/"+email+"/"+name;
        RequestQueue requestQueue = Volley.newRequestQueue(Dashboard2.this);
        JSONObject object = new JSONObject();
        try {
            object.put("user",email);
            object.put("group",name);
        }catch(Exception e) {

        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                postUrl,null,
                new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("message").equals("success")) {
                                Toast.makeText(Dashboard2.this, "Success", Toast.LENGTH_SHORT).show();
                            }else
                                Toast.makeText(Dashboard2.this, "Failure", Toast.LENGTH_SHORT).show();
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof ServerError) {
                    Toast.makeText(Dashboard2.this, "Server", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(Dashboard2.this, "Network", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(Dashboard2.this, "Auth", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(Dashboard2.this, "Parse", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(Dashboard2.this, "No Connection", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(Dashboard2.this, "T/O", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Dashboard2.this, "Unknown", Toast.LENGTH_SHORT).show();

                }
            }

        });
        requestQueue.add(jsonObjReq);

    }

}
