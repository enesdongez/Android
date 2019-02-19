package com.enes.notlar;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.enes.notlar.db.DbClient;
import com.enes.notlar.db.DbClientInterface;

import java.util.ArrayList;

public class Main9Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        final DbClientInterface dbClient = DbClient.createClient(this);
        int id=getIntent().getExtras().getInt("id");
        final ListView list=(ListView) findViewById(R.id.whatsapplist);
        final ArrayList<String> veri = dbClient.NotListeleme(id);

        ArrayAdapter<String> adapter = new  ArrayAdapter<>(Main9Activity.this, android.R.layout.simple_list_item_1,android.R.id.text1,veri);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemim=(String) parent.getItemAtPosition(position);
                String veri="";
                String idim=dbClient.notid(itemim);
                PackageManager pm=getPackageManager();
                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    veri=dbClient.notgetir(idim);
                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);

                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, veri.toString());
                    startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(getApplicationContext(),"Whatsapp Yüklü Değil!",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
