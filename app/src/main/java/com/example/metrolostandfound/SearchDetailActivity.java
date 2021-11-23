package com.example.metrolostandfound;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class SearchDetailActivity extends AppCompatActivity {
/*
    private ArrayAdapter lineadapter;
    private ArrayAdapter stationadapter;
    private ArrayAdapter locationadapter;
    private Spinner linespinner;
    private Spinner stationspinner;
    private Spinner locationspinner;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        /*
        linespinner = (Spinner) findViewById(R.id.lineSpinner);
        stationspinner = (Spinner) findViewById(R.id.stationSpinner);
        locationspinner = (Spinner) findViewById(R.id.locationSpinner);

        lineadapter = ArrayAdapter.createFromResource(this, R.array.lineArray, android.R.layout.simple_dropdown_item_1line);
        linespinner.setAdapter(lineadapter);

        locationadapter = ArrayAdapter.createFromResource(this, R.array.locationArray, android.R.layout.simple_dropdown_item_1line);
        locationspinner.setAdapter(locationadapter);

        linespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(linespinner.getSelectedItem().equals("수인분당선")){
                    stationadapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.suin_bundangStationArray, android.R.layout.simple_dropdown_item_1line);
                    stationspinner.setAdapter(stationadapter);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */
    }

}
