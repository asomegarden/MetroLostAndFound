package com.example.metrolostandfound;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;
import java.util.Timer;

public class EnrollActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapterLine, adapterStation, adapterBig, adapterSmall;
    private Spinner spinnerLine, spinnerStation, spinnerBig, spinnerSmall;
    private String choice_line = "모두", choice_station ="모두";
    private String choice_big = "모두", choice_small = "모두";
    private Bitmap choice_img;

    private int year, month, date;
    private int hour, minute;

    private TextView enrollDateTextView;
    private TextView enrollTimeTextView;

    private EditText editStorage;
    private EditText editMemo;
    private EditText editPasswd;

    private final int GET_GALLERY_IMAGE = 200;

    //private String choice_location = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        TextView enrollHomeTextView = (TextView) findViewById(R.id.enrollHomeTextView);
        TextView enrollSearchTextView = (TextView) findViewById(R.id.enrollSearchTextView);
        TextView enrollSettingTextView = (TextView) findViewById(R.id.enrollSettingTextView);

        editPasswd = (EditText) findViewById(R.id.edit_passwd);
        editStorage = (EditText) findViewById(R.id.edit_storage);
        editMemo = (EditText) findViewById(R.id.edit_memo);

        enrollDateTextView = (TextView) findViewById(R.id.enrollDateTextView);
        enrollTimeTextView = (TextView) findViewById(R.id.enrollTimeTextView);

        Button resetEnrollButton = (Button) findViewById(R.id.resetEnrollButton);
        Button enrollButton = (Button) findViewById(R.id.enrollButton);
        Button imgPickButton = (Button) findViewById(R.id.btn_pickImg);


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

        List<String> defaultList = new ArrayList<>();
        defaultList.add("모두");

        spinnerLine = (Spinner) findViewById(R.id.enrollLineSpinner);
        spinnerStation = (Spinner) findViewById(R.id.enrollStationSpinner);
        //spinner = (Spinner) findViewById(R.id.enrollLocationSpinner);
        spinnerBig = (Spinner) findViewById(R.id.bigProductSpinner);
        spinnerSmall = (Spinner) findViewById(R.id.smallProductSpinner);

        initSpinner();

        spinnerLine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_line = adapterLine.getItem(position).toString();
                if(choice_line.equals("모두")){
                    adapterStation = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, defaultList);
                    spinnerStation.setAdapter(adapterStation);
                }
                else if(choice_line.equals("수인분당선")){
                    List<String> stationList = new ArrayList<>();
                    stationList.add("모두");
                    stationList.addAll(MetroSchedule.getSuinbundangStationList());

                    adapterStation = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, stationList);
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
                if(choice_big.equals("모두")){
                    adapterSmall = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, defaultList);
                    spinnerSmall.setAdapter(adapterSmall);
                }
                else{
                    List<String> subList = new ArrayList<>();
                    subList.add("모두");
                    subList.addAll(ObjectCategory.getSubCategories(choice_big));

                    adapterSmall = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, subList);
                    spinnerSmall.setAdapter(adapterSmall);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //endregion

        imgPickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        resetEnrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwd = editPasswd.getText().toString();
                if(passwd.equals("")){
                    Toast.makeText(getApplicationContext(), "비밀번호는 필수항목", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String day = year + ":" + month + ":" + date + ":" + hour + ":" + minute;
                    String line = choice_line;
                    String station = choice_station;
                    String mc = choice_big;
                    String sc = choice_small;
                    String storage = editStorage.getText().toString();
                    String memo = editMemo.getText().toString();
                    Bitmap image = choice_img;
                    
                    LostObject obj = new LostObject();
                    obj.setDateTime(day);
                    obj.setLine(line);
                    obj.setStation(station);
                    obj.setMainCategory(mc);
                    obj.setSubCategory(sc);
                    obj.setStorage(storage);
                    obj.setContents(memo);
                    obj.setImage(image);
                    obj.setPasswd(passwd);

                    DBController.postItem(obj);

                    Toast.makeText(getApplicationContext(), "등록됨", Toast.LENGTH_SHORT).show();
                    clear();
                }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                InputStream in = getContentResolver().openInputStream(data.getData());

                choice_img = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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

    private void clear(){
        initDateTime();
        initSpinner();

        editPasswd.setText("");
        editMemo.setText("");
        editStorage.setText("");
        choice_img = null;
        choice_big = "모두";
        choice_small = "모두";
        choice_line = "모두";
        choice_station = "모두";
    }

    private void initSpinner(){
        List<String> defaultList = new ArrayList<>();
        List<String> lineList = new ArrayList<>();
        List<String> cateList = new ArrayList<>();

        defaultList.add("모두");
        lineList.add("모두");

        lineList.addAll(MetroSchedule.getLines());

        cateList.add("모두");
        cateList.addAll(ObjectCategory.getMainCategories());

        adapterLine =  new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, lineList);
        adapterStation = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, defaultList);
        adapterBig = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cateList);
        adapterSmall = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, defaultList);

        spinnerLine.setAdapter(adapterLine);
        spinnerStation.setAdapter(adapterStation);
        spinnerBig.setAdapter(adapterBig);
        spinnerSmall.setAdapter(adapterSmall);

    }

    private  void initDateTime(){
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
    public void onBackPressed () {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            onStop();
    }
}
