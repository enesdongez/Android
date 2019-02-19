package com.enes.notlar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.enes.notlar.db.DbClient;
import com.enes.notlar.db.DbClientInterface;



public class Main6Activity extends AppCompatActivity {
    String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        final EditText baslik=(EditText) findViewById(R.id.editText5);
        final EditText not=(EditText) findViewById(R.id.editText6);
        final DbClientInterface dbClient = DbClient.createClient(this);
        final Button kaydet=(Button) findViewById(R.id.button6);


        id=getIntent().getExtras().getString("gonder");
        baslik.setText(dbClient.baslikyazdir(id));
        not.setText(dbClient.notyazdir(id));

        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbClient.baslikguncelle(id, baslik.getText().toString());
                dbClient.notguncelle(id, not.getText().toString());

                    Intent intent=new Intent(Main6Activity.this,Main5Activity.class);
                    intent.putExtra("gonder",getIntent().getExtras().getString("gonder"));
                    intent.putExtra("id",getIntent().getExtras().getInt("id"));
                    intent.putExtra("veri",getIntent().getExtras().getString("veri"));

                    startActivity(intent);

            }
        });


    }
}
