package com.example.workspace;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner spinner = (Spinner) findViewById(R.id.spinner);

    public void onItemSelected(AdapterView<?> parent, View view, int pos,  long id)
    {
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent)
    {

    }


}
