package com.example.wms;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewEmployees extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employees);
        getSupportActionBar().hide();
        list = findViewById(R.id.list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<String> a = new ArrayList<>();
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(ViewEmployees.this, R.layout.items, a);
        list.setAdapter(arrayAdapter);
        FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    a.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        Users i = snapshot1.getValue(Users.class);
                        String t = "Name :" + i.getName()
                                + "\nJob Profile: " + i.getJobprofile()
                                + "\nEmail: " + i.getEmail()
                                + "\nPhone Number: " + i.getPhone();
                        a.add(t);

                    }
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
