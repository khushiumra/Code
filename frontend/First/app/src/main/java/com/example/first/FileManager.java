package com.example.first;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class FileManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        Button storageButton = findViewById(R.id.storageButton);
        Button backNotes = findViewById(R.id.backto_notes);
        Button backDash = findViewById(R.id.backto_dashboard);

        backNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(view.getContext(),MainNotes.class));
            }
        });

        backDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),Dashboard.class));
            }
        });

        storageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(checkPermission())
                {
                    //permission allowed
                    Intent intent = new Intent(FileManager.this, FileList.class);
                    String path = Environment.getExternalStorageDirectory().getPath();
                    intent.putExtra("path",path);
                    startActivity(intent);
                }
                else
                {
                    //permission denied
                    requestPermission();
                }
            }
        });
    }

    private boolean checkPermission()
    {
        int result = ContextCompat.checkSelfPermission(FileManager.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission()
    {

        if(ActivityCompat.shouldShowRequestPermissionRationale(FileManager.this,Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            Toast.makeText(FileManager.this, "Storage Permission is required!", Toast.LENGTH_SHORT).show();
        }

        else
        ActivityCompat.requestPermissions(FileManager.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
    }
}
