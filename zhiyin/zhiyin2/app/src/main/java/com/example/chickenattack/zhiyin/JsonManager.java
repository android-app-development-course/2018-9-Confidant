package com.example.chickenattack.zhiyin;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chicken Attack on 2018/12/28.
 */

public class JsonManager {

    private List<Item_list> item_list= new ArrayList<>();

    private Context context;

    public JsonManager(Context context){
        this.context=context;
    };

    public List<Item_list> getdata(String data){
        parseJSONWithJSONObject(data);
        return item_list;
    }

    private void parseJSONWithJSONObject(String jsonData){
        try {
            //将json字符串jsonData装入JSON数组，即JSONArray
            //jsonData可以是从文件中读取，也可以从服务器端获得
            JSONObject jsonObject_total=new JSONObject(jsonData.toString());
            int total=Integer.valueOf(jsonObject_total.names().get(jsonObject_total.names().length()-1).toString()).intValue();
            int record_number=0;
            for (int i = 0; i<= total; i++) {
                //检测i序号是否存在
                boolean judge=false;
                int now_number=Integer.valueOf(jsonObject_total.names().get(record_number).toString()).intValue();
                if(i!=now_number){
                    continue;
                }
                record_number++;

                //循环遍历，依次取出JSONObject对象
                //用getInt和getString方法取出对应键值
                String record=String.valueOf(i);

                JSONObject jsonObject = jsonObject_total.getJSONObject(record);

                List<String> DetailsImage=new ArrayList<>(),zhuanfa_DetailsImage=new ArrayList<>();


                String Sign=jsonObject.getString("分类标签");           //分类不同，不选入
                if(!Sign.matches(MainActivity.Kind_string[MainActivity.Kind])&&MainActivity.Kind!=0){
                    continue;
                }
                String UserImage=jsonObject.getString("UserImage");
                String Name=jsonObject.getString("Name");
                String UserSign=jsonObject.getString("发表时间");
                String Details=jsonObject.getString("原始文本");
                String Video=jsonObject.getString("视频地址");
                String Keyword=jsonObject.getString("关键字");
                for(int j=1;j<10;j++){
                    String temp_name="图片地址"+String.valueOf(j);
                    String temp=jsonObject.getString(temp_name);
                    if(!temp.matches("null")){
                        DetailsImage.add(temp);
                    }else{
                        break;
                    }
                }
                if(DetailsImage.isEmpty()){
                    DetailsImage.add("null");
                }
                String zhuanfa_Name=jsonObject.getString("zhuanfa_Name");
                String zhuanfa_Details=jsonObject.getString("zhuanfa_Details");
                String zhuanfa_Video=jsonObject.getString("转发视频");
//                String zhuanfa_Keyword=jsonObject.getString("转发关键词");
                String zhuanfa_Keyword="TEXT";
                for(int j=1;j<10;j++){
                    String temp_name="zf图片地址"+String.valueOf(j);
                    String temp=jsonObject.getString(temp_name);
                    if(!temp.matches("null")){
                        zhuanfa_DetailsImage.add(temp);
                    }else{
                        break;
                    }
                }
                if(zhuanfa_DetailsImage.isEmpty()){
                    zhuanfa_DetailsImage.add("null");
                }

                Item item,item_zhuanfa;
                item=new Item(UserImage,Name,UserSign,Sign,Details,Video,Keyword,DetailsImage);
                item_zhuanfa=new Item("null",zhuanfa_Name,"null","null",zhuanfa_Details,zhuanfa_Video,zhuanfa_Keyword,zhuanfa_DetailsImage);

                Item_list itemlist=new Item_list(item,item_zhuanfa);
                item_list.add(itemlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
