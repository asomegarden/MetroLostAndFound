package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnrollActivity_Bag extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_bag);

        Button btn_Back = (Button) findViewById(R.id.btn_Back);
        Button btn_Hand = (Button) findViewById(R.id.btn_Hand);
        Button btn_Clutch = (Button) findViewById(R.id.btn_Clutch);
        Button btn_Carrier = (Button) findViewById(R.id.btn_Carrier);
        Button btn_Pouch = (Button) findViewById(R.id.btn_Pouch);
        Button btn_Eco = (Button) findViewById(R.id.btn_Eco);
        Button btn_Shop = (Button) findViewById(R.id.btn_Shop);
        Button btn_Else = (Button) findViewById(R.id.btn_Else);

        //db에 저장
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","가방");
                intent.putExtra("sc","백팩");
                startActivity(intent);
                onStop();
            }
        });
        btn_Hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","가방");
                intent.putExtra("sc","핸드백/숄더백/메신저");
                startActivity(intent);
                onStop();
            }
        });
        btn_Clutch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","가방");
                intent.putExtra("sc","클러치백");
                startActivity(intent);
                onStop();
            }
        });
        btn_Carrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","가방");
                intent.putExtra("sc","캐리어");
                startActivity(intent);
                onStop();
            }
        });
        btn_Pouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","가방");
                intent.putExtra("sc","파우치");
                startActivity(intent);
                onStop();
            }
        });
        btn_Eco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","가방");
                intent.putExtra("sc","에코백");
                startActivity(intent);
                onStop();
            }
        });
        btn_Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","가방");
                intent.putExtra("sc","쇼핑백");
                startActivity(intent);
                onStop();
            }
        });
        btn_Else.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","가방");
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
