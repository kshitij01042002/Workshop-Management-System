package com.example.wms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_management);
        getSupportActionBar().hide();
    }
    public void View(View view){
        Intent intent = new Intent(EmployeeManagement.this,ViewEmployees.class);
        startActivity(intent);
    }
    public void Register(View view){
        Intent intent = new Intent(EmployeeManagement.this,EmployeeRegistration.class);
        startActivity(intent);
    }
    public void Remove(View view){
        Intent intent = new Intent(EmployeeManagement.this,RemoveEmployeeData.class);
        startActivity(intent);
    }
}