package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnrollActivity_PW extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_password);

        Button btn_Enroll = (Button) findViewById(R.id.btn_Enroll);
        EditText password = (EditText) findViewById(R.id.edit_password);

        //db에 저장
        btn_Enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Password = password.getText().toString();
                if(Password.equals("")){
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "등록 시작", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), EnrollActivity.class);
                    DBController.passwdTemp = Password;
                    startActivity(intent);
                    onStop();
                }
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
