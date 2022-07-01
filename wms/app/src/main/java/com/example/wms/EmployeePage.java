package com.example.wms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class EmployeePage extends AppCompatActivity {

    Button jobcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);
        getSupportActionBar().hide();

        jobcard = findViewById(R.id.jobcard);
        jobcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeePage.this,JobCard.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.checkbystatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeePage.this,CheckByStatus.class);
                startActivity(intent);
            }
        });
    }
    public void CheckStatus(View view){
        Intent intent = new Intent(EmployeePage.this,CheckStatus.class);
        startActivity(intent);
    }
    public void UpdateStatus(View view) {
        Intent intent = new Intent(EmployeePage.this,UpdateStatus.class);
        startActivity(intent);
    }
}