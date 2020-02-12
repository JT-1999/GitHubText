package com.example.zhekefamily.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhekefamily.R;
import com.example.zhekefamily.activity.ActivityCard;
import com.example.zhekefamily.activity.DetailedActivity;
import com.example.zhekefamily.adapter.mypageradapter;
import com.example.zhekefamily.gsonformat.HottestActivity;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


public class FirstFragment extends Fragment {

    String TAG=ThirdFragment.class.getCanonicalName();
    private FirstFragment THIS =this;
    private String baseUrl;
    private String mparams;
    private String mResult;
    private SearchView mSearchView;
    private ViewPager mViewPager;
    private Button mactivity;
    private String activitytitle;
    private TextView mTextView;
    private ArrayList<ActivityCard> alist;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_first_fragment, container, false);
        baseUrl="http://192.168.1.105:3030/findListhotest";
        mparams="";
        mSearchView=view.findViewById(R.id.search_view);
        mViewPager=view.findViewById(R.id.myviewpager);
        mactivity=view.findViewById(R.id.bt_activity);
        alist=ActivityCard.getDefaultList();
        mTextView=view.findViewById(R.id.hottest_activity);
        mViewPager.setAdapter(new mypageradapter(THIS.getContext(),alist));
        try{
            MyTask myTask=new MyTask();
            myTask.execute();
        }
        catch (Exception e)
        {
            mTextView.setText("未获取到信息");
        }
        mactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(THIS.getContext(), DetailedActivity.class);
                startActivity(intent);
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setIconifiedByDefault(false);

        return view;
    }
    private class MyTask extends AsyncTask<String,Integer,String>
    {
        private String result;
        @Override
        protected void onPreExecute() {
            // 执行前显示提示
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                Log.e(TAG,"params--post-->>"+mparams);
                // 请求的参数转换为byte数组
//            byte[] postData = params.getBytes();
                // 新建一个URL对象
                URL url = new URL(baseUrl);
                // 打开一个HttpURLConnection连接
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                // 设置连接超时时间
                urlConn.setConnectTimeout(20 * 1000);
                //设置从主机读取数据超时
                urlConn.setReadTimeout(5 * 1000);
                // Post请求必须设置允许输出 默认false
                urlConn.setDoOutput(true);
                //设置请求允许输入 默认是true
                urlConn.setDoInput(true);
                // Post请求不能使用缓存
                urlConn.setUseCaches(false);
                // 设置为Post请求
                urlConn.setRequestMethod("POST");
                //设置本次连接是否自动处理重定向
                urlConn.setInstanceFollowRedirects(true);
                //配置请求Content-Type
                urlConn.setRequestProperty("Content-Type", "application/json");
                // 开始连接
                urlConn.connect();

                // 发送请求参数
                PrintWriter dos = new PrintWriter(urlConn.getOutputStream());
                dos.write(mparams);
                dos.flush();
                dos.close();
                // 判断请求是否成功
                if (urlConn.getResponseCode() == 200) {
                    // 获取返回的数据
                    result = streamToString(urlConn.getInputStream());
                    Log.e(TAG, "Post方式请求成功，result--->" +result);
                } else {
                    Log.e(TAG, "Post方式请求失败");
                }
                // 关闭连接
                urlConn.disconnect();
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            return null;
        }
        @Override
        protected void onPostExecute(String res) {
            // 执行完毕后，则更新UI
            mResult=result;
            try {
                Gson mgson=new Gson();
                HottestActivity b=mgson.fromJson(mResult, HottestActivity.class);
                activitytitle=b.getObj().getList().getActivityTittle();
                mTextView.setText(activitytitle);
            }
            catch (Exception e)
            {
                Toast ts1 = Toast.makeText(THIS.getContext(),"未连接到网络", Toast.LENGTH_SHORT);
                ts1.show();
                mTextView.setText("未获取到信息");
            }
        }

    }





    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }

    }
}

