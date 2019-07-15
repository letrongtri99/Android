package com.example.hardhon;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private static final int REQUEST_CODE = 15;
    TextView display;
    Button mot,hai,ba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        display = findViewById(R.id.id4);
        mot = findViewById(R.id.id1);
        hai= findViewById(R.id.id2);
        ba = findViewById(R.id.id3);
        Intent t = getIntent();
        String a = t.getStringExtra("anhhung");
        final int b = t.getIntExtra("chiso",-1);
        display.setText(a);
        hai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent h = new Intent();
                h.putExtra("loc",b);
                setResult(RESULT_OK,h);
                finish();
            }
        });
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Main2Activity.this,Main3Activity.class);
                n.putExtra("ten",display.getText().toString());
                n.putExtra("so",b);
                startActivityForResult(n,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_CANCELED){
            Intent y = new Intent();
            String ca = data.getStringExtra("danh");
            int cec = data.getIntExtra("num",-1);
            y.putExtra("gi",ca);
            y.putExtra("go",cec);
            setResult(RESULT_FIRST_USER,y);
            finish();
        }
    }
}
