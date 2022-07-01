package com.example.wms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void AdminActivity(View view){
        Intent intent = new Intent(MainActivity.this,AdminLoginActivity.class);
        startActivity(intent);
    }

    public void EmployeeActivity(View view){
        Intent intent = new Intent(MainActivity.this,EmployeeLoginActivity.class);
        startActivity(intent);
    }
}