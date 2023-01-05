package com.example.first;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Calendar extends AppCompatActivity implements  CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    static JSONArray array;
    static String[] names, startDates, desc,endDates;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planner_screen);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
        String email = getIntent().getStringExtra("email");

        ConstraintLayout layout = findViewById(R.id.eventCreateLayout);
        Button createButton = findViewById(R.id.createEventButton);
        Button finishButton = findViewById(R.id.eventCreation);
        EditText eventName = findViewById(R.id.eventName);
        EditText eventDesc = findViewById(R.id.eventDesc);
        EditText startDate = findViewById(R.id.eventStartDate);
        EditText endDate = findViewById(R.id.eventEndDate);
        endDate.setVisibility(View.GONE);
        startDate.setVisibility(View.GONE);
        eventName.setVisibility(View.GONE);
        eventDesc.setVisibility(View.GONE);
        finishButton.setVisibility(View.GONE);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                endDate.setVisibility(View.VISIBLE);
                startDate.setVisibility(View.VISIBLE);
                eventName.setVisibility(View.VISIBLE);
                eventDesc.setVisibility(View.VISIBLE);
                finishButton.setVisibility(View.VISIBLE);
                createButton.setVisibility(View.GONE);



            }
        });


        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eventName.getText().toString().length()<1){
                    Toast.makeText(Calendar.this, "Please Enter an Event Name", Toast.LENGTH_SHORT).show();
                }else if(eventDesc.getText().toString().length()<1){
                    Toast.makeText(Calendar.this, "Please Enter an Event Description", Toast.LENGTH_SHORT).show();
                }else if(startDate.getText().toString().length()<1 || !isValidDate(startDate.getText().toString()) ){
                    Toast.makeText(Calendar.this, "Please Enter a Valid Start Date", Toast.LENGTH_SHORT).show();
                }else if(endDate.getText().toString().length()<1 || !isValidDate(endDate.getText().toString())){
                    Toast.makeText(Calendar.this, "Please Enter a Valid End Date", Toast.LENGTH_SHORT).show();
                }else{
                    sendData(eventName.getText().toString(),eventDesc.getText().toString(),startDate.getText().toString(),endDate.getText().toString(),email);

                    endDate.setVisibility(View.GONE);
                    startDate.setVisibility(View.GONE);
                    eventName.setVisibility(View.GONE);
                    eventDesc.setVisibility(View.GONE);
                    finishButton.setVisibility(View.GONE);
                    createButton.setVisibility(View.VISIBLE);
                }
            }
        });

        String url = "http://coms-309-021.class.las.iastate.edu:8080/events/"+email;
        RequestQueue queue = Volley.newRequestQueue(Calendar.this);
        StringRequest request = new StringRequest(Request.Method.GET,url,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    array = new JSONArray(response);
                    names = new String[array.length()];
                    desc = new String[array.length()];
                    startDates = new String[array.length()];
                    endDates = new String[array.length()];
                    for(int i = 0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        names[i] = object.getString("name");
                        desc[i] = object.getString("description");
                        startDates[i] = object.getString("start");
                        endDates[i] = object.getString("end");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                    }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                if (error instanceof ServerError) {
                    Toast.makeText(Calendar.this, "Server", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(Calendar.this, "Network", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(Calendar.this, "Auth", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(Calendar.this, "Parse", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(Calendar.this, "No Connection", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(Calendar.this, "T/O", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Calendar.this, "Unknown", Toast.LENGTH_SHORT).show();
                }
            }
        });
        queue.add(request);

    }

    private void sendData(String name, String desc, String start, String end, String email){
        String url = "http://coms-309-021.class.las.iastate.edu:8080/events/"+email+"/"+name+"/"+desc+"/"+start+"/"+end;
        RequestQueue queue = Volley.newRequestQueue(Calendar.this);
        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();
        try{
            obj.put("name",name);
            obj.put("description",desc);
            obj.put("start",start);
            obj.put("end",end);
            obj.put("user",email);
        }catch(Exception e){

        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(Calendar.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         if (error instanceof ServerError) {
                             Toast.makeText(Calendar.this, "Server", Toast.LENGTH_SHORT).show();
                         } else if (error instanceof NetworkError) {
                             Toast.makeText(Calendar.this, "Network", Toast.LENGTH_SHORT).show();
                         } else if (error instanceof AuthFailureError) {
                             Toast.makeText(Calendar.this, "Auth", Toast.LENGTH_SHORT).show();
                         } else if (error instanceof ParseError) {
                             Toast.makeText(Calendar.this, "Parse", Toast.LENGTH_SHORT).show();
                         } else if (error instanceof NoConnectionError) {
                             Toast.makeText(Calendar.this, "No Connection", Toast.LENGTH_SHORT).show();
                         } else if (error instanceof TimeoutError) {
                             Toast.makeText(Calendar.this, "T/O", Toast.LENGTH_SHORT).show();
                         } else {
                             Toast.makeText(Calendar.this, "Unknown", Toast.LENGTH_SHORT).show();
                         }
                     }
        });
        queue.add(jsonObjReq);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isValidDate(String inDate) {
        return true;
        /*
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH-mm");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;

         */
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);

        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i<=42;i++){
            if(i<= dayOfWeek || i> daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i-dayOfWeek));
            }

        }
        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calenderRecycleView);
        monthYearText = findViewById(R.id.monthYearText);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {
        int x = hasEvent(dayText);
        if(!dayText.equals("") && x>=0){
            String message = names[x] +"until "+ endDates[x];
            Toast.makeText(this,message, Toast.LENGTH_LONG).show();
        }
    }
    public String getDay(int i){
        return (startDates[i].substring(0,1));
    }
    public int hasEvent(String dayText){
        for(int i =0;i<startDates.length;i++)
        {
            if(getDay(i).equals(dayText))
                return i;
        }
        return -1;
    }

}
