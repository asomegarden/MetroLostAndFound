package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EnrollActivity extends AppCompatActivity {

    private ArrayAdapter adapterLine, adapterStation, adapterBig, adapterSmall;
    private Spinner spinnerLine, spinnerStation, spinnerBig, spinnerSmall;
    private String choice_line = "";
    private String choice_station ="";
    private String choice_big;
    private String choice_small;

    //private String choice_location = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        TextView enrollHomeTextView = (TextView) findViewById(R.id.enrollHomeTextView);
        TextView enrollSearchTextView = (TextView) findViewById(R.id.enrollSearchTextView);
        TextView enrollSettingTextView = (TextView) findViewById(R.id.enrollSettingTextView);
        TextView enrollDateTextView = (TextView) findViewById(R.id.enrollDateTextView);

        Button resetEnrollButton = (Button) findViewById(R.id.resetEnrollButton);

        CalendarView enrollCalendarView = (CalendarView) findViewById(R.id.enrollCalendarView);

        spinnerLine = (Spinner) findViewById(R.id.enrollLineSpinner);
        spinnerStation = (Spinner) findViewById(R.id.enrollStationSpinner);
        //spinner = (Spinner) findViewById(R.id.enrollLocationSpinner);
        spinnerBig = (Spinner) findViewById(R.id.bigProductSpinner);
        spinnerSmall = (Spinner) findViewById(R.id.smallProductSpinner);

        ArrayList<String> defaultList = new ArrayList<>();
        defaultList.add("전체");

        adapterLine =  ArrayAdapter.createFromResource(this, R.array.lineArray, android.R.layout.simple_spinner_dropdown_item);
        adapterStation = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, defaultList);
        adapterBig = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, ObjectCategory.getMainCategories());
        adapterLine = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, defaultList);

        spinnerLine.setAdapter(adapterLine);
        spinnerStation.setAdapter(adapterStation);
        spinnerBig.setAdapter(adapterBig);
        spinnerSmall.setAdapter(adapterSmall);

        spinnerLine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_line = adapterLine.getItem(position).toString();
                if(choice_line.equals("수인분당선")){

                    adapterStation = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, MetroSchedule.getSuinbundangStationList());
                    adapterStation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

        /*
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_location = adapter3.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */

        spinnerBig.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_big = adapterBig.getItem(position).toString();
                adapterSmall = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, ObjectCategory.getSubCategories(choice_big));
                adapterSmall.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSmall.setAdapter(adapterSmall);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        resetEnrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //초기화 눌렀을 때 어떻게 되는지
            }
        });

        enrollCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;
                enrollDateTextView.setText("  " + year + " - " + month + " - " + dayOfMonth + "   ");
            }
        });

        enrollDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrollCalendarView.setVisibility(View.VISIBLE);
            }
        });


/*

        Button btnEnroll = (Button) findViewById(R.id.btnEnroll);
        EditText editName, editMemo;

        editName = (EditText) findViewById(R.id.editName);
        editMemo = (EditText) findViewById(R.id.editMemo);

        //db에 저장
        btnEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "등록 완료", Toast.LENGTH_SHORT).show();

                editName.setText("");
                editMemo.setText("");
                
                //LostObject newObj = new LostObject();
                //newObj.setItem_id("");
                
                //이렇게 계속 추가해서 데이터 다 입력하면
                //DBController.postItem(obj);
                //하면 됨
                //static으로 만들어서 인스턴스 생성 안 해도 됨
            }
        });
        */
        enrollHomeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        enrollSearchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        enrollSettingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                onStop();
            }
        });

    }
    @Override
    public void onBackPressed () {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            onStop();
    }
}
