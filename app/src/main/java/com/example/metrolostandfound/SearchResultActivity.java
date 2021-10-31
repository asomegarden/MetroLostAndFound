package com.example.metrolostandfound;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

        loadItem();
    }

    //리스트 내용 불러오기 여기서 데이터 불러와서 추가하면 될듯 일단은 아무거나 넣음
    public void loadItem(){
        //List<LostObject> objs = DBController.getItem();
        //로 받아서 그 형식에 맞게 잘 넣어주면 됨

        //꼭 텍스트 말고 다른 것도 넣을 수 있음 일단은 테스트용으로 텍스트
        addItem("아이폰7", "스마트폰", "1호선 시청역");
        addItem("카시오 시계", "시계", "수인분당선 망포역");
        addItem("책가방", "가방", "4호선 석계역");
        addItem("에어팟", "이어폰", "1호선 수원역");
        addItem("갤럭시 s10", "스마트폰", "2호선 강남역");
    }

    //리스트에 아이템 추가
    public void addItem(String name, String category, String locate){
        RecyclerItemCustom item = new RecyclerItemCustom();

        item.setName(name);
        item.setCategory(category);
        item.setLocate(locate);

        mList.add(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
        onStop();
    }
}
