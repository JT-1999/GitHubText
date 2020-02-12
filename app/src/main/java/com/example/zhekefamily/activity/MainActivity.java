package com.example.zhekefamily.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.zhekefamily.R;
import com.example.zhekefamily.fragment.FirstFragment;
import com.example.zhekefamily.fragment.ForthFragment;
import com.example.zhekefamily.fragment.SecondFragment;
import com.example.zhekefamily.fragment.ThirdFragment;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


//    private SearchView mSearchView;
    private FragmentManager mFragmentManager;
    private BottomNavigationBar bottomNavigationBar;

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private ForthFragment fourthFragment;
    //默认选择第一个fragment
    private int lastSelectedPosition = 0;
    private FragmentTransaction transaction;
    public static int logincode=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar = this.findViewById(R.id.bottom_navigation_bar);
        showNumberAndShape();
        initNavigation();

    }
    protected void onRestart() { Log.e("zz","onRestart");

        int id= getIntent().getIntExtra("id",0);
        if(id==1)
        {
            transaction.show(fourthFragment);

        }
        super.onRestart();
        }

//BottomNavigationBar
    private void initNavigation() {
        //导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setTabSelectedListener(this);//监听切换点击事件
        //bottomNavigationBar.setBarBackgroundColor("#595757");//背景颜色
        //1、BACKGROUND_STYLE_DEFAULT：如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
        //2、BACKGROUND_STYLE_STATIC：点击无水波纹效果
        //3、BACKGROUND_STYLE_RIPPLE：点击有水波纹效果
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //需要添加的item数
        bottomNavigationBar
                //选中时的图片的资源、文字
                .addItem(new BottomNavigationItem(R.drawable.home, "首页")
                        //选中的颜色
//                        .setActiveColor(R.color.colorAccent)
                        //选中的颜色(资源文件 下面同上)
                        //.setActiveColorResource(R.color.colorAccent)
                        //未选中的颜色(默认灰色 可注释)
//                        .setInActiveColor("#999999")
                        //未选中时的图片的资源
                        .setInactiveIconResource(R.drawable.home_not))
                .addItem(new BottomNavigationItem(R.drawable.shoucang, "收藏")
//                        .setActiveColorResource(R.color.colorAccent)
////                        .setInActiveColor("#999999")
                        .setInactiveIconResource(R.drawable.shoucang_not)).
                addItem(new BottomNavigationItem(R.drawable.guanzhu, "关注")
//                        .setActiveColorResource(R.color.colorAccent)
//                        .setInActiveColor("#999999")
//                        .setBadgeItem(textBadgeItem)
                        .setInactiveIconResource(R.drawable.guanzhu_not)).
                addItem(new BottomNavigationItem(R.drawable.guanzhu, "我的")
//                        .setActiveColorResource(R.color.colorAccent)
//                        .setInActiveColor("#999999")
//                        .setBadgeItem(shapeBadgeItem)
                        .setInactiveIconResource(R.drawable.guanzhu_not))
                .setFirstSelectedPosition(lastSelectedPosition)//设置默认选中项
                //.setFab(FloatingActionButton的id)//FloatingActionButton 关联
                .initialise();//注意此句放在最后
//        setIconItemMargin(bottomNavigationBar, 10, 25, 14);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        firstFragment = new FirstFragment();
        transaction.add(R.id.tb, firstFragment);
        transaction.commit();

    }



    private void showNumberAndShape() {
    }

    @Override
    public void onTabSelected(int position) {
        lastSelectedPosition = position;//每次点击赋值
        //开启事务
        transaction = mFragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (firstFragment == null) {
                    firstFragment = new FirstFragment();
                    transaction.add(R.id.tb, firstFragment);
                } else {
                    transaction.show(firstFragment);
                }
                // transaction.replace(R.id.tb, FirstFragment);
                break;
            case 1:
                if (secondFragment == null) {
                    secondFragment = new SecondFragment();
                    transaction.add(R.id.tb, secondFragment);
                } else {
                    transaction.show(secondFragment);
                }
                break;
            case 2:
                if (thirdFragment == null) {
                    thirdFragment = new ThirdFragment();
                    transaction.add(R.id.tb, thirdFragment);
                } else {
                    transaction.show(thirdFragment);
                }
                break;
            case 3:
                if (logincode==0)
                {Intent intent=new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }
                else
                {
                    if (fourthFragment == null) {
                        fourthFragment = new ForthFragment();
                        transaction.add(R.id.tb, fourthFragment);
                    } else {
                        transaction.show(fourthFragment);
                    }
                }


                break;
        }
        // 事务提交
        transaction.commit();
    }

    /**
     * 隐藏当前fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (firstFragment != null) {
            transaction.hide(firstFragment);
        }
        if (secondFragment != null) {
            transaction.hide(secondFragment);
        }
        if (thirdFragment != null) {
            transaction.hide(thirdFragment);
        }
        if (fourthFragment != null) {
            transaction.hide(fourthFragment);
        }
    }

    private TextBadgeItem textBadgeItem;
    private ShapeBadgeItem shapeBadgeItem;



    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }

    /**
     * 修改间距及图片文字大小
     * @param bottomNavigationBar
     * @param space  文字与图片的间距
     * @param imgLen  单位：dp，图片大小
     * @param textSize 单位：dp，文字大小
     */
    private void setIconItemMargin(BottomNavigationBar bottomNavigationBar, int space, int imgLen, int textSize){
        Class barClass = bottomNavigationBar.getClass();
        Field[] fields = barClass.getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            Field field = fields[i];
            field.setAccessible(true);
            if(field.getName().equals("mTabContainer")){
                try{
                    //反射得到 mTabContainer
                    LinearLayout mTabContainer = (LinearLayout) field.get(bottomNavigationBar);
                    for(int j = 0; j < mTabContainer.getChildCount(); j++){
                        //获取到容器内的各个Tab
                        View view = mTabContainer.getChildAt(j);
                        //获取到Tab内的各个显示控件
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(56));
                        FrameLayout container = (FrameLayout) view.findViewById(R.id.fixed_bottom_navigation_container);
                        container.setLayoutParams(params);
                        container.setPadding(dip2px(12), dip2px(0), dip2px(12), dip2px(0));

                        //获取到Tab内的文字控件
                        TextView labelView = (TextView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_title);
                        //计算文字的高度DP值并设置，setTextSize为设置文字正方形的对角线长度，所以：文字高度（总内容高度减去间距和图片高度）*根号2即为对角线长度，此处用DP值，设置该值即可。
                        labelView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                        labelView.setIncludeFontPadding(false);
                        labelView.setPadding(0,0,0,dip2px(20-textSize - space/2));

                        //获取到Tab内的图像控件
                        ImageView iconView = (ImageView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);
                        //设置图片参数，其中，MethodUtils.dip2px()：换算dp值
                        params = new FrameLayout.LayoutParams(dip2px(imgLen), dip2px(imgLen));
                        params.setMargins(0,0,0,space/2);
                        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                        iconView.setLayoutParams(params);
                    }
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private int dip2px(float dpValue) {
        final float scale = getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
