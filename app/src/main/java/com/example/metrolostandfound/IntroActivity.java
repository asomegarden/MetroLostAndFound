package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //시간표 정보가 담긴 엑셀 시트 불러오기
        readExcel();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1000 );
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }

    private void readExcel(){
        //엑셀 불러오는 작업
        try {
            InputStream is = getBaseContext().getResources().getAssets().open("suinbundang_line.xls");
            MetroSchedule.readSbExcel(is);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
