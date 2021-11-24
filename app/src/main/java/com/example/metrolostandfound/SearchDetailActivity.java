package com.example.metrolostandfound;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private List<LostObject> tempList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        searchDetailDateTextView1 = (TextView) findViewById(R.id.searchDetailDateTextView1);
        searchDetailDateTextView2 = (TextView) findViewById(R.id.searchDetailDateTextView2);
        searchDetailTimeTextView1 = (TextView) findViewById(R.id.searchDetailTimeTextView1);

        List<String> defaultList = new ArrayList<>();
        defaultList.add("모두");
        searchSpinnerLine = (Spinner) findViewById(R.id.searchLineSpinner);
        searchSpinnerStation = (Spinner) findViewById(R.id.searchStationSpinner);
        searchSpinnerBig = (Spinner) findViewById(R.id.searchBigProductSpinner);
        searchSpinnerSmall = (Spinner) findViewById(R.id.searchSmallProductSpinner);
        clear();


        Button btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mc = choice_big;
                String sc = choice_small;

                if(mc.equals("모두")){
                    new DBLoadCall().execute();
                }else {
                    if(sc.equals("모두")){
                        new DBLoadCall().execute(mc, sc);
                    }else{
                        new DBLoadCall().execute(mc);
                    }
                }
            }
        });

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

    private class DBLoadCall extends AsyncTask<String, String, String> {

        List<LostObject> objs = new ArrayList<>();
        @Override
        protected String doInBackground(String[] params) {
            if(params.length == 0) {
                objs.addAll(DBController.getItems());
            }
            else if(params.length == 1){
                objs.addAll(DBController.getItems(params[0]));
            }
            else if(params.length == 2){
                objs.addAll(DBController.getItems(params[0], params[1]));
            }
            else if(params.length == 3){
                objs.addAll(DBController.getItems(params[0], params[1], params[2]));
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            LostObject o = new LostObject();
            String line = new String();
            if(!choice_line.equals("모두")){
                line = choice_line;
                if(choice_station.equals("모두")){
                    o.setLine(choice_line);
                    o.setStation(choice_station);
                    o.setTrain(MetroSchedule.whatTrain(o));
                }
            }
            if(line == null){
                tempList.addAll(objs);
            }else {
                for (LostObject a : objs) {
                    if (a.getLine().equals(line)) {
                        if(o.getTrain() == null){
                            tempList.add(a);
                        }else {
                            if(o.getTrain().equals(a.getTrain())) {
                                tempList.add(a);
                            }
                        }
                    }
                }
            }

            SearchActivity.detailList.addAll(tempList);
            SearchActivity.detail = true;

            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(intent);
            onStop();
        }
    }

    //리스트 내용 모두 불러오기
    public void loadItem(){
        new DBLoadCall().execute();
    }

    //리스트 내용 메인 카테고리로 불러오기
    public void loadItem(String mc){
        new DBLoadCall().execute(mc);
    }

    //리스트 내용 메인 카테고리와 서브 카테고리로 불러오기
    public void loadItem(String mc, String sc){
        new DBLoadCall().execute(mc, sc);
    }

    //리스트 내용 메인 카테고리와 서브 카테고리와 호선으로 불러오기
    public void loadItem(String mc, String sc, String l){
        new DBLoadCall().execute(mc, sc, l);
    }

    @Override
    public void onBackPressed () {


        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
        onStop();
    }
}
