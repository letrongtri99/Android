package com.example.hardhon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    private static final int REQUEST_CODE = 30 ;
    EditText trang;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        trang = findViewById(R.id.thay);
        back = findViewById(R.id.sua);
        Intent y = getIntent();
        final String l = y.getStringExtra("ten");
        final int b = y.getIntExtra("so",-1);
        trang.setText(l);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!trang.getText().toString().equals("")){
                    Intent u = new Intent(Main3Activity.this,Main2Activity.class);
                    u.putExtra("danh",trang.getText().toString());
                    u.putExtra("num",b);
                    setResult(RESULT_CANCELED,u);
                    finish();
                }
            }
        });
    }
}
