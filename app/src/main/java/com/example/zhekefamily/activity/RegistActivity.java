package com.example.zhekefamily.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhekefamily.R;
import com.example.zhekefamily.gsonformat.UsersText;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class RegistActivity extends AppCompatActivity {
    String TAG = RegistActivity.class.getCanonicalName();
    private Button registerandlogin;
    private EditText et_data_phonenumber;
    private EditText et_data_password;
    private EditText et_data_name;
    private String selectresult1;
    private String selectresult;
    private String addresult;
    private String addurl;
    private String selecturl;
    private String addparam;
    private String selectparam;
//    private HashMap<String,String> stringHashMap;

//    指定一个键为String，值也为String的HashMap


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        registerandlogin = findViewById(R.id.register_bt_gettologin);
        et_data_phonenumber = findViewById(R.id.register_et_phonenumber);
        et_data_password = findViewById(R.id.register_et_password);
        et_data_name = findViewById(R.id.register_name);
        registerandlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addurl="http://192.168.1.105:3030/newList";
                    addparam="{\"PhoneNumber\":\""+et_data_phonenumber.getText().toString()
                            +"\",\"Password\":\""+et_data_password.getText().toString()+"\",\"UserName\":\""
                            +et_data_name.getText().toString()+"\"}";
                    myAddTask myaddtask=new myAddTask();
                    myaddtask.execute();
                    Intent intent=new Intent(RegistActivity.this,MainActivity.class);
                    MainActivity.logincode = 1;
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast ts1 = Toast.makeText(RegistActivity.this,"没有注册成功呢", Toast.LENGTH_SHORT);
                    ts1.show();
                }

            }
        });

    }

    private class myAddTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            // 执行前显示提示
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Log.e(TAG, "params--post-->>" + addparam);
                // 请求的参数转换为byte数组
//            byte[] postData = params.getBytes();
                // 新建一个URL对象
                URL url = new URL(addurl);
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
                dos.write(addparam);
                dos.flush();
                dos.close();
                // 判断请求是否成功
                if (urlConn.getResponseCode() == 200) {
                    // 获取返回的数据
                    addresult = streamToString(urlConn.getInputStream());
                    Log.e(TAG, "Post方式请求成功，result--->" +addresult);
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
            selecturl = "http://192.168.1.105:3030/findList";
            selectparam="{\"phonenumber\":\""+et_data_phonenumber.getText().toString()+"\"}";
            mySelectTask myselecttask=new mySelectTask();
            myselecttask.execute();

        }
    }
    private class mySelectTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            // 执行前显示提示
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Log.e(TAG, "params--post-->>" + selectparam);
                // 请求的参数转换为byte数组
//            byte[] postData = params.getBytes();
                // 新建一个URL对象
                URL url = new URL(selecturl);
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
                dos.write(selectparam);
                dos.flush();
                dos.close();
                // 判断请求是否成功
                if (urlConn.getResponseCode() == 200) {
                    // 获取返回的数据
                    selectresult1 = streamToString(urlConn.getInputStream());
                    Log.e(TAG, "Post方式请求成功，result--->" + selectresult1);
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
            selectresult=selectresult1;
            try{
                Gson mgson=new Gson();
                UsersText b=mgson.fromJson(selectresult,UsersText.class);
                UserID.UUID=b.getObj().getList().getUserID();

            }
            catch (Exception e)
            {
                Toast ts1 = Toast.makeText(RegistActivity.this,"哎呀出错啦", Toast.LENGTH_SHORT);
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




