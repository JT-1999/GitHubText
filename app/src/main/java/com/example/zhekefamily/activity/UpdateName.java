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

public class UpdateName extends AppCompatActivity {

    String TAG=UpdateName.class.getCanonicalName();
    private UpdateName THIS =this;
    private EditText mEditText;
    private Button mButton;
    private int id;
    private String baseUrl;
    private String mparams;
    private String mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);
        mEditText=findViewById(R.id.et_name);
        mButton=findViewById(R.id.bt_update);
        id= UserID.UUID;
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    baseUrl="http://192.168.1.105:3030/updateList";
                    mparams="{\"id\":\""+id+"\",\"name\":\""+mEditText.getText().toString()+"\"}";
                    MyTask mTask=new MyTask();
                    mTask.execute();
                    Toast ts1 = Toast.makeText(THIS,"修改用户名为"+mEditText.getText().toString(),Toast.LENGTH_SHORT);
                    ts1.show();
                }
                catch (Exception e)
                {
                    Toast ts1 = Toast.makeText(THIS,"修改失败", Toast.LENGTH_SHORT);
                    ts1.show();
                }
            }
        });
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

            try {
                mResult=result;
                Intent intent=new Intent(THIS,MainActivity.class);
                startActivity(intent);
            }
            catch (Exception e)
            {
                Toast ts1 = Toast.makeText(THIS,"修改失败", Toast.LENGTH_SHORT);
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
