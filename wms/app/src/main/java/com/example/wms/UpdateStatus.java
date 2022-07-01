package com.example.wms;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateStatus extends AppCompatActivity {

    EditText jobcardn;
    Spinner ustatus;
    TextView status;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);
        getSupportActionBar().hide();
        jobcardn = findViewById(R.id.jobcardnumber);
        ustatus = findViewById(R.id.currentstatus);
        status = findViewById(R.id.list);
        update = findViewById(R.id.update);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(UpdateStatus.this,R.array.statusArray,R.layout.support_simple_spinner_dropdown_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ustatus.setAdapter(statusAdapter);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jobcardn.getText().toString().isEmpty()){
                    Toast.makeText(UpdateStatus.this, "Enter Details", Toast.LENGTH_SHORT).show();
                }
                final DocumentReference docref = FirebaseFirestore.getInstance()
                                                .collection("Cars")
                                                .document(jobcardn.getText().toString());
                Map<String,Object> map = new HashMap<>();
                map.put("Current Status",ustatus.getSelectedItem().toString());

                docref.update(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()){
                                            DocumentSnapshot document = task.getResult();
                                            if (document != null){
                                                status.setText("Current Status: " + document.getString("Current Status") +
                                                        "\nCustomer Name: " + document.getString("Customer Name") +
                                                        "\nDate: " + document.getString("Date") +
                                                        "\nJobcard Number: " + document.getString("Jobcard") +
                                                        "\nMake: " + document.getString("Make") +
                                                        "\nModel: " + document.getString("Model") +
                                                        "\nTechnician: " + document.getString("Technician") +
                                                        "\nType of Repair: " + document.getString("Type of Repair")+
                                                        "\nExpected Date of Completion: " + document.getString("Expected Date"));
                                            }
                                            else {
                                                status.setText("No such document");
                                            }
                                        }
                                        else {
                                            status.setText("Failed with: " + task.getException());
                                        }
                                    }
                                });
                            }
                        })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            status.setText("No such Job Card");
                        }
                    });
            }
        });
    }
}