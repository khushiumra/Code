package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitButton = findViewById(R.id.registration_submit_button);
        EditText emailInput = findViewById(R.id.registration_email_input);
        EditText emailConfirmInput = findViewById(R.id.registration_confirm_email_input);
        EditText usernameInput = findViewById(R.id.registration_username_input);
        EditText passwordInput = findViewById(R.id.registration_password_input);
        EditText passwordConfirmInput = findViewById(R.id.registration_confirm_password_input);
        TextView errorMessage = findViewById(R.id.registration_error_text);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorMessage.setText("");
                boolean allGood = true;
                if(passwordInput.getText().toString().equals("")) {
                    allGood = false;
                    errorMessage.setText(errorMessage.getText() + "\nPassword is required");
                }else{
                    //check if password length < 8
                    if(passwordInput.getText().toString().length()<8){
                        allGood = false;
                        errorMessage.setText(errorMessage.getText()+"\nPassword too short");
                    }
                    //check if password = confirm password
                    if(!(passwordInput.getText().toString().equals(passwordConfirmInput.getText().toString()))){
                        allGood = false;
                        errorMessage.setText(errorMessage.getText()+"\nConfirm Password must match Password");
                    }
                    //checks if password has a number
                    if(passwordChecker("[0-9]",passwordInput.getText().toString())){
                        allGood = false;
                        errorMessage.setText(errorMessage.getText()+"\nPassword must contain a number");
                    }
                    //checks if password has uppercase letter
                    if(passwordChecker("[A-Z]",passwordInput.getText().toString())){
                        allGood = false;
                        errorMessage.setText(errorMessage.getText()+"\nPassword must contain an uppercase letter");
                    }
                    if(passwordChecker("[a-z]",passwordInput.getText().toString())){
                        allGood = false;
                        errorMessage.setText(errorMessage.getText()+"\nPassword must contain an lowercase letter");
                    }
                }
                if(emailInput.getText().toString().equals("")) {
                    allGood = false;
                    errorMessage.setText(errorMessage.getText() + "\nEmail is required");
                }else{
                    if(!(emailInput.getText().toString().equals(emailConfirmInput.getText().toString()))){
                        allGood = false;
                        errorMessage.setText(errorMessage.getText()+"\nConfirm Email must match Email");
                    }
                    if(emailFormat(emailInput.getText().toString())){
                        allGood = false;
                        errorMessage.setText(errorMessage.getText()+"\nPlease enter a valid email");
                    }
                }
                if(allGood)
                    sendData(emailInput.getText().toString(),usernameInput.getText().toString(),passwordInput.getText().toString());
            }
        });
    }
    public boolean passwordChecker(String check, String text){
        Pattern pattern = Pattern.compile(check);
        Matcher match = pattern.matcher(text);
        if(match.find())
            return false;
        return true;
    }
    public boolean emailFormat(String text){
        int atIndex = text.indexOf("@");

        if(atIndex == -1 || atIndex != text.lastIndexOf("@"))
            return true;
        if(text.indexOf(".",atIndex)==-1 || text.indexOf(".",atIndex)==text.length()-1)
            return true;
        return false;
    }
    private void sendData(String email, String password, String user){
        String url = "https://f452470b-3812-4964-92e6-5feecd33a4cb.mock.pstmn.io/registration";
        RequestQueue queue = Volley.newRequestQueue(Registration.this);

        StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Toast.makeText(Registration.this,"Success",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(Registration.this,"Failed",Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params=new HashMap<String,String>();
                params.put("User", user);
                params.put("email",email);
                params.put("pass",password);
                return params;
            }
        };
        queue.add(request);
    }
}