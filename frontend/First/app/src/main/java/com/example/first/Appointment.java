package com.example.first;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Appointment extends AppCompatActivity {

    private String[] data = {"Appointments","Teams"};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_activity);

        ViewPager2 viewPage = findViewById(R.id.viewPager);
        viewPage.setAdapter(
                new FragAdapt(this)
        );
        TabLayout appTabs = findViewById(R.id.appointTabs);
        new TabLayoutMediator(
                appTabs,
                viewPage,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(data[position]);
                        tab.setIcon(R.drawable.ic_launcher_foreground);
                    }
                }
        ).attach();
/*
        Button makeAppoint = findViewById(R.id.makeAppointButton);
        Button createAppoint = findViewById(R.id.createAppointment);
        EditText appointName = findViewById(R.id.appointCreateName);
        EditText appointDate = findViewById(R.id.appointCreateDate);
        ConstraintLayout makeLayout = findViewById(R.id.makeAppointLayout);

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
                    Toast.makeText(Appointment.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                }else if(appointDate.getText().toString().length()<1){
                    Toast.makeText(Appointment.this, "Please enter a date", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Appointment.this, "Appointment made", Toast.LENGTH_SHORT).show();
                    makeLayout.setVisibility(View.GONE);
                    appointName.setVisibility(View.GONE);
                    appointDate.setVisibility(View.GONE);
                    createAppoint.setVisibility(View.GONE);
                    makeAppoint.setVisibility(View.VISIBLE);
                }
            }
        });
        */
    }
    class FragAdapt extends FragmentStateAdapter {

        public FragAdapt(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        public FragAdapt(@NonNull Fragment fragment) {
            super(fragment);
        }

        public FragAdapt(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if(position == 0)
                return new AppointmentFragment(data[position]);
            else
                return new TeamFragment(data[position]);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }
    }
}
