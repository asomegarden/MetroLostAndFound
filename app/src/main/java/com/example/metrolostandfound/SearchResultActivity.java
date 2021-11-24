package com.example.metrolostandfound;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    //리스트뷰 변수 선언
    RecyclerView mRecyclerView = null;
    RecyclerItemCustomAdapter mAdapter = null;
    ArrayList<RecyclerItemCustom> mList = new ArrayList<RecyclerItemCustom>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        //리스트 사용 위한 부분
        mRecyclerView = findViewById(R.id.recycler);

        mAdapter = new RecyclerItemCustomAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                    addItem(object);
                }
            }
            //데이터 변경 반영
            mAdapter.notifyDataSetChanged();
        }
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
    public void addItem(LostObject object){
        RecyclerItemCustom item = new RecyclerItemCustom();

        Bitmap image = object.getImage();
        if(image == null){
            image = BitmapFactory.decodeResource(getResources(), R.drawable.search);
        }

        item.setId(Integer.parseInt(object.getId()));
        item.setImage(image);
        item.setMC(object.getMainCategory());
        item.setSC(object.getSubCategory());
        item.setLocate(object.getLine() + " " + object.getStation());

        mList.add(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
        onStop();
    }
}
