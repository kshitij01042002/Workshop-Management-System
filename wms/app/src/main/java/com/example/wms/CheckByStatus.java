package com.example.wms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CheckByStatus extends AppCompatActivity {

    Spinner status;
    Button submit;
    ListView sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_by_status);

        getSupportActionBar().hide();
        sort = findViewById(R.id.sort);
        status = findViewById(R.id.status);
        submit = findViewById(R.id.submit);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(CheckByStatus.this,R.array.statusArray,R.layout.support_simple_spinner_dropdown_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> a = new ArrayList<>();
                ArrayAdapter arrayAdapter = new ArrayAdapter(CheckByStatus.this,R.layout.items,a);
                sort.setAdapter(arrayAdapter);

                FirebaseFirestore.getInstance().collection("Cars").whereEqualTo("Current Status",status.getSelectedItem().toString())
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (value.isEmpty()){
                                    Toast.makeText(CheckByStatus.this, "No Cars at " + status.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                                }
                                else {
                                a.clear();
                                    for (QueryDocumentSnapshot document: value){
                                        String t = ("Current Status: " + document.getString("Current Status") +
                                                "\nCustomer Name: " + document.getString("Customer Name") +
                                                "\nDate: " + document.getString("Date") +
                                                "\nJobcard Number: " + document.getString("Jobcard") +
                                                "\nMake: " + document.getString("Make") +
                                                "\nModel: " + document.getString("Model") +
                                                "\nTechnician: " + document.getString("Technician") +
                                                "\nType of Repair: " + document.getString("Type of Repair")+
                                                "\nExpected Date of Completion: " + document.getString("Expected Date"));
                                        a.add(t);
                                    }
                                arrayAdapter.notifyDataSetChanged();
                                }
                            }
                        });
            }
        });
    }
}