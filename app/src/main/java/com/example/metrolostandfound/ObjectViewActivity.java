package com.example.metrolostandfound;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ObjectViewActivity extends AppCompatActivity {
    private LostObject printObject;

    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_view);

        loadItem();

        test = (TextView) findViewById(R.id.test_text);


    }

    private void loadItem(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        if(id != -1){
            new DBLoadCall().execute(id);
        }
    }// 불러와서 printObject에 저장

    private void printItem(){
        test.setText(printObject.toString());
    }//printObject에 저장이 완료되면 TextView 같은 곳에 오브젝트 내용을 쓴다.
    //그니까 printObject 쓰는 건 여기서 다 해야한다 다른 곳에서는 에러 뜰 수 있다
    private class DBLoadCall extends AsyncTask<Integer, String, String> {

        @Override
        protected String doInBackground(Integer[] params) {
            printObject = DBController.getItem(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            printItem();
        }
    } //동기 처리
    //날짜 입력칸이 왜 두개인건가요??
    public int Compare_Date(String Date1, String Date2,String Date3) throws ParseException { //날짜,시간 비교
        Date1 = Date1.replaceAll(" /:",""); //검색페이지 날짜,시간
        int day1 = Integer.parseInt(Date1);
        Date2 = Date1.replaceAll(" /:",""); //검색페이지 날짜,시간
        int day2 = Integer.parseInt(Date1);
        Date3 = Date1.replaceAll(" /:",""); //등록된 날짜,시간
        int day3 = Integer.parseInt(Date1);
        if(day3>=day1) {
            if (day2 >= day3)
            return 1; //만족하는 날짜
            else
                return 0;//조건을 만족하지 못할때
        } else
            return 0; //조건을 만족하지 못할때

    }
}
