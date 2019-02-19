package com.enes.notlar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.enes.notlar.db.DbClient;
import com.enes.notlar.db.DbClientInterface;


import java.util.ArrayList;

public class Main8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        final DbClientInterface dbClient = DbClient.createClient(this);

        final ListView list=(ListView) findViewById(R.id.alarmlist);
        int id=getIntent().getExtras().getInt("id");
        final ArrayList<String> veri = dbClient.alarmlistele(id);

        ArrayAdapter<String> adapter = new  ArrayAdapter<>(Main8Activity.this, android.R.layout.simple_list_item_1,android.R.id.text1,veri);
        list.setAdapter(adapter);
        final Button anasyfa=(Button) findViewById(R.id.button7);

        anasyfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ikincisayfagecis=new Intent(Main8Activity.this, Main3Activity.class);
                ikincisayfagecis.putExtra("veri",getIntent().getExtras().getString("veri2"));
                ikincisayfagecis.putExtra("id",getIntent().getExtras().getInt("id"));
                startActivity(ikincisayfagecis);
            }
        });

        Button yenialarm=(Button) findViewById(R.id.button8);
        yenialarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ikincisayfagecis=new Intent(Main8Activity.this, Main7Activity.class);
                ikincisayfagecis.putExtra("veri",getIntent().getExtras().getString("veri2"));
                ikincisayfagecis.putExtra("id",getIntent().getExtras().getInt("id"));
                startActivity(ikincisayfagecis);
            }
        });
    }
}
