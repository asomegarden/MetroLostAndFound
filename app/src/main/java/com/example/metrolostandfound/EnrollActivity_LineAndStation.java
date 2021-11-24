package com.example.metrolostandfound;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EnrollActivity_LineAndStation extends AppCompatActivity {
    private ArrayAdapter<String> adapterLine;
    private ArrayAdapter<String> adapterStation;
    private Spinner spinnerLine;
    private Spinner spinnerStation;
    private String choice_line, choice_station;

    private int year, month, date;
    private int hour, minute;

    private TextView enrollDateTextView;
    private TextView enrollTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_line);

        Button btnEnroll = (Button) findViewById(R.id.btn_Enroll);

        enrollDateTextView = (TextView) findViewById(R.id.enrollDateTextView);
        enrollTimeTextView = (TextView) findViewById(R.id.enrollTimeTextView);


        //region 날짜/시간 설정
        initDateTime();

        enrollDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        enrollTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime();
            }
        });
        //endregion

        //region spinner 설정

        spinnerLine = (Spinner) findViewById(R.id.enrollLineSpinner);
        spinnerStation = (Spinner) findViewById(R.id.enrollStationSpinner);

        initSpinner();

        spinnerLine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_line = adapterLine.getItem(position).toString();
                if(choice_line.equals("수인분당선")){
                    adapterStation = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, MetroSchedule.getSuinbundangStationList());
                    adapterStation.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinnerStation.setAdapter(adapterStation);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerStation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_station = adapterStation.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //endregion


        //db에 저장
        btnEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 3/4", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                Intent intent2 = new Intent(EnrollActivity_LineAndStation.this, EnrollActivity_Other.class);

                String day = year + ":" + month + ":" + date + ":" + hour + ":" + minute;
                String line = choice_line;
                String station = choice_station;

                intent2.putExtra("station", station);
                intent2.putExtra("date",day);
                intent2.putExtra("line",line);
                intent2.putExtra("mc",intent.getStringExtra("mc"));
                intent2.putExtra("sc",intent.getStringExtra("sc"));
                startActivity(intent2);
                onStop();
            }
        });
    }

    private void showDate() {
        Calendar cal = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int y, int m, int d) {
                year = y;
                month = m + 1;
                date = d;
                enrollDateTextView.setText(" " + year + "년 " + month + "월 " + date + "일" + " ");
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

        datePickerDialog.show();
    }



    private void showTime() {
        Calendar cal = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(this, R.style.TimePickerDialogTheme, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int h, int m) {
                hour = h;
                minute = m;
                enrollTimeTextView.setText(" " + String.format("%02d", hour) + " : " + String.format("%02d", minute) + " ");
            }
        }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), false);

        //dialog.setTitle();
        dialog.show();
    }

    private void initSpinner(){
        List<String> defaultList = new ArrayList<>();
        defaultList.add("모두");

        adapterLine =  new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, MetroSchedule.getLines());
        adapterLine.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        adapterStation = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, defaultList);
        adapterStation.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerLine.setAdapter(adapterLine);
        spinnerStation.setAdapter(adapterStation);
    }

    private void initDateTime(){
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        date = c.get(Calendar.DATE);
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);

        enrollDateTextView.setText(" " + year +"년 "+ month +"월 "+ date + "일 ");
        enrollTimeTextView.setText(" " + String.format("%02d", hour) + " : " + String.format("%02d", minute) + " ");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        onStop();
    }
}
