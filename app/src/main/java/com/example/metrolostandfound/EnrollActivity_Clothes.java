package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnrollActivity_Clothes extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_cloth);

        Button btn_Female = (Button) findViewById(R.id.btn_Female);
        Button btn_Male = (Button) findViewById(R.id.btn_Male);
        Button btn_Kid = (Button) findViewById(R.id.btn_Kid);
        Button btn_Sports = (Button) findViewById(R.id.btn_Sports);
        Button btn_Cap = (Button) findViewById(R.id.btn_Cap);
        Button btn_Shoes = (Button) findViewById(R.id.btn_Shoes);
        Button btn_Else = (Button) findViewById(R.id.btn_Else);

        //db에 저장
        btn_Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","의류");
                intent.putExtra("sc","여성의류");
                startActivity(intent);
                onStop();
            }
        });
        btn_Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","의류");
                intent.putExtra("sc","남성의류");
                startActivity(intent);
                onStop();
            }
        });
        btn_Kid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","의류");
                intent.putExtra("sc","아동의류");
                startActivity(intent);
                onStop();
            }
        });
        btn_Sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","의류");
                intent.putExtra("sc","스포츠의류");
                startActivity(intent);
                onStop();
            }
        });
        btn_Cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","의류");
                intent.putExtra("sc","모자");
                startActivity(intent);
                onStop();
            }
        });
        btn_Shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","의류");
                intent.putExtra("sc","신발");
                startActivity(intent);
                onStop();
            }
        });
        btn_Else.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","의류");
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
