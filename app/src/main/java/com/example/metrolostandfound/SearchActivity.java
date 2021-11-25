package com.example.metrolostandfound;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    //리스트뷰 변수 선언
    RecyclerView mRecyclerView = null;
    RecyclerItemCustomAdapter mAdapter = null;
    ArrayList<RecyclerItemCustom> mList = new ArrayList<RecyclerItemCustom>();

    private TextView searchDetailTimeTextView1;

    private int year, month, date;
    private int hour, minute;

    private Spinner searchSpinnerLine, searchSpinnerStation, searchSpinnerBig, searchSpinnerSmall;
    private ArrayAdapter<String> searchAdapterLine, searchAdapterStation, searchAdapterBig, searchAdapterSmall;

    private String choice_line = "모두", choice_station ="모두";
    private String choice_big = "모두", choice_small = "모두";

    private RelativeLayout layoutResult, layoutDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView searchHomeTextView = (TextView) findViewById(R.id.searchHomeTextView);
        TextView searchAddTextView = (TextView) findViewById(R.id.searchAddTextView);
        TextView searchSettingTextView = (TextView) findViewById(R.id.searchSettingTextView);
        ImageView searchImageView = (ImageView) findViewById(R.id.searchImageView);

        layoutResult = (RelativeLayout) findViewById(R.id.layout_result);
        layoutDetail = (RelativeLayout) findViewById(R.id.layout_detail);

        //리스트 사용 위한 부분
        mRecyclerView = findViewById(R.id.recycler);

        mAdapter = new RecyclerItemCustomAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadItem();

        //region Activity 이동
        searchHomeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        searchAddTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnrollActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        searchSettingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutDetail.setVisibility(View.VISIBLE);
                layoutResult.setVisibility(View.GONE);
            }
        });
        //endregion

        searchDetailTimeTextView1 = (TextView) findViewById(R.id.searchDetailTimeTextView1);

        List<String> defaultList = new ArrayList<>();
        defaultList.add("모두");
        searchSpinnerLine = (Spinner) findViewById(R.id.searchLineSpinner);
        searchSpinnerStation = (Spinner) findViewById(R.id.searchStationSpinner);
        searchSpinnerBig = (Spinner) findViewById(R.id.searchBigProductSpinner);
        searchSpinnerSmall = (Spinner) findViewById(R.id.searchSmallProductSpinner);
        clear();


        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        ImageView btnRefresh = (ImageView) findViewById(R.id.refresh);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "새로고침", Toast.LENGTH_SHORT).show();
                listRefresh();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mc = choice_big;
                String sc = choice_small;

                mList.clear();

                if(mc.equals("모두")){
                    loadItem();
                }else {
                    if(sc.equals("모두")){
                        loadItem(mc);
                    }else{
                        loadItem(mc, sc);
                    }
                }

                clear();

                layoutResult.setVisibility(View.VISIBLE);
                layoutDetail.setVisibility(View.GONE);
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

        searchDetailTimeTextView1.setText(" " + String.format("%02d", hour) + " : " + String.format("%02d", minute) + " ");
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


    private void listRefresh(){
        mList.clear();
        if(choice_line.equals("모두")) {
            for (LostObject o : DBController.objects) {
                addItem(o);
            }
        }else{
            if(choice_station.equals("모두")){
                for (LostObject o : DBController.objects) {
                    if(o.getLine().equals(choice_line)){
                        addItem(o);
                    }
                }
            }else{
                LostObject lo = new LostObject();
                lo.setLine(choice_line);
                lo.setStation(choice_station);
                lo.setDateTime(year + ":" + month + ":" + date + ":" + hour + ":" + minute);
                lo.setTrain(MetroSchedule.whatTrain(lo));

                for (LostObject o : DBController.objects) {
                    if(o.getTrain().equals(lo.getTrain())){
                        addItem(o);
                    }
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    //리스트 내용 모두 불러오기
    public void loadItem(){
        DBController.getItems();
    }

    //리스트 내용 메인 카테고리로 불러오기
    public void loadItem(String mc){
       DBController.getItems(mc);
    }

    //리스트 내용 메인 카테고리와 서브 카테고리로 불러오기
    public void loadItem(String mc, String sc){
        DBController.getItems(mc, sc);
    }

    //리스트에 아이템 추가
    public void addItem(LostObject object){
        RecyclerItemCustom item = new RecyclerItemCustom();

        Bitmap image = object.getImage();
        if(image == null){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.search);
        }

        item.setId(Integer.parseInt(object.getId()));
        item.setImage(image);
        item.setMC(object.getMainCategory());
        item.setSC(object.getSubCategory());
        item.setLocate(object.getLine() + " " + object.getStation());

        mList.add(item);
    }

    @Override
    public void onBackPressed () {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        onStop();
    }
}
