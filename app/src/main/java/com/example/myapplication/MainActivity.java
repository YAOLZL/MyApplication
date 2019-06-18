package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.myapplication.Fragment.MeFragment;
import com.example.myapplication.Fragment.MengFragment;

public class MainActivity extends AppCompatActivity {
   TextView test;
   private Fragment fragment;
   private FragmentManager fragmentManager;
   private FragmentTransaction fragmentTransaction;
   private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioButton=findViewById(R.id.rb_home);
        radioButton.setChecked(true);

    }
    public  void toshouye(View view){
        fragment=new MengFragment(MainActivity.this);
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.f1,fragment);
        fragmentTransaction.commit();
    }
    public  void tome(View view){
        fragment=new MeFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.f1,fragment);
        fragmentTransaction.commit();
    }
}
