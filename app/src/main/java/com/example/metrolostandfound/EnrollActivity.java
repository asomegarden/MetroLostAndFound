package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnrollActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        TextView enrollHomeTextView = (TextView) findViewById(R.id.enrollHomeTextView);
        TextView enrollSearchTextView = (TextView) findViewById(R.id.enrollSearchTextView);
        TextView enrollSettingTextView = (TextView) findViewById(R.id.enrollSettingTextView);
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
