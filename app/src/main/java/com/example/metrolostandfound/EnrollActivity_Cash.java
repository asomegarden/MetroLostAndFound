package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnrollActivity_Cash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_cash);

        Button btn_Cash = (Button) findViewById(R.id.btn_Cash);
        Button btn_Gift = (Button) findViewById(R.id.btn_Gift);
        Button btn_Check = (Button) findViewById(R.id.btn_Check);
        Button btn_Currency = (Button) findViewById(R.id.btn_Currency);
        Button btn_Else = (Button) findViewById(R.id.btn_Else);

        //db에 저장
        btn_Cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","현금");
                intent.putExtra("sc","수표");
                startActivity(intent);
                onStop();
            }
        });
        btn_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","현금");
                intent.putExtra("sc","수표");
                startActivity(intent);
                onStop();
            }
        });
        btn_Currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","현금");
                intent.putExtra("sc","외화");
                startActivity(intent);
                onStop();
            }
        });
        btn_Else.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","현금");
                intent.putExtra("sc","기타");
                startActivity(intent);
                onStop();
            }
        });
        btn_Gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","현금");
                intent.putExtra("sc","상품권");
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
