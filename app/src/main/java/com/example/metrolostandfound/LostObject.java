package com.example.metrolostandfound;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

//DB에서 파일 불러온거 담기 위한 클래스
//다 String인건 어쩔 수 없는듯 다 따로 Text나 Date로 변환하는 과정 필요
//나중에 getter 함수들에서 처리해주면 될듯? 근데 일단 귀찮아서 나중에
//setter에서 Text로 받아서 String으,로 해주고 하는 것도 필요할듯
//생각해보니까 Text로 굳이 쓸 필요가 없네 Date같은 것만 해주면 될듯

public class LostObject implements Cloneable
{
    private String passwd;
    private String sub_category;
    private String line;
    private String storage;
    private String date_time;
    private String contents;
    private String station;
    private String main_category;
    private String image;

    //얘들은 DB 등록될 때 자동으로 만들어지는 정보
    private String id;
    private String published_at;
    private String updated_at;
    private String created_at;

    public LostObject(){
        passwd = null;
        sub_category = null;
        line = null;
        storage = null;
        date_time = null;
        contents = null;
        station = null;
        main_category = null;
        image = null;
        id = null;
        published_at = null;
        updated_at = null;
        created_at = null;
    }

    public LostObject(LostObject o){
        this.passwd = o.passwd;
        this.sub_category = o.sub_category;
        this.line = o.line;
        this.storage = o.storage;
        this.date_time = o.date_time;
        this.contents = o.contents;
        this.station = o.station;
        this.main_category = o.main_category;
        this.image = o.image;

        this.id = o.id;
        this.published_at = o.published_at;
        this.updated_at = o.updated_at;
        this.created_at = o.created_at;
    }

    public static LostObject newInstance(LostObject o){
        LostObject newObj = new LostObject();
        newObj.passwd = o.passwd;
        newObj.sub_category = o.sub_category;
        newObj.line = o.line;
        newObj.storage = o.storage;
        newObj.date_time = o.date_time;
        newObj.contents = o.contents;
        newObj.station = o.station;
        newObj.main_category = o.main_category;
        newObj.image = o.image;

        newObj.id = o.id;
        newObj.published_at = o.published_at;
        newObj.updated_at = o.updated_at;
        newObj.created_at = o.created_at;

        return newObj;
    }

    //10/31 image는 json에서 변환해오는 과정에 문제가 있어서 다른 방법을 좀 찾아봐야함
    //11/01 원래 이미지만 저장하는 테이블 만들어서 따로 받아오면 어떨까 했는데 
    //      생각해보니까 데이터에 해당하는 이미지 불러오려면 id까지 저장해야 해서 똑같은 오류가 발생할 기분
    //      그래서 그냥 String으로 저장하고 Bitmap - String간 변환을 통해 해보면 어떨까 해서 그렇게 해봄
    //      사용하는 사람은 그냥 Bitmap 이미지를 쓴다고 생각하고 쓰면 됨 변환 알아서 다 됨
    //      근데 이건 DB에서 임의로 올릴 수 있는게 아니라 나중에 등록 구현하고 앱에서 등록해봐야함

    public String getPasswd () { return passwd; }
    public void setPasswd (String passwd) { this.passwd = passwd; }

    public String getSubCategory () { return sub_category; }
    public void setSubCategory (String sub_category) { this.sub_category = sub_category; }

    public String getLine () { return line; }
    public void setLine (String line) { this.line = line; }

    public String getStorage () { return storage; }
    public void setStorage (String storage) { this.storage = storage; }

    public String getDateTime () { return date_time; }
    public void setDateTime (String date_time) { this.date_time = date_time; }

    public String getContents () { return contents; }
    public void setContents (String contents) { this.contents = contents; }

    public String getStation () { return station; }
    public void setStation (String station) { this.station = station; }

    public String getMainCategory () { return main_category; }
    public void setMainCategory (String main_category) { this.main_category = main_category; }

    //String으로 이미지 데이터 관리 - retrofit 라이브러리가 이미지를 받아오는 데에 문제가 있어서
    public Bitmap getImage(){ return BitmapConverter.StringToBitmap(image); }
    public void setImage(Bitmap img){ image = BitmapConverter.BitmapToString(img); }

    @Override
    public String toString()
    {
        return "LostObject [item_id = "+passwd+", sub_category = "+sub_category+", line = "+line+", created_at = "+created_at+", storage = "+storage+", date_time = "+date_time+", updated_at = "+updated_at+", contents = "+contents+", station = "+station+", main_category = "+main_category+", id = "+id+", published_at = "+published_at+", image = "+image+"]";
    }

    //이 밑의 항목들은 따로 지정해주지 않아도 DB에서 알아서 지정, 읽을 때만 있으면 됨
    public String getId () { return id; }
    private void setId (String id) { this.id = id; }
    private String getPublished_at () { return published_at; }
    private void setPublished_at (String published_at) { this.published_at = published_at; }
    private String getCreated_at () { return created_at; }
    private void setCreated_at (String created_at) { this.created_at = created_at; }
    private String getUpdated_at () { return updated_at; }
    private void setUpdated_at (String updated_at) { this.updated_at = updated_at; }



}

// 비트맵과 스트링 간의 변환을 위한 클래스
// 출처 : https://youngest-programming.tistory.com/95
class BitmapConverter {
    /*
     * String형을 BitMap으로 변환시켜주는 함수
     * */
    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /*
     * Bitmap을 String형으로 변환
     * */
    public static String BitmapToString(Bitmap bitmap) {
        String temp = "";
        if(bitmap != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
            byte[] bytes = baos.toByteArray();
            temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        }
        return temp;
    }

    /*
     * Bitmap을 byte배열로 변환
     * */
    public static byte[] BitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        return baos.toByteArray();
    }
}
