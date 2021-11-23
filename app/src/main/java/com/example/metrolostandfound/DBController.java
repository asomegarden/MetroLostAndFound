package com.example.metrolostandfound;

import android.util.Log;

import java.util.ArrayList;
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
    private static List<LostObject> obj;

    static {
        obj = new ArrayList<>();
    }

    //DB의 모든 아이템을 List로 반환
    public static List<LostObject> getItems(){
        obj.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.64.198.240:1337/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<List<LostObject>> call = service.getItems();
        List<LostObject> list = new ArrayList<>();

        call.enqueue((new Callback<List<LostObject>>() {
            @Override
            public void onResponse(Call<List<LostObject>> call, Response<List<LostObject>> response) {
                if(response.isSuccessful()) {
                    for(LostObject o : response.body()){
                        list.add(LostObject.newInstance(o));
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
        Log.d("테스트", list.toString());
        return obj;
    }

    //메인 카테고리로 검색
    public static List<LostObject> getItems(String mc){
        obj.clear();

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
                    obj.addAll(response.body());
                    Log.d("result", obj.toString());
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

        return obj;
    }

    //메인 카테고리 + 서브 카테고리로 검색
    public static List<LostObject> getItems(String mc, String sc){
        obj.clear();

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
                    obj.addAll(response.body());
                    Log.d("result", obj.toString());
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

        return obj;
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

        Call call = service.deletePost(id);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(!response.isSuccessful()){
                    Log.d("Post 오류", response.message());
                    return;
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("Post 실패", "이유 : ");
                t.printStackTrace();
            }
        });
    }
}

//API 메소드 사용을 위한 인터페이스 나중에 Query도 만들거임
interface RetrofitService {
    //전부 받아오기
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

    @GET("items")
    Call<List<LostObject>> getPosts(@QueryMap Map<String,String> querys);

    @POST("items")
    Call<LostObject> postItem(@Body LostObject obj);

    //삭제 id는 그 데이터 받아오면 getId 메소드로 가져올 수 있음 setId는 그래서 private임 사실 지워도 됐지만 그냥 냅둠
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

    //이건 아이디로 하나만 가져오는 건데 사실상 필요없음 그리고 메인 카테고리로 검색이랑 오버라이딩이 겹쳐서 안 쓸듯
    //@GET("items/{item}")
    //Call<LostObject> getItems(@Path("item") String post);

    /*
    Map<String, String> querys = new HashMap<>();
    querys.put("userId", "10");
    querys.put("id", "96");
     */



    /*
    @FormUrlEncoded
    @POST("posts")
    Call<PostResult> setPostField(
	@Field("userId") String userId,
   	@Field("title") String title,
   	@Field("body") String body
);

    @FormUrlEncoded
    @POST("posts")
    Call<PostResult> setPostFieldMap(
	@FieldMap Map<String, String> fieldMap
);
     */

    

    
}