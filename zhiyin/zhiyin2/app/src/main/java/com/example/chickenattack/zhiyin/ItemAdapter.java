package com.example.chickenattack.zhiyin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chicken Attack on 2018/12/24.
 */

public class ItemAdapter extends ArrayAdapter<Item_list> {
    private int resourceId;

    public ItemAdapter(Context context, int textViewResourceId, List<Item_list> list) {
        super(context,textViewResourceId, list);
        this.resourceId = textViewResourceId;
    }
//UserImage,Name,UersSign,Sign,Details,DetailsImage
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item_list item_list = getItem(position);
        Item item=item_list.getItem_main();
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        ImageView UserImage = (ImageView) view.findViewById(R.id.Item_UserImage);
        TextView Name=(TextView) view.findViewById(R.id.Item_Name);
        TextView UserSign=(TextView) view.findViewById(R.id.Item_UserSign);
        TextView Sign=(TextView) view.findViewById(R.id.Item_Sign);                         //判断是否为全局变量所示类型，若不是则隐藏此Item
        TextView Details=(TextView) view.findViewById(R.id.Item_Details);
        TextView Video=(TextView) view.findViewById(R.id.Item_Video);
        TextView Keyword=(TextView)view.findViewById(R.id.Item_Keyword);
        Glide.with(view).load(item.getUserImage()).into(UserImage);
        Name.setText(item.getName());
        UserSign.setText(item.getUersSign());
        Sign.setText(item.getSign());
        Details.setText(item.getDetails());
        if(!item.getVideo().matches("null")){
            //视频链接与此TextView相关联
            Video.setText("@此处有视频");
        }else{
            Video.setVisibility(View.GONE);
        }
        Keyword.setText("关键词："+item.getKeyword());


        List<ImageView> imageViewList=new ArrayList<ImageView>();
        ImageView DetailsImage1=(ImageView) view.findViewById(R.id.Item_DetailsImage1);
        ImageView DetailsImage2=(ImageView) view.findViewById(R.id.Item_DetailsImage2);
        ImageView DetailsImage3=(ImageView) view.findViewById(R.id.Item_DetailsImage3);
        ImageView DetailsImage4=(ImageView) view.findViewById(R.id.Item_DetailsImage4);
        ImageView DetailsImage5=(ImageView) view.findViewById(R.id.Item_DetailsImage5);
        ImageView DetailsImage6=(ImageView) view.findViewById(R.id.Item_DetailsImage6);
        ImageView DetailsImage7=(ImageView) view.findViewById(R.id.Item_DetailsImage7);
        ImageView DetailsImage8=(ImageView) view.findViewById(R.id.Item_DetailsImage8);
        ImageView DetailsImage9=(ImageView) view.findViewById(R.id.Item_DetailsImage9);
        imageViewList.add(DetailsImage1);
        imageViewList.add(DetailsImage2);
        imageViewList.add(DetailsImage3);
        imageViewList.add(DetailsImage4);
        imageViewList.add(DetailsImage5);
        imageViewList.add(DetailsImage6);
        imageViewList.add(DetailsImage7);
        imageViewList.add(DetailsImage8);
        imageViewList.add(DetailsImage9);
        if(!item.getDetailsImage().get(0).matches("null")){
            for(int i=0;i<item.getDetailsImage().size();i++){                               //加载图片
                Glide.with(view).load(item.getDetailsImage().get(i)).into(imageViewList.get(i));
            }
            for(int i=item.getDetailsImage().size();i<9;i++){
                imageViewList.get(i).setVisibility(View.GONE);
            }
        }else{
            for(int i=0;i<9;i++){
                imageViewList.get(i).setVisibility(View.GONE);
            }
        }




        Item item_zhuanfa=item_list.getItem_other();
        LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.zhuanfa);
        if(item_zhuanfa.getName().matches("null")){         //无转发
            linearLayout.setVisibility(View.GONE);
        }else{
            TextView zhuanfa_Name=(TextView)view.findViewById(R.id.Item_zhuanfa_Name);
            TextView zhuanfa_Details=(TextView)view.findViewById(R.id.Item_zhuanfa_Details);
            TextView zhuanfa_Video=(TextView)view.findViewById(R.id.Item_zhuanfa_Video);
            zhuanfa_Name.setText("@"+item_zhuanfa.getName());
            zhuanfa_Details.setText(item_zhuanfa.getDetails());
            if(!item_zhuanfa.getVideo().matches("null")){
                //视频链接与此TextView相关联
                zhuanfa_Video.setText("@此处有视频");
            }else{
                zhuanfa_Video.setVisibility(View.GONE);
            }


            List<ImageView> zhuanfa_imageViewList=new ArrayList<ImageView>();
            ImageView zhuanfa_DetailsImage1=(ImageView) view.findViewById(R.id.Item_zhuanfa_DetailsImage1);
            ImageView zhuanfa_DetailsImage2=(ImageView) view.findViewById(R.id.Item_zhuanfa_DetailsImage2);
            ImageView zhuanfa_DetailsImage3=(ImageView) view.findViewById(R.id.Item_zhuanfa_DetailsImage3);
            ImageView zhuanfa_DetailsImage4=(ImageView) view.findViewById(R.id.Item_zhuanfa_DetailsImage4);
            ImageView zhuanfa_DetailsImage5=(ImageView) view.findViewById(R.id.Item_zhuanfa_DetailsImage5);
            ImageView zhuanfa_DetailsImage6=(ImageView) view.findViewById(R.id.Item_zhuanfa_DetailsImage6);
            ImageView zhuanfa_DetailsImage7=(ImageView) view.findViewById(R.id.Item_zhuanfa_DetailsImage7);
            ImageView zhuanfa_DetailsImage8=(ImageView) view.findViewById(R.id.Item_zhuanfa_DetailsImage8);
            ImageView zhuanfa_DetailsImage9=(ImageView) view.findViewById(R.id.Item_zhuanfa_DetailsImage9);
            zhuanfa_imageViewList.add(zhuanfa_DetailsImage1);
            zhuanfa_imageViewList.add(zhuanfa_DetailsImage2);
            zhuanfa_imageViewList.add(zhuanfa_DetailsImage3);
            zhuanfa_imageViewList.add(zhuanfa_DetailsImage4);
            zhuanfa_imageViewList.add(zhuanfa_DetailsImage5);
            zhuanfa_imageViewList.add(zhuanfa_DetailsImage6);
            zhuanfa_imageViewList.add(zhuanfa_DetailsImage7);
            zhuanfa_imageViewList.add(zhuanfa_DetailsImage8);
            zhuanfa_imageViewList.add(zhuanfa_DetailsImage9);
            if(!item_zhuanfa.getDetailsImage().get(0).matches("null")){
                for(int i=0;i<item_zhuanfa.getDetailsImage().size();i++){
                    Glide.with(view).load(item_zhuanfa.getDetailsImage().get(i)).into(zhuanfa_imageViewList.get(i));
                }
                for(int i=item_zhuanfa.getDetailsImage().size();i<9;i++){
                    zhuanfa_imageViewList.get(i).setVisibility(View.GONE);
                }
            }else{
                for(int i=0;i<9;i++){
                    zhuanfa_imageViewList.get(i).setVisibility(View.GONE);
                }
            }

        }


        return view;

    }
}
