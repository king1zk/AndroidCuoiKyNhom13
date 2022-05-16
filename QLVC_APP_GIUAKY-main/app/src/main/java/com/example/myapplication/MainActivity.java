package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {
    DBHelper DBhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        Tools.setSystemBarLight(this);
        Tools.setSystemBarColor(this, R.color.white);

        final TextView username = (TextView) findViewById(R.id.username);
        final TextView password = (TextView) findViewById(R.id.password);

        TextView loginbtn = (TextView) findViewById(R.id.login);
        TextView signup = (TextView) findViewById(R.id.singup);
        DBhelper = new DBHelper(this, "qlvc.sqlite", null, 1);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignUp.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = DBhelper.checkusernamepassword(user,pass);
                    if (checkuserpass == true){
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, dashboard.class));
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }

                }

                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công. Bạn đang đăng nhập vào tài khoản root", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, dashboard.class));
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                } else {
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}