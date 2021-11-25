package com.example.metrolostandfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoSearch = (Button) findViewById(R.id.btnGoSearch);
        Button btnGoEnroll = (Button) findViewById(R.id.btnGoEnroll);
        TextView mainAddTextView = (TextView) findViewById(R.id.mainAddTextView);
        TextView mainSearchTextView = (TextView) findViewById(R.id.mainSearchTextView);
        TextView mainSettingTextView = (TextView) findViewById(R.id.mainSettingTextView);

        btnGoSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        btnGoEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnrollActivity_PW.class);
                startActivity(intent);
                onStop();
            }
        });

        mainAddTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnrollActivity_PW.class);
                startActivity(intent);
                onStop();
            }
        });

        mainSearchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        mainSettingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                onStop();
            }
        });
    }

    //뒤로가기 두번 누르면 종료
    private long backKeyPressedTime = 0;
    private Toast toast;
    final String PREFNAME = "Preferences";

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            SharedPreferences settings = getSharedPreferences(PREFNAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();

            editor.putBoolean("Appexec", true);
            editor.apply();
            ActivityCompat.finishAffinity(this);
            toast.cancel();
        }
    }
}