package com.example.metrolostandfound;

import android.graphics.Bitmap;

//리스트뷰 아이템 클래스 아이템 수정할때 멤버 변수 수정하면 됨
public class RecyclerItemCustom {
    private Bitmap image;
    private String name;
    private String category;
    private String locate;

    public void setImage(Bitmap b) { image = b; }
    public void setName(String n){
        name = n;
    }
    public void setCategory(String c){
        category = c;
    }
    public void setLocate(String l){
        locate = l;
    }

    public Bitmap getImage() { return image; }
    public String getName(){
        return name;
    }
    public String getCategory(){
        return category;
    }
    public String getLocate(){
        return locate;
    }
}
