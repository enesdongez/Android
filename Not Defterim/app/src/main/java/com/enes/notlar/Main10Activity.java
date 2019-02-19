package com.enes.notlar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.enes.notlar.db.DbClient;
import com.enes.notlar.db.DbClientInterface;

import java.util.ArrayList;

public class Main10Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        final DbClientInterface dbClient = DbClient.createClient(this);

        final ListView list=(ListView) findViewById(R.id.listpublic);
        final ArrayList<String> veri=dbClient.publicnotlistele(1);
        ArrayAdapter<String> adapter = new  ArrayAdapter<>(Main10Activity.this, android.R.layout.simple_list_item_1,android.R.id.text1,veri);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemim=(String) parent.getItemAtPosition(position);
                String a= dbClient.notid(itemim);
                // Toast.makeText(getApplicationContext(),a,Toast.LENGTH_LONG).show();

                Intent intent=new Intent(Main10Activity.this,Main11Activity.class);
                intent.putExtra("gonder",a);
                intent.putExtra("id",getIntent().getExtras().getInt("id"));
                intent.putExtra("veri",getIntent().getExtras().getString("veri"));
                startActivity(intent);
            }
        });
        final Button don=(Button) findViewById(R.id.button12);

        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main10Activity.this,Main3Activity.class);
                intent.putExtra("id",getIntent().getExtras().getInt("id"));
                intent.putExtra("veri",getIntent().getExtras().getString("veri"));
                startActivity(intent);
            }
        });


    }
}
