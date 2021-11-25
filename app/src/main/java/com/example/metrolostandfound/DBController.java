package com.example.metrolostandfound;

import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public class DBController {
    public static List<LostObject> objects;
    public static LostObject singleObject;

    //패스워드가 막판에 추가돼서 인탠트를 다 수정하기 힘들 것 같아서 여기에 임시저장
    public static String passwdTemp;
    
    //이제 리턴해주는 형식이 아니라 변수에 저장해놓은 후 리프레시 할 때마다 불러오게
    //동기 처리 문제가 있어서 결국 이렇게 했음
    //DBController.objects 는 리스트
    //DBController.singleObject 는 단일 객체

    static {
        singleObject = new LostObject();
        objects = new ArrayList<>();
    }

    //DB의 모든 아이템을 List로 반환
    public static void getItems(){
        objects.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.64.198.240:1337/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<List<LostObject>> call = service.getItems();

        call.enqueue((new Callback<List<LostObject>>() {
            @Override
            public void onResponse(Call<List<LostObject>> call, Response<List<LostObject>> response) {
                if(response.isSuccessful()) {
                    for(LostObject o : response.body()){
                        objects.add(new LostObject(o));
                    }
                }else {
                    Log.d("get 에러", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<LostObject>> call, Throwable t) {
                Log.d("get 실패", "이유 : ");
                t.printStackTrace();
            }
        }));
    }

    //메인 카테고리로 검색
    public static void getItems(String mc){
        objects.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.64.198.240:1337/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<List<LostObject>> call = service.getItems(mc);

        call.enqueue((new Callback<List<LostObject>>() {
            @Override
            public void onResponse(Call<List<LostObject>> call, Response<List<LostObject>> response) {
                if(response.isSuccessful()) {
                    for(LostObject o : response.body()){
                        objects.add(new LostObject(o));
                    }
                    Log.e("확인", objects.toString());
                }else {
                    Log.d("get 에러", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<LostObject>> call, Throwable t) {
                Log.d("get 실패", "이유 : ");
                t.printStackTrace();
            }
        }));
    }

    //메인 카테고리 + 서브 카테고리로 검색
    public static void getItems(String mc, String sc){
        objects.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.64.198.240:1337/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<List<LostObject>> call = service.getItems(mc, sc);

        call.enqueue((new Callback<List<LostObject>>() {
            @Override
            public void onResponse(Call<List<LostObject>> call, Response<List<LostObject>> response) {
                if(response.isSuccessful()) {
                    for(LostObject o : response.body()){
                        objects.add(new LostObject(o));
                    }
                }else {
                    Log.d("get 에러", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<LostObject>> call, Throwable t) {
                Log.d("get 실패", "이유 : ");
                t.printStackTrace();
            }
        }));
    }

    //id로 하나만 받아오기
    public static LostObject getItem(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.64.198.240:1337/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<LostObject> call = service.getItem(id);

        call.enqueue((new Callback<LostObject>() {
            @Override
            public void onResponse(Call<LostObject> call, Response<LostObject> response) {
                if(response.isSuccessful()) {
                    singleObject = new LostObject(response.body());

                }else {
                    Log.d("get 에러", response.message());
                }
            }

            @Override
            public void onFailure(Call<LostObject> call, Throwable t) {
                Log.d("get 실패", "이유 : ");
                t.printStackTrace();
            }
        }));

        return singleObject;
    }

    //obj를 DB에 등록
    public static void postItem(LostObject obj){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.64.198.240:1337/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<LostObject> call = service.postItem(obj);

        call.enqueue(new Callback<LostObject>() {
            @Override
            public void onResponse(Call<LostObject> call, Response<LostObject> response) {
                if(!response.isSuccessful()){
                    Log.d("Post 오류", response.message());
                    return;
                }
            }

            @Override
            public void onFailure(Call<LostObject> call, Throwable t) {
                Log.d("Post 실패", "이유 : ");
                t.printStackTrace();
            }
        });
    }

    //item 삭제
    public static void deleteItem(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.64.198.240:1337/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<Void> call = service.deletePost(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Log.d("삭제 오류", response.message());
                    return;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("삭제 실패", "이유 : ");
                t.printStackTrace();
            }
        });
    }
    
    
}

//API 메소드 사용을 위한 인터페이스 나중에 Query도 만들거임
interface RetrofitService {
    //전부 받아오기
    @GET("items/{id}")
    Call<LostObject> getItem(@Path("id") int id);

    @GET("items")
    Call<List<LostObject>> getItems();

    //메인 카테고리로 검색
    @GET("items")
    Call<List<LostObject>> getItems(@Query("main_category") String mc);

    //메인 카테고리와 서브 카테고리로 검색
    @GET("items")
    Call<List<LostObject>> getItems(
            @Query("main_category") String mc,
            @Query("sub_category") String sc
    );

    //메인 카테고리와 서브 카테고리와 호선으로 검색
    @GET("items")
    Call<List<LostObject>> getItems(
            @Query("main_category") String mc,
            @Query("sub_category") String sc,
            @Query("line") String l
    );

    @GET("items")
    Call<List<LostObject>> getPosts(@QueryMap Map<String,String> querys);

    @POST("items")
    Call<LostObject> postItem(@Body LostObject obj);

    //삭제 id는 그 데이터 받아오면 getId 메소드로 가져올 수 있음 setId는 그래서 private임 사실 지워도 됐지만 그냥 냅둠
    @DELETE("items/{id}")
    Call<Void> deletePost(@Path("id") int id);

}