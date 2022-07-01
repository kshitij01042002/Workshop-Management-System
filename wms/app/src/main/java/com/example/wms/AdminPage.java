package com.example.wms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        getSupportActionBar().hide();

        findViewById(R.id.checkbystatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,CheckByStatus.class);
                startActivity(intent);
            }
        });
    }

    public void EmployeeManagement(View view){
        Intent intent = new Intent(AdminPage.this,EmployeeManagement.class);
        startActivity(intent);
    }
    public void CheckStatus(View view){
        Intent intent = new Intent(AdminPage.this,CheckStatus.class);
        startActivity(intent);
    }
    public void UpdateStatus(View view){
        Intent intent = new Intent(AdminPage.this,UpdateStatus.class);
        startActivity(intent);
    }

}