package com.example.cuocduakithu;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView diem;
    CheckBox cho,meo,chuot;
    SeekBar chay,nhay,nga;
    ImageButton batdau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cho = findViewById(R.id.mot);
        meo = findViewById(R.id.hai);
        chuot = findViewById(R.id.ba);
        chay = findViewById(R.id.seek1);
        nhay = findViewById(R.id.seek2);
        nga = findViewById(R.id.seek3);
        batdau = findViewById(R.id.nut);
        final CountDownTimer countDownTimer = new CountDownTimer(60000,300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int num = 10;
                Random random = new Random();
                int one = random.nextInt(num);
                int two = random.nextInt(num);
                int three = random.nextInt(num);
                if(chay.getProgress() >= chay.getMax()){
                    this.cancel();
                    batdau.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Một win",Toast.LENGTH_LONG).show();
                }else if(nhay.getProgress() >= nhay.getMax()){
                    this.cancel();
                    batdau.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Hai win",Toast.LENGTH_LONG).show();
                }else if(nga.getProgress() >= nga.getMax()){
                    this.cancel();
                    batdau.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Ba win",Toast.LENGTH_LONG).show();
                }
                chay.setProgress(chay.getProgress()+one);
                nhay.setProgress(nhay.getProgress()+two);
                nga.setProgress(nga.getProgress()+three);
            }

            @Override
            public void onFinish() { }
        };
        batdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cho.isChecked() || meo.isChecked() || chuot.isChecked()) {
                    chay.setProgress(0);
                    nhay.setProgress(0);
                    nga.setProgress(0);
                    batdau.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                }
                else {
                    Toast.makeText(MainActivity.this,"Vui lòng đặt cược",Toast.LENGTH_LONG).show();
                }
            }
        });
        cho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                   meo.setChecked(false);
                   chuot.setChecked(false);
                }
            }
        });
        meo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cho.setChecked(false);
                    chuot.setChecked(false);
                }
            }
        });
        chuot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cho.setChecked(false);
                    meo.setChecked(false);
                }
            }
        });
    }
}
