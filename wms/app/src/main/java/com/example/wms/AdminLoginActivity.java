package com.example.wms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AdminLoginActivity extends AppCompatActivity {

    public EditText id;
    EditText adminpassword;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getSupportActionBar().hide();

    }
    public void AdminActivity(View view){
        Intent intent = new Intent(AdminLoginActivity.this,AdminPage.class);

        id = findViewById(R.id.ID);
        adminpassword = findViewById(R.id.employeepassword);
        String adminid = id.getText().toString().trim();
        String password = adminpassword.getText().toString().trim();
        if (adminid.contentEquals("Kshitij") && password.contentEquals("123456")){
            id.setText("");
            adminpassword.setText("");
            startActivity(intent);
        }
        else {
            Toast.makeText(AdminLoginActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        }

    }

}