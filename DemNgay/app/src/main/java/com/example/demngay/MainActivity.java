package com.example.demngay;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText first,second;
    Button calculate;
    TextView dapso;
    Calendar calendarOne,calendarTwo;
    SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first = findViewById(R.id.ngaydau);
        second = findViewById(R.id.ngaysau);
        calculate = findViewById(R.id.tinh);
        dapso = findViewById(R.id.ketqua);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay1();
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay2();
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ngayXaNhau = (int) (calendarTwo.getTimeInMillis() - calendarOne.getTimeInMillis())/(1000*60*60*24);
                dapso.setText("Số ngày là: "+ngayXaNhau);
            }
        });
    }
    private void chonNgay1(){
        calendarOne = Calendar.getInstance();
        int ngay = calendarOne.get(Calendar.DATE);
        int thang = calendarOne.get(Calendar.MONTH);
        int nam = calendarOne.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarOne.set(year,month,dayOfMonth);
                first.setText(simpleDateFormat.format(calendarOne.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    private void chonNgay2(){
        calendarTwo = Calendar.getInstance();
        int ngay = calendarTwo.get(Calendar.DATE);
        int thang = calendarTwo.get(Calendar.MONTH);
        int nam = calendarTwo.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarTwo.set(year,month,dayOfMonth);
                second.setText(simpleDateFormat.format(calendarTwo.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
}
