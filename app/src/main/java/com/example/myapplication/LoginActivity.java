package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Fragment.MeFragment;

public class LoginActivity extends AppCompatActivity {
private EditText ed_account,ed_password;
private TextView tv_zhuce;
private Button ben_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_account=findViewById(R.id.login_account);
        ed_password=findViewById(R.id.login_password);
        ben_login=findViewById(R.id.btn_login);
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        final String account = sharedPreferences.getString("account", null);
        final String password = sharedPreferences.getString("password", null);
        ed_account.setText(account);
        ed_password.setText(password);
        tv_zhuce=findViewById(R.id.tv_zhuce);
        tv_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,ZhuceActivity.class);
                startActivity(intent);
            }
        });


        ben_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.putExtra("account",ed_account.getText().toString().trim());
                    setResult(2,intent);
                    finish();
            }
        });
    }
}
