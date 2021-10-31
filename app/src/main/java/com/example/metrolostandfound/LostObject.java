package com.example.metrolostandfound;

import android.media.Image;

//DB에서 파일 불러온거 담기 위한 클래스
//다 String인건 어쩔 수 없는듯 다 따로 Text나 Date로 변환하는 과정 필요
//나중에 getter 함수들에서 처리해주면 될듯? 근데 일단 귀찮아서 나중에
//setter에서 Text로 받아서 String으,로 해주고 하는 것도 필요할듯
//생각해보니까 Text로 굳이 쓸 필요가 없네 Date같은 것만 해주면 될듯

public class LostObject
{
    private String item_id;
    private String sub_category;
    private String line;
    private String storage;
    private String date_time;
    private String contents;
    private String station;
    private String main_category;

    //얘들은 DB 등록될 때 자동으로 만들어지는 정보
    private String id;
    private String published_at;
    private String updated_at;
    private String created_at;
    
    //image는 json에서 변환해오는 과정에 문제가 있어서 다른 방법을 좀 찾아봐야함

    public String getItemId () { return item_id; }
    public void setItem_id (String item_id) { this.item_id = item_id; }

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

    @Override
    public String toString()
    {
        return "LostObject [item_id = "+item_id+", sub_category = "+sub_category+", line = "+line+", created_at = "+created_at+", storage = "+storage+", date_time = "+date_time+", updated_at = "+updated_at+", contents = "+contents+", station = "+station+", main_category = "+main_category+", id = "+id+", published_at = "+published_at+"]";
    }

    //이 밑의 항목들은 따로 지정해주지 않아도 DB에서 알아서 지정, 읽을 때만 있으면 됨
    private String getId () { return id; }
    private void setId (String id) { this.id = id; }
    private String getPublished_at () { return published_at; }
    private void setPublished_at (String published_at) { this.published_at = published_at; }
    private String getCreated_at () { return created_at; }
    private void setCreated_at (String created_at) { this.created_at = created_at; }
    private String getUpdated_at () { return updated_at; }
    private void setUpdated_at (String updated_at) { this.updated_at = updated_at; }
}
