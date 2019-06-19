package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ZhuceActivity extends AppCompatActivity {
private EditText ed_account,ed_password;
private Button btn_zhuce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ed_account=findViewById(R.id.ed_account);
        ed_password=findViewById(R.id.ed_password);
        btn_zhuce=findViewById(R.id.btn_zhuce);
        final String account = ed_account.getText().toString();
        final String password = ed_password.getText().toString();
        SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("account",account);
        editor.putString("password",password);
        editor.commit();
        btn_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Toast.makeText(ZhuceActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();



            }
        });
    }
}
