package com.example.chickenattack.zhiyin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class KindManager extends AppCompatActivity {

    private ListView listView_kind;

    private List<String> kind;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_manager);

        listView_kind=findViewById(R.id.listView_kind);

        intent=new Intent(KindManager.this,MainActivity.class);

        kind=new ArrayList<>();

        kind.add("生活");
        kind.add("体育");
        kind.add("经济");
        kind.add("娱乐");
        kind.add("教育");
        kind.add("美食");
        kind.add("历史");
        kind.add("健康");
        kind.add("人文");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,kind);
        listView_kind.setAdapter(adapter);


        listView_kind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int ii= new Long(l).intValue();
                MainActivity.Kind=ii+1;
                startActivity(intent);
            }
        });

    }
}
