package com.example.chickenattack.zhiyin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Item{

    private String UserImage,Name,UserSign,Sign,Details,Video,Keyword;

    private List<String> DetailsImage;

    public Item(String UserImage,String Name,String UserSign,String Sign,String Details,String Video,String Keyword,List<String> DetailsImage){
        this.UserImage=UserImage;
        this.Name=Name;
        this.UserSign=UserSign;
        this.Sign=Sign;
        this.Details=Details;
        this.Video=Video;
        this.Keyword=Keyword;
        this.DetailsImage=DetailsImage;
    }

    public String getUserImage(){
        return UserImage;
    }
    public String getName(){
        return Name;
    }
    public String getUersSign(){
        return UserSign;
    }
    public String getSign(){
        return Sign;
    }
    public String getDetails(){
        return Details;
    }
    public String getVideo(){
        return Video;
    }
    public String getKeyword(){
        return Keyword;
    }
    public List<String> getDetailsImage(){
        return DetailsImage;
    }

    public void setUserImage(String UserImage){
        this.UserImage=UserImage;
    }
    public void setName(String Name){
        this.Name=Name;
    }
    public void setUserSign(String UserSign){
        this.UserSign=UserSign;
    }
    public void setSign(String Sign){
        this.Sign=Sign;
    }
    public void setDetails(String Details){
        this.Details=Details;
    }
    public void setVideo(String Video){
        this.Video=Video;
    }
    public void setKeyword(String Keyword){
        this.Keyword=Keyword;
    }
    public void setDetailsImage(List<String> DetailsImage){
        this.DetailsImage=DetailsImage;
    }


}
