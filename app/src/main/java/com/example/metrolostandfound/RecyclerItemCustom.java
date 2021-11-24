package com.example.metrolostandfound;

import android.graphics.Bitmap;

//리스트뷰 아이템 클래스 아이템 수정할때 멤버 변수 수정하면 됨
public class RecyclerItemCustom {
    private int id = -1;
    private Bitmap image;
    private String main_category;
    private String sub_category;
    private String locate;

    public void setId(int i){id = i;}
    public void setImage(Bitmap b) { image = b; }
    public void setMC(String mc){ main_category = mc; }
    public void setSC(String sc){
        sub_category = sc;
    }
    public void setLocate(String l){
        locate = l;
    }

    public int getId() { return id; }
    public Bitmap getImage() { return image; }
    public String getMC(){
        return main_category;
    }
    public String getSC(){
        return sub_category;
    }
    public String getLocate(){
        return locate;
    }
}
