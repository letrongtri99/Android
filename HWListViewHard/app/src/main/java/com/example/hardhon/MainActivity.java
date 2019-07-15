package com.example.hardhon;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 10;
    ListView aVenger;
    ArrayList<String> baoThu = new ArrayList<>();
    ArrayAdapter<String> phucHan;
    Random random = new Random();
    int pos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pos = 0;
        aVenger = findViewById(R.id.tri);
        baoThu.add("Thanos");
        baoThu.add("Thor");
        baoThu.add("Iron Man");
        baoThu.add("Steve");
        baoThu.add("Black Window");
        baoThu.add("Ronin");
        baoThu.add("Doctor Strange");
        baoThu.add("Spider Man");
        phucHan = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, baoThu);
        aVenger.setAdapter(phucHan);
        aVenger.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String a = baoThu.get(position).toString();
                int d = baoThu.size();
                if (a.equals("Thanos")) {
                    for (int i = 0; i < d / 2; i++) {
                        int b = baoThu.size();
                        int c = random.nextInt(b);
                        baoThu.remove(c);
                        phucHan.notifyDataSetChanged();
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("anhhung", baoThu.get(position));
                    intent.putExtra("chiso", position);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            int hang = data.getIntExtra("loc",-1);
            baoThu.remove(hang);
            phucHan.notifyDataSetChanged();
        }
        else if(requestCode == REQUEST_CODE && resultCode == RESULT_FIRST_USER){
            String chi = data.getStringExtra("gi");
            int chu = data.getIntExtra("go",-1);
            baoThu.set(chu,chi);
            phucHan.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        pos =1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        pos =2;
    }

    @Override
    protected void onPause() {
        super.onPause();
        pos =3;
    }

    @Override
    protected void onStop() {
        super.onStop();
        pos =4;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pos =5;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pos = 6;
    }
}
