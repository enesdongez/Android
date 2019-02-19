package com.enes.notlar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enes.notlar.db.DbClient;
import com.enes.notlar.db.DbClientInterface;

public class Main11Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        final TextView baslik=(TextView) findViewById(R.id.textView12);
        baslik.setMovementMethod(new ScrollingMovementMethod());
        final TextView not=(TextView) findViewById(R.id.textView11);
        not.setMovementMethod(new ScrollingMovementMethod());
        final TextView kullanici=(TextView) findViewById(R.id.textView14);
        final DbClientInterface dbClient = DbClient.createClient(this);
        final Button don=(Button) findViewById(R.id.button13);

        final String tut=getIntent().getExtras().getString("gonder");
        final String userid=dbClient.userid(tut);
        final String isim=dbClient.isimsoyisim(Integer.parseInt(userid));
        kullanici.setText(isim.toString());
        baslik.setText(dbClient.baslikyazdir(tut));
        not.setText(dbClient.notyazdir(tut));




        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main11Activity.this,Main10Activity.class);
                intent.putExtra("id",getIntent().getExtras().getInt("id"));
                intent.putExtra("veri",getIntent().getExtras().getString("veri"));
                startActivity(intent);
            }
        });
    }
}
