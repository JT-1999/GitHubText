package com.example.zhekefamily.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhekefamily.R;
import com.example.zhekefamily.activity.ActivityCard;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class mypageradapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<ActivityCard> mActivityList=new ArrayList<>();

    public  mypageradapter(Context context,ArrayList<ActivityCard> viewLists)
    {
        this.mContext = context;
        this.mActivityList = viewLists;

    }


    @Override
    public int getCount() {
        return mActivityList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View myview=View.inflate(container.getContext(), R.layout.cardview,null);
        ActivityCard myactivitycard=mActivityList.get(position);
        ((TextView)myview.findViewById(R.id.text1)).setText(myactivitycard.name);
        ((TextView)myview.findViewById(R.id.text2)).setText(myactivitycard.desc);
        ((ImageView)myview.findViewById(R.id.myimage)).setImageResource(myactivitycard.pic);
        container.addView(myview);
        return myview;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
