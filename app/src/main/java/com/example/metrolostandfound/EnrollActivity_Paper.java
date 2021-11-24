package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnrollActivity_Paper extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_paper);

        Button btn_Id = (Button) findViewById(R.id.btn_Id);
        Button btn_License = (Button) findViewById(R.id.btn_License);
        Button btn_Passport = (Button) findViewById(R.id.btn_Passport);
        Button btn_Else = (Button) findViewById(R.id.btn_Else);

        //db에 저장
        btn_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","증명서");
                intent.putExtra("sc","신분증");
                startActivity(intent);
                onStop();
            }
        });
        btn_License.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","증명서");
                intent.putExtra("sc","면허");
                startActivity(intent);
                onStop();
            }
        });
        btn_Passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","증명서");
                intent.putExtra("sc","여권");
                startActivity(intent);
                onStop();
            }
        });
        btn_Else.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "감사합니다 2/4", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EnrollActivity_LineAndStation.class);
                intent.putExtra("mc","증명서");
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
