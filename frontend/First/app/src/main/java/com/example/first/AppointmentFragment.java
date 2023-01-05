package com.example.first;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class AppointmentFragment extends Fragment {

    private String title;
    public AppointmentFragment(String title) {
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_appointment,container,false);

        Button makeAppoint = (Button) root.findViewById(R.id.makeAppointButton);
        Button createAppoint = root.findViewById(R.id.createAppointment);
        EditText appointName = root.findViewById(R.id.appointCreateName);
        EditText appointDate = root.findViewById(R.id.appointCreateDate);
        ConstraintLayout makeLayout = root.findViewById(R.id.makeAppointLayout);

        makeLayout.setVisibility(View.GONE);
        appointName.setVisibility(View.GONE);
        appointDate.setVisibility(View.GONE);
        createAppoint.setVisibility(View.GONE);

        makeAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeLayout.setVisibility(View.VISIBLE);
                appointName.setVisibility(View.VISIBLE);
                appointDate.setVisibility(View.VISIBLE);
                createAppoint.setVisibility(View.VISIBLE);
                makeAppoint.setVisibility(View.GONE);
            }
        });

        createAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(appointName.getText().toString().length()<1){
                    //Toast.make Text(AppointmentFragment.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                }else if(appointDate.getText().toString().length()<1){
                    //Toast.makeText(Appointment.this, "Please enter a date", Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(Appointment.this, "Appointment made", Toast.LENGTH_SHORT).show();
                    makeLayout.setVisibility(View.GONE);
                    appointName.setVisibility(View.GONE);
                    appointDate.setVisibility(View.GONE);
                    createAppoint.setVisibility(View.GONE);
                    makeAppoint.setVisibility(View.VISIBLE);
                }
            }
        });



        return root;
    }

    private void sendData(String name, String date){
        String url = "http://coms-309-021.class.las.iastate.edu:8080";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JSONObject obj = new JSONObject();
        try{
            obj.put("name",name);
            obj.put("description",date);
        }catch(Exception e){

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
}