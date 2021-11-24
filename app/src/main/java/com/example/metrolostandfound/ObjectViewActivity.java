package com.example.metrolostandfound;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ObjectViewActivity extends AppCompatActivity {
    private LostObject printObject;

    TextView objectMcTextView, objectScTextView, objectDateTextView, objectTimeTextView, objectLineTextView, objectStationTextView, objectLocTextView, objectDetailTextView;
    ImageView objectImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_view);

        ImageView searchImageView = (ImageView) findViewById(R.id.searchImageView);

        objectImageView = (ImageView) findViewById(R.id.objectImageView);
        objectMcTextView = (TextView) findViewById(R.id.objectMcTextView);
        objectScTextView = (TextView) findViewById(R.id.objectScTextView);
        objectDateTextView = (TextView) findViewById(R.id.objectDateTextView);
        objectTimeTextView = (TextView) findViewById(R.id.objectTimeTextView);
        objectLineTextView = (TextView) findViewById(R.id.objectLineTextView);
        objectStationTextView = (TextView) findViewById(R.id.objectStationTextView);
        objectDetailTextView = (TextView) findViewById(R.id.objectDetailTextView);

        loadItem();


        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





    }

    private void loadItem(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        if(id != -1){
            new DBLoadCall().execute(id);
        }
    }// 불러와서 printObject에 저장

    private void printItem(){
        String[] dateAndTime = printObject.getDateTime().split(":");
        objectImageView.setImageBitmap(printObject.getImage());
        objectMcTextView.setText(printObject.getMainCategory());
        objectScTextView.setText(printObject.getSubCategory());
        objectDateTextView.setText(dateAndTime[0]);
        objectTimeTextView.setText(dateAndTime[1]);
        objectLineTextView.setText(printObject.getLine());
        objectStationTextView.setText(printObject.getStation());
        objectDetailTextView.setText(printObject.getContents());  }//printObject에 저장이 완료되면 TextView 같은 곳에 오브젝트 내용을 쓴다.
    //그니까 printObject 쓰는 건 여기서 다 해야한다 다른 곳에서는 에러 뜰 수 있다
    private class DBLoadCall extends AsyncTask<Integer, String, String> {

        @Override
        protected String doInBackground(Integer[] params) {
            printObject = DBController.getItem(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            printItem();
        }
    } //동기 처리
}
