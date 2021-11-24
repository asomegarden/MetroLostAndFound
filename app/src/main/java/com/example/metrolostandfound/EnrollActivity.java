package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EnrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_main);

        Button btn_Bag = (Button) findViewById(R.id.btn_Bag);
        Button btn_Acc = (Button) findViewById(R.id.btn_Acc);
        Button btn_Book = (Button) findViewById(R.id.btn_Book);
        Button btn_Glasses = (Button) findViewById(R.id.btn_Glasses);
        Button btn_Tool = (Button) findViewById(R.id.btn_Tool);
        Button btn_Sport = (Button) findViewById(R.id.btn_Sport);
        Button btn_Cloth = (Button) findViewById(R.id.btn_Cloth);
        Button btn_Car = (Button) findViewById(R.id.btn_Car);
        Button btn_Elect = (Button) findViewById(R.id.btn_Elect);
        Button btn_Wallet = (Button) findViewById(R.id.btn_Wallet);
        Button btn_Paper = (Button) findViewById(R.id.btn_Paper);
        Button btn_Cash = (Button) findViewById(R.id.btn_Cash);
        Button btn_Card = (Button) findViewById(R.id.btn_Card);
        Button btn_Phone = (Button) findViewById(R.id.btn_Phone);
        Button btn_Etc = (Button) findViewById(R.id.btn_Etc);

        //db에 저장
        btn_Bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnrollActivity_Bag.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_Acc.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_Book.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_Glasses.class);
                startActivity(intent);
                onStop();

            }
        });
        btn_Tool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_Tool.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","스포츠용품");
                intent.putExtra("sc","스포츠용품");
                startActivity(intent);
                onStop();
            }
        });
        btn_Cloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_Clothes.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_Car.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Elect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_Electronics.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_Wallet.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_Paper.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnrollActivity_Cash.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_Card.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_Phone.class);
                startActivity(intent);
                onStop();
            }
        });
        btn_Etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","기타용품");
                intent.putExtra("sc","기타용품");
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
