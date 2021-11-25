package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView productDetailHomeTextView = (TextView) findViewById(R.id.productDetailHomeTextView);
        TextView productDetailAddTextView = (TextView) findViewById(R.id.productDetailAddTextView);
        TextView productDetailSearchTextView = (TextView) findViewById(R.id.productDetailSearchTextView);
        TextView productDetailSettingTextView = (TextView) findViewById(R.id.productDetailSettingTextView);

        productDetailHomeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        productDetailAddTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnrollActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        productDetailSearchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        productDetailSettingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                onStop();
            }
        });
    }
}
