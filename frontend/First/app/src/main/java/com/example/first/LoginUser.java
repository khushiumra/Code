package com.example.first;

import android.content.Intent;
import android.os.Bundle;
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


import org.json.JSONException;
import org.json.JSONObject;

public class LoginUser extends AppCompatActivity {

    protected int userName;
    protected int userType;

    TextView LoadingBar;
    static boolean cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registrationBut = findViewById(R.id.createA);
        System.out.println("Registration Screen");

        registrationBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Registration.class));
            }
        });

        Button dashboardBut = findViewById(R.id.button4);

        dashboardBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingBar = (TextView) findViewById(R.id.loading);
                EditText email = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);

                if (email.getText().toString().length() != 0 && password.getText().toString().length() != 0) {
                    LoadingBar.setVisibility(View.VISIBLE);
                    sendData(v,email.getText().toString(),password.getText().toString());
                    if(cont){
                    }
                }
            }
        });

        LoadingBar = (TextView) findViewById(R.id.loading);
        LoadingBar.setVisibility(View.GONE);
    }
    public void sendData(View v,String email, String pass){
        String postUrl = "http://coms-309-021.class.las.iastate.edu:8080/users";
        RequestQueue requestQueue = Volley.newRequestQueue(LoginUser.this);

        postUrl = postUrl + "/" + email + "/" + pass;


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                postUrl,null,
                new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(LoginUser.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            if(response.getString("message").equals("success")){
                                Intent intent = new Intent(v.getContext(),Dashboard2.class);
                                intent.putExtra("email",email);
                                startActivity(intent);
                            }else{
                                cont = false;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof ServerError) {
                    Toast.makeText(LoginUser.this, "Server", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(LoginUser.this, "Network", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(LoginUser.this, "Auth", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(LoginUser.this, "Parse", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(LoginUser.this, "No Connection", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(LoginUser.this, "T/O", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginUser.this, "Unknown", Toast.LENGTH_SHORT).show();

                }
            }

        });
        requestQueue.add(jsonObjReq);
    }
}
