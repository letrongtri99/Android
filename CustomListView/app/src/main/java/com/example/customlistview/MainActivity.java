package com.example.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView a;
    List<item> list = new ArrayList<>();
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = findViewById(R.id.id1);
        list.add(new item(R.drawable.neu,"Clash of Clan đang trực tiếp một game ","4 giờ trước"));
        list.add(new item(R.drawable.clan,"NEU conffesion vừa cập nhật một trạng thái của họ","1 giờ trước"));
        list.add(new item(R.drawable.chuot,"Minh Nguyễn đã đăng trong Bk đại cương môn phái","2 giờ trước"));
        list.add(new item(R.drawable.tri,"Huy Phu đã bày tỏ cảm xúc về bình luận của bạn","15 phút trước"));
        adapter = new Adapter(list,getApplicationContext());
        a.setAdapter(adapter);
    }
}
