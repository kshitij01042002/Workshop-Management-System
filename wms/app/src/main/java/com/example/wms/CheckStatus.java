package com.example.wms;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class CheckStatus extends AppCompatActivity {
    Button viewstatus;
    EditText jcnumber;
    TextView statusview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_status);
        getSupportActionBar().hide();
        viewstatus = findViewById(R.id.viewstatus);
        jcnumber = findViewById(R.id.jcnumber);
        statusview = findViewById(R.id.list);
        String jc = jcnumber.getText().toString();

        viewstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference docref = FirebaseFirestore.getInstance().collection("Cars").document(jcnumber.getText().toString());
                docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();

                            if (document != null){
                                statusview.setText("Current Status :" + document.getString("Current Status") +
                                            "\nCustomer Name: " + document.getString("Customer Name") +
                                            "\nDate: " + document.getString("Date") +
                                            "\nJobcard Number: " + document.getString("Jobcard") +
                                            "\nMake: " + document.getString("Make") +
                                            "\nModel: " + document.getString("Model") +
                                            "\nTechnician: " + document.getString("Technician") +
                                            "\nType of Repair: " + document.getString("Type of Repair") +
                                            "\nExpected Date of Completion: " + document.getString("Expected Date"));
                            }
                            else {
                                statusview.setText("No such Jobcard");
                            }
                        }
                        else {
                            statusview.setText("Failed with: " + task.getException());
                        }
                    }
                });
            }
        });
    }
}