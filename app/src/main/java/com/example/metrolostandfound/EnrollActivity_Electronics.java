package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnrollActivity_Electronics extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_electronics);

        Button btn_Tablet = (Button) findViewById(R.id.btn_Tablet);
        Button btn_Camera = (Button) findViewById(R.id.btn_Camera);
        Button btn_Notebook = (Button) findViewById(R.id.btn_Notebook);
        Button btn_Earphone = (Button) findViewById(R.id.btn_Earphone);
        Button btn_smartwatch = (Button) findViewById(R.id.btn_Smartwatch);
        Button btn_Else = (Button) findViewById(R.id.btn_Else);

        //db에 저장
        btn_Tablet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","전자기기");
                intent.putExtra("sc","태블릿PC");
                startActivity(intent);
                onStop();
            }
        });
        btn_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","전자기기");
                intent.putExtra("sc","카메라");
                startActivity(intent);
                onStop();
            }
        });
        btn_Notebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","전자기기");
                intent.putExtra("sc","노트북");
                startActivity(intent);
                onStop();
            }
        });
        btn_Earphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","전자기기");
                intent.putExtra("sc","이어폰");
                startActivity(intent);
                onStop();
            }
        });
        btn_smartwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","전자기기");
                intent.putExtra("sc","스마트워치");
                startActivity(intent);
                onStop();
            }
        });
        btn_Else.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","전자기기");
                intent.putExtra("sc","기타");
                startActivity(intent);
                onStop();
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
