package com.example.zhekefamily.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhekefamily.R;

public class Login extends AppCompatActivity {
    private Button psd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        psd=findViewById(R.id.login_bt_mima);
        psd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,PasswordLogin.class);
                startActivity(intent);
            }
        });
    }
}
