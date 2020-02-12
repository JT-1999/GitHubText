package com.example.zhekefamily.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zhekefamily.R;
import com.example.zhekefamily.activity.Users_item;
import com.example.zhekefamily.adapter.RecyclerViewAdapter;
import com.example.zhekefamily.gsonformat.InterestText;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ThirdFragment extends Fragment {
    String TAG=ThirdFragment.class.getCanonicalName();
    private List<Users_item> userList = new ArrayList<>();
    private ThirdFragment THIS =this;
    private String mparams;
    private String baseUrl;
    private String mResult;
    private String interestname;
    private int interestSize;
    private List<String> iname=new ArrayList<>();
    private RecyclerView mrecycleview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_third_fragment, container, false);
        iname.add("");
        iname.add("");
        iname.add("");
        iname.add("");
        iname.add("");
        iname.add("");
        iname.add("");
        iname.add("");
        iname.add("");
        iname.add("");
        mrecycleview=view.findViewById(R.id.recyclerview);
        mparams="";
        baseUrl="http://192.168.1.105:3030/findList3";
        try{
            MyTask myTask=new MyTask();
            myTask.execute();
        }
        catch(Exception e){
            Toast ts1 = Toast.makeText(THIS.getContext(),"未获取到数据", Toast.LENGTH_SHORT);
            ts1.show();

        }
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
            try{
                Gson mGson=new Gson();
                InterestText b=mGson.fromJson(mResult,InterestText.class);
//            interestname=b.getObj().getList().get(1).getInterestName();
                interestSize=b.getObj().getList().size();
                for (int i=0;i < interestSize;i++)
                {
                    iname.set(i,b.getObj().getList().get(i).getInterestName());
                    String c=iname.get(i);
                    Users_item user1=new Users_item(c,R.drawable.dbl);
                    userList.add(user1);
                    LinearLayoutManager layoutManager=new LinearLayoutManager(THIS.getContext());
                    mrecycleview.setLayoutManager(layoutManager);
                    RecyclerViewAdapter madapter=new RecyclerViewAdapter(userList);
                    mrecycleview.setAdapter(madapter);
                }
            }
            catch (Exception e)
            {
                Toast ts1 = Toast.makeText(THIS.getContext(),"未连接到网络", Toast.LENGTH_SHORT);
                ts1.show();
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
