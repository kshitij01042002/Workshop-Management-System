package com.example.wms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class EmployeeLoginActivity extends AppCompatActivity {

    EditText ID,employeepassword;
    Button login;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        getSupportActionBar().hide();
        ID = findViewById(R.id.ID);
        employeepassword = findViewById(R.id.employeepassword);
        login = findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ID.getText().toString();
                String password = employeepassword.getText().toString();
                if (email.isEmpty()) {
                    ID.setError(getString(R.string.input_error_email));
                    ID.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    ID.setError(getString(R.string.input_error_email_invalid));
                    ID.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    employeepassword.setError(getString(R.string.input_error_password));
                    employeepassword.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    employeepassword.setError(getString(R.string.input_error_password_length));
                    employeepassword.requestFocus();
                    return;
                }
                else {
                    regis(email,password);

                    }
                }
        });
    }
    private void regis(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EmployeeLoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EmployeeLoginActivity.this,EmployeePage.class);
                    ID.setText("");
                    employeepassword.setText("");
                    startActivity(intent);
                } else {
                    Toast.makeText(EmployeeLoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}