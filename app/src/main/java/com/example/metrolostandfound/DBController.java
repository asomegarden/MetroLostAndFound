package com.example.metrolostandfound;

import android.util.Log;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public class DBController {
    private static List<LostObject> obj;

    //DB의 모든 아이템을 List로 반환
    public static List<LostObject> getItems(){
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
                    obj = response.body();
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
}

//API 메소드 사용을 위한 인터페이스 나중에 Query도 만들거임
interface RetrofitService {
    @GET("items")
    Call<List<LostObject>> getItems();

    @GET("items/{item}")
    Call<LostObject> getItems(@Path("item") String post);

    @GET("items")
    Call<List<LostObject>> getPosts(@Query("main_category") String mc);

    @GET("items")
    Call<List<LostObject>> getPosts(
            @Query("main_category") String mc,
            @Query("sub_category") String sc
    );

    /*
    Map<String, String> querys = new HashMap<>();
    querys.put("userId", "10");
    querys.put("id", "96");
     */

    @GET("items")
    Call<List<LostObject>> getPosts(@QueryMap Map<String,String> querys);

    @POST("items")
    Call<LostObject> postItem(@Body LostObject obj);

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
/*
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

 */

}