package com.example.metrolostandfound;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchDetailActivity extends AppCompatActivity {

    private TextView searchDetailDateTextView1;
    private TextView searchDetailDateTextView2;

    private TextView searchDetailTimeTextView1;
    private TextView searchDetailTimeTextView2;

    private int year, month, date;
    private int hour, minute;

    private Spinner searchSpinnerLine, searchSpinnerStation, searchSpinnerBig, searchSpinnerSmall;
    private ArrayAdapter<String> searchAdapterLine, searchAdapterStation, searchAdapterBig, searchAdapterSmall;

    private String choice_line = "모두", choice_station ="모두";
    private String choice_big = "모두", choice_small = "모두";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        searchDetailDateTextView1 = (TextView) findViewById(R.id.searchDetailDateTextView1);
        searchDetailDateTextView2 = (TextView) findViewById(R.id.searchDetailDateTextView2);
        searchDetailTimeTextView1 = (TextView) findViewById(R.id.searchDetailTimeTextView1);
        searchDetailTimeTextView2 = (TextView) findViewById(R.id.searchDetailTimeTextView2);

        List<String> defaultList = new ArrayList<>();
        defaultList.add("모두");
        searchSpinnerLine = (Spinner) findViewById(R.id.searchLineSpinner);
        searchSpinnerStation = (Spinner) findViewById(R.id.searchStationSpinner);
        searchSpinnerBig = (Spinner) findViewById(R.id.searchBigProductSpinner);
        searchSpinnerSmall = (Spinner) findViewById(R.id.searchSmallProductSpinner);
        clear();



        searchDetailDateTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate1();
            }
        });
        searchDetailDateTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate2();
            }
        });

        searchDetailTimeTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime1();
            }
        });

        searchDetailTimeTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime2();
            }
        });

        searchSpinnerLine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_line = searchAdapterLine.getItem(position).toString();
                if(choice_line.equals("모두")){
                    searchAdapterStation = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, defaultList);
                    searchSpinnerStation.setAdapter(searchAdapterStation);
                }
                else if(choice_line.equals("수인분당선")){
                    List<String> stationList = new ArrayList<>();
                    stationList.add("모두");
                    stationList.addAll(MetroSchedule.getSuinbundangStationList());

                    searchAdapterStation = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, stationList);
                    searchSpinnerStation.setAdapter(searchAdapterStation);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchSpinnerStation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_station = searchAdapterStation.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchSpinnerBig.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_big = searchAdapterBig.getItem(position).toString();
                if(choice_big.equals("모두")){
                    searchAdapterSmall = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, defaultList);
                    searchSpinnerSmall.setAdapter(searchAdapterSmall);
                }
                else{
                    List<String> subList = new ArrayList<>();
                    subList.add("모두");
                    subList.addAll(ObjectCategory.getSubCategories(choice_big));

                    searchAdapterSmall = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, subList);
                    searchSpinnerSmall.setAdapter(searchAdapterSmall);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /* 안필요한가?(EnrollActivity too)
        searchSpinnerSmall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_small = searchAdapterSmall.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */
    }

    private void showDate1() {
        Calendar cal = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int y, int m, int d) {
                year = y;
                month = m + 1;
                date = d;
                searchDetailDateTextView1.setText(" " + year +"/"+ month +"/ "+ date + " ");
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

        datePickerDialog.show();
    }

    private void showDate2() {
        Calendar cal = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int y, int m, int d) {
                year = y;
                month = m + 1;
                date = d;
                searchDetailDateTextView2.setText(" " + year +"/"+ month +"/ "+ date + " ");
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

        datePickerDialog.show();
    }

    private void showTime1() {
        Calendar cal = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(this, R.style.TimePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int h, int m) {
                hour = h;
                minute = m;
                searchDetailTimeTextView1.setText(" " + String.format("%02d", hour) + " : " + String.format("%02d", minute) + " ");
            }
        }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), false);

        //dialog.setTitle();
        dialog.show();
    }

    private void showTime2() {
        Calendar cal = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(this, R.style.TimePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int h, int m) {
                hour = h;
                minute = m;
                searchDetailTimeTextView2.setText(" " + String.format("%02d", hour) + " : " + String.format("%02d", minute) + " ");
            }
        }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), false);

        //dialog.setTitle();
        dialog.show();
    }

    private void clear(){
        initDateTime();
        initSpinner();

        choice_big = "모두";
        choice_small = "모두";
        choice_line = "모두";
        choice_station = "모두";
    }

    private  void initDateTime(){
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        date = c.get(Calendar.DATE);
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);

        searchDetailDateTextView1.setText(" " + year +"/"+ month +"/ "+ date + " ");
        searchDetailDateTextView1.setText(" " + year +"/"+ month +"/ "+ date + " ");
        searchDetailTimeTextView1.setText(" " + String.format("%02d", hour) + " : " + String.format("%02d", minute) + " ");
        searchDetailTimeTextView2.setText(" " + String.format("%02d", hour) + " : " + String.format("%02d", minute) + " ");
    }

    private void initSpinner(){
        List<String> defaultList1 = new ArrayList<>();
        List<String> defaultList2 = new ArrayList<>();
        List<String> lineList = new ArrayList<>();
        List<String> cateList = new ArrayList<>();

        defaultList1.add("모두");
        defaultList2.add("모두");
        lineList.add("모두");

        lineList.addAll(MetroSchedule.getLines());

        cateList.add("모두");
        cateList.addAll(ObjectCategory.getMainCategories());

        searchAdapterLine =  new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, lineList);
        searchAdapterStation = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, defaultList1);
        searchAdapterBig = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cateList);
        searchAdapterSmall = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, defaultList2);

        searchSpinnerLine.setAdapter(searchAdapterLine);
        searchSpinnerStation.setAdapter(searchAdapterStation);
        searchSpinnerBig.setAdapter(searchAdapterBig);
        searchSpinnerSmall.setAdapter(searchAdapterSmall);
    }

    @Override
    public void onBackPressed () {


        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
        onStop();
    }
}
