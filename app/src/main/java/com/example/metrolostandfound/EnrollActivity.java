package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.time.Year;

public class EnrollActivity extends AppCompatActivity {

    private ArrayAdapter adapter1, adapter2, adapter3, adapter4, adapter5;
    private Spinner spinner1, spinner2, spinner3, spinner4, spinner5;
    private String choise_line = "";
    private String choise_station ="";
    private String choise_location = "";

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

        spinner1 = (Spinner) findViewById(R.id.enrollLineSpinner);
        spinner2 = (Spinner) findViewById(R.id.enrollStationSpinner);
        spinner3 = (Spinner) findViewById(R.id.enrollLocationSpinner);
        spinner4 = (Spinner) findViewById(R.id.bigProductSpinner);
        spinner5 = (Spinner) findViewById(R.id.smallProductSpinner);

        adapter1 = ArrayAdapter.createFromResource(this, R.array.lineArray, android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.stationArray, android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        adapter3 = ArrayAdapter.createFromResource(this, R.array.locationArray, android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        adapter4 = ArrayAdapter.createFromResource(this, R.array.bigProductArray, android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        adapter5 = ArrayAdapter.createFromResource(this, R.array.allSmallProductArray, android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(adapter1.getItem(position).equals("수인분당선")){
                    choise_line = "수인분당선";
                    adapter2 = ArrayAdapter.createFromResource(EnrollActivity.this, R.array.suin_bundangStationArray, android.R.layout.simple_spinner_dropdown_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choise_station = adapter2.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choise_location = adapter3.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapter1.getItem(position).equals("가방")) {
                    adapter5 = ArrayAdapter.createFromResource(EnrollActivity.this, R.array.bagSmallProductArray, android.R.layout.simple_spinner_dropdown_item);
                    adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner5.setAdapter(adapter5);
                } else if (adapter1.getItem(position).equals("귀금속")) {
                    adapter5 = ArrayAdapter.createFromResource(EnrollActivity.this, R.array.jewelSmallProductArray, android.R.layout.simple_spinner_dropdown_item);
                    adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner5.setAdapter(adapter5);
                }
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
