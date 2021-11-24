package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnrollActivity_LineAndStation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_line);

        Button btnEnroll = (Button) findViewById(R.id.btn_Enroll);
        EditText editLine, editStation, editDate;

        editLine = (EditText) findViewById(R.id.editLine);
        editStation = (EditText) findViewById(R.id.editStation);
        editDate = (EditText) findViewById(R.id.editDate);

        //db에 저장
        btnEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 3/4", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                Intent intent2 = new Intent(EnrollActivity_LineAndStation.this, EnrollActivity_Other.class);
                intent2.putExtra("station",editStation.getText().toString());
                intent2.putExtra("date",editDate.getText().toString());
                intent2.putExtra("line",editLine.getText().toString());
                intent2.putExtra("mc",intent.getStringExtra("mc"));
                intent2.putExtra("sc",intent.getStringExtra("sc"));
                startActivity(intent2);
                onStop();
                
                //이렇게 계속 추가해서 데이터 다 입력하면
                //DBController.postItem(obj);
                //하면 됨
                //static으로 만들어서 인스턴스 생성 안 해도 됨
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        onStop();
    }
}
