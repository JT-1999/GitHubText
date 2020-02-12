package com.example.zhekefamily.activity;

import com.example.zhekefamily.R;

import java.util.ArrayList;

public class ActivityCard {
    public String name;
    public String desc;
    public int pic;
    public ActivityCard() {
        name="";
        desc="";
        pic=0;
    }
    private static String[] mNameArray = {
            "安卓答辩", "三维场景设计答辩", "足球俱乐部", "安卓答辩"
    };
    private static String[] mDescArray = {
            "安卓答辩第二批2020/1/8",
            "三维场景设计答辩2020/1/4",
            "小和山足球俱乐部",
            "安卓答辩第二批2020/1/8",

    };
    private static int[] mPicArray = {
            R.drawable.android, R.drawable.jianmo, R.drawable.football,
            R.drawable.android
    };

    public  static ArrayList<ActivityCard> getDefaultList()
    {
        ArrayList<ActivityCard> activitylist=new ArrayList<>();
        for (int i = 0; i < mNameArray.length; i++) {
         ActivityCard activityCard=new ActivityCard();
         activityCard.name=mNameArray[i];
         activityCard.desc=mDescArray[i];
         activityCard.pic=mPicArray[i];
         activitylist.add(activityCard);
        }
        return activitylist;
    }
}
