package com.example.chickenattack.zhiyin;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private ImageView Icon_left,Icon_right;

    private EditText User,Password;

    private Button Login_button,Register_button,Forget_password_button;

    private ImageButton Wechat,QQ,Weibo;

    private String userName,userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Icon_left=findViewById(R.id.Icon_22);
        Icon_right=findViewById(R.id.Icon_33);
        User=findViewById(R.id.user);
        Password=findViewById(R.id.password);
        Login_button=findViewById(R.id.button_login);
        Register_button=findViewById(R.id.button_register);
        Forget_password_button=findViewById(R.id.button_forget_password);
        Wechat=findViewById(R.id.wechat);
        QQ=findViewById(R.id.qq);
        Weibo=findViewById(R.id.weibo);


        User.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view,boolean hasFocus) {
                if(hasFocus){
                    Icon_left.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.icon_22));
                    Icon_right.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.icon_33));
                } else{
                    InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                }
            }
        });

        Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view,boolean hasFocus) {
                if(hasFocus){
                    Icon_left.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.icon_22_p));
                    Icon_right.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.icon_33_p));
                }else{
                    InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                }
            }
        });

        Login_button.setOnClickListener(new View.OnClickListener() {        //登录，检查用户名、密码，接受反馈
            @Override
            public void onClick(View view) {
                userName=User.getText().toString();             //获取用户名密码
                userPassword=Password.getText().toString();
                //提交服务器检测，若接受反馈
                if(userName.matches("kami")&&userPassword.matches("20181227")){               //成功跳转
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                } else{                 //失败显示提示
                    Password.setText("");
                    Toast.makeText(Login.this,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "跳转至微博注册处",Toast.LENGTH_SHORT).show();
            }
        });

        Forget_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "跳转至微博找回密码处",Toast.LENGTH_SHORT).show();
            }
        });

        Wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "无效按钮",Toast.LENGTH_SHORT).show();
            }
        });

        QQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "无效按钮",Toast.LENGTH_SHORT).show();
            }
        });

        Weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "使用微博APP授权方式",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
