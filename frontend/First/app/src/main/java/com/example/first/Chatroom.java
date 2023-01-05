package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Chatroom extends AppCompatActivity  {

    Button bconnect, bsend;
    EditText userText, cMessages;
    TextView text;
    String chatroom;
    SimpleDateFormat date;
    String dateT;

    String username;
    int userId;

    private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        bconnect = (Button) findViewById(R.id.connect);
        bsend = (Button) findViewById(R.id.send);
        userText = (EditText) findViewById(R.id.user);
        cMessages = (EditText) findViewById(R.id.message);
        text = (TextView) findViewById(R.id.text);
        text.setMovementMethod(new ScrollingMovementMethod());
        chatroom = " ";
        date = new SimpleDateFormat("HH:mm, MM-dd-yyyy");
        dateT = " ";

        bconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Draft[] drafts = {
                        new Draft_6455()
                };

                String url = "http://coms-309-021.cs.iastate.edu:8080/actors/" + userId;
                RequestQueue requestQueue = Volley.newRequestQueue(Chatroom.this);
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(0);
                            username = jsonObject.getString("username");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        String ErrorMessage = "";
                        if (error instanceof NetworkError) {
                            ErrorMessage = "Cannot connect to Internet. Check your connection!";
                        } else if (error instanceof ServerError) {
                            ErrorMessage = "Server not found. Please try again later.";
                        } else if (error instanceof AuthFailureError) {
                            ErrorMessage = "Cannot connect to Internet. Check your connection!";
                        } else if (error instanceof ParseError) {
                            ErrorMessage = "Parse Error! Please try again later.";
                        } else if (error instanceof NoConnectionError) {
                            ErrorMessage = "Cannot connect to Internet. Check your connection!";
                        } else if (error instanceof TimeoutError) {
                            ErrorMessage = "Connection TimeOut. Check your connection!";
                        }
                        Toast.makeText(getApplicationContext(),ErrorMessage, Toast.LENGTH_SHORT).show();

                    }
                });

                String w = "ws://coms-309-021.cs.iastate.edu:8080/chat//" + userText.getText().toString();

                try {
                    Log.d("Socket:", "Trying socket");
                    cc = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                        @Override
                        public void onMessage(String message) {
                            Log.d("", "run() returned: " + message);
                            chatroom = chatroom + message;
                            text.setText(chatroom);

                            //To allow the chatroom to view new messages

                            int scrollDown = text.getLayout().getLineTop(text.getLineCount()) - text.getHeight();

                            if(scrollDown > 0)
                            {
                                text.scrollTo(0,scrollDown);
                            }

                            else
                            {
                                text.scrollTo(0,0);
                            }
                        }

                        @Override
                        public void onOpen(ServerHandshake handshake) {
                            Log.d("OPEN", "run() returned: " + "is connecting");
                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote) {
                            Log.d("CLOSE", "onClose() returned: " + reason);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("Exception:", e.toString());
                        }
                    };
                } catch (URISyntaxException e) {
                    Log.d("Exception:", e.getMessage().toString());
                    e.printStackTrace();
                }
                cc.connect();

            }
        });

        bsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dateT = date.format(new Date());
                    cc.send("\n" + username + ":" + dateT + "\n" + cMessages.getText().toString() + "\n");
                    cMessages.setText(" ");

                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(bsend.getApplicationWindowToken(),0);

                    //To get to the bottom of the chat

                    int scrollDown = text.getLayout().getLineTop(text.getLineCount()) - text.getHeight();

                    if(scrollDown > 0)
                    {
                        text.scrollTo(0,scrollDown);
                    }

                    else
                    {
                        text.scrollTo(0,0);
                    }
                }
                catch (Exception e) {
                    Log.d("ExceptionSendMessage:", e.getMessage());
                }
            }
        });
    }

}
