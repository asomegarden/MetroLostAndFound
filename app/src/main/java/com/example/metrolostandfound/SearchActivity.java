package com.example.metrolostandfound;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    //리스트뷰 변수 선언
    RecyclerView mRecyclerView = null;
    RecyclerItemCustomAdapter mAdapter = null;
    ArrayList<RecyclerItemCustom> mList = new ArrayList<RecyclerItemCustom>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView searchHomeTextView = (TextView) findViewById(R.id.searchHomeTextView);
        TextView searchAddTextView = (TextView) findViewById(R.id.searchAddTextView);
        TextView searchSettingTextView = (TextView) findViewById(R.id.searchSettingTextView);
        ImageView searchImageView = (ImageView) findViewById(R.id.searchImageView);

        //리스트 사용 위한 부분
        mRecyclerView = findViewById(R.id.recycler);

        mAdapter = new RecyclerItemCustomAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadItem();

        searchHomeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        searchAddTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnrollActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        searchSettingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchDetailActivity.class);
                startActivity(intent);
                onStop();
            }
        });
/*
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //테스트하려고 추가했었던 부분
                //Toast.makeText(getApplicationContext(), "검색", Toast.LENGTH_SHORT).show();

                //결과창으로 이동
                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                startActivity(intent);
                onStop();
            }
        });
*/
    }

    private class DBLoadCall extends AsyncTask<String, String, String> {

        List<LostObject> objs = new ArrayList<>();
        @Override
        protected String doInBackground(String[] params) {
            if(params.length == 0) {
                objs.addAll(DBController.getItems());
            }
            else if(params.length == 1){
                objs.addAll(DBController.getItems(params[0]));
            }
            else if(params.length == 2){
                objs.addAll(DBController.getItems(params[0], params[1]));
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if(objs != null) {
                for (LostObject object : objs) {
                    addItem(object.getImage(), object.getSubCategory(), object.getMainCategory(), object.getLine() + " " + object.getStation());
                }
            }
            //데이터 변경 반영
            mAdapter.notifyDataSetChanged();
        }
    }

    //테스트용
    public void loadItemTest(){

        //이미지 리스트뷰 테스트용
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.search);

        //꼭 텍스트 말고 다른 것도 넣을 수 있음 일단은 테스트용으로 텍스트
        addItem(bitmap, "아이폰7", "스마트폰", "1호선 시청역");
        addItem(bitmap, "카시오 시계", "시계", "수인분당선 망포역");
        addItem(bitmap, "책가방", "가방", "4호선 석계역");
        addItem(bitmap, "에어팟", "이어폰", "1호선 수원역");
        addItem(bitmap, "갤럭시 s10", "스마트폰", "2호선 강남역");
    }

    //리스트 내용 모두 불러오기
    public void loadItem(){
        new DBLoadCall().execute();
    }

    //리스트 내용 메인 카테고리로 불러오기
    public void loadItem(String mc){
        new DBLoadCall().execute(mc);
    }

    //리스트 내용 메인 카테고리와 서브 카테고리로 불러오기
    public void loadItem(String mc, String sc){
        new DBLoadCall().execute(mc, sc);
    }

    //리스트에 아이템 추가
    public void addItem(Bitmap image, String name, String category, String locate){
        RecyclerItemCustom item = new RecyclerItemCustom();

        if(image == null){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.search);
        }
        item.setImage(image);
        item.setName(name);
        item.setCategory(category);
        item.setLocate(locate);

        mList.add(item);
    }

    @Override
    public void onBackPressed () {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        onStop();
    }
}
