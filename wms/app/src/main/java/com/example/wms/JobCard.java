package com.example.wms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JobCard extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText jc;
    TextView date,date1;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private DatePickerDialog datePickerDialog2;
    private Button dateButton2;
    EditText customer;
    EditText make;
    EditText model;
    EditText repair;
    EditText technician;
    Spinner status;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_card);
        getSupportActionBar().hide();
        jc = findViewById(R.id.jc);
        initDatePicker();
        initDatePicker2();
        dateButton = findViewById(R.id.datePickerButton);;
        dateButton2 = findViewById(R.id.datePickerButton2);
        customer = findViewById(R.id.customer);
        make = findViewById(R.id.make);
        model = findViewById(R.id.model);
        repair = findViewById(R.id.repair);
        technician = findViewById(R.id.technician);
        status = findViewById(R.id.status);
        button = findViewById(R.id.button);
        date = findViewById(R.id.date);
        date1 = findViewById(R.id.date1);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(JobCard.this,R.array.statusArray,R.layout.support_simple_spinner_dropdown_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jc.getText().toString().isEmpty() ||
                    customer.getText().toString().isEmpty() ||
                    make.getText().toString().isEmpty() ||
                    model.getText().toString().isEmpty() ||
                    repair.getText().toString().isEmpty() ||
                    technician.getText().toString().isEmpty())
                    {
                    Toast.makeText(JobCard.this, "Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerjobcard();
                }
            }
        });
    }

    private void registerjobcard(){
        Map<String, String> v = new HashMap<>();
        v.put("Jobcard",jc.getText().toString());
        v.put("Date",date.getText().toString());
        v.put("Customer Name",customer.getText().toString());
        v.put("Make",make.getText().toString());
        v.put("Model",model.getText().toString());
        v.put("Type of Repair",repair.getText().toString());
        v.put("Technician",technician.getText().toString());
        v.put("Current Status",status.getSelectedItem().toString());
        v.put("Expected Date",date1.getText().toString());
        db.collection("Cars").document(jc.getText().toString()).set(v).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    jc.setText("");
                    customer.setText("");
                    make.setText("");
                    model.setText("");
                    repair.setText("");
                    technician.setText("");
                    status.setSelection(0);
                    dateButton.setText("Click to set Date of Job Card Opening");
                    dateButton2.setText("Click to set Expected date of completion");
                    date.setText("");
                    date1.setText("");
                    Toast.makeText(JobCard.this, "Job Card Created Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(JobCard.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date1 = makeDateString(day, month, year);
                dateButton.setText(date1);
                getdate1(date1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.BUTTON_POSITIVE;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private void initDatePicker2(){
        DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date2 = makeDateString(day,month,year);
                dateButton2.setText(date2);
                getdate2(date2);
            }


        };
        Calendar cal1 = Calendar.getInstance();
        int year1 = cal1.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.BUTTON_POSITIVE;
        datePickerDialog2 = new DatePickerDialog(this,style,dateSetListener1,year1,month1,day1);
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
    public void openDatePicker2(View view){ datePickerDialog2.show(); }

    private void getdate1(String d){
        date.setText(d);
    }

    private void getdate2(String d){
        date1.setText(d);
    }
}