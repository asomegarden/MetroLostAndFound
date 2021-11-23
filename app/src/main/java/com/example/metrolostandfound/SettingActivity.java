package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        TextView settingHomeTextView = (TextView) findViewById(R.id.settingHomeTextView);
        TextView settingAddTextView = (TextView) findViewById(R.id.settingAddTextView);
        TextView settingSearchTextView = (TextView) findViewById(R.id.settingSearchTextView);

        settingHomeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        settingAddTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnrollActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        settingSearchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                onStop();
            }
        });

/*
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //테스트하려고 추가했었던 부분
                //Toast.makeText(getApplicationContext(), "검색", Toast.LENGTH_SHORT).show();

                //결과창으로 이동
                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                startActivity(intent);
                onStop();
            }
        });
*/
    }


    @Override
    public void onBackPressed () {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        onStop();


    }

}
