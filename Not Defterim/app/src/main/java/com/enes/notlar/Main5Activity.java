package com.enes.notlar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.enes.notlar.db.DbClient;
import com.enes.notlar.db.DbClientInterface;



public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        final DbClientInterface dbClient = DbClient.createClient(this);
        final Button duzenle=(Button) findViewById(R.id.button9);
        final Button anasayfa=(Button) findViewById(R.id.button10);

        final String tut=getIntent().getExtras().getString("gonder");

        TextView txtbaslik=(TextView) findViewById(R.id.textView);
        txtbaslik.setMovementMethod(new ScrollingMovementMethod());
        TextView txtnot=(TextView) findViewById(R.id.textView5);
        txtnot.setMovementMethod(new ScrollingMovementMethod());

        txtbaslik.setText(dbClient.baslikyazdir(tut));
        txtnot.setText(dbClient.notyazdir(tut));

        duzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main5Activity.this,Main6Activity.class);
                intent.putExtra("gonder",getIntent().getExtras().getString("gonder"));
                intent.putExtra("id",getIntent().getExtras().getInt("id"));
                intent.putExtra("veri",getIntent().getExtras().getString("veri"));
                startActivity(intent);
            }
        });

        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main5Activity.this,Main3Activity.class);
                intent.putExtra("veri",getIntent().getExtras().getString("veri"));
                intent.putExtra("id",getIntent().getExtras().getInt("id"));
                startActivity(intent);
            }
        });
    }
}
