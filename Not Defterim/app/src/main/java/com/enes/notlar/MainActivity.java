package com.enes.notlar;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.enes.notlar.db.DbClient;
import com.enes.notlar.db.DbClientInterface;
import com.enes.notlar.db.sqlite.Sqlite;

public class MainActivity extends AppCompatActivity {
    FragmentManager fr=getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ad=(EditText) findViewById(R.id.editText);
        final EditText sifre=(EditText) findViewById(R.id.editText2);

        final Button giris=(Button) findViewById(R.id.button);
        final DbClientInterface dbClient = DbClient.createClient(this);
        final Button kayit=(Button) findViewById(R.id.button2);

        //Sqlite sqlite = new Sqlite(MainActivity.this);
        //sqlite.copyDb();

        //dbClient.kullanicikayit("a","1","b", "c");

        //sqliteHelper.onUpgrade(sqliteHelper.getReadableDatabase(), 1, 1);
        //sqliteHelper.onCreate(sqliteHelper.getReadableDatabase());

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String adi=ad.getText().toString();
                String sifresi=sifre.getText().toString();

               // sqliteHelper.bul(adi);
               // sqliteHelper.kullanicikayit("deneme","123"); //Veritabanındaki kayıtlı kullanıcı oluşturup deneme yapıyoruz.

                Kullanici giris = dbClient.Kontrol(new Kullanici(adi,sifresi));

                if(giris!=null) {

                    Toast.makeText(getApplicationContext(), "Hoşgeldiniz", Toast.LENGTH_LONG).show(); //Giriş başarılı olursa yapılacaklar.

                    Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                    intent.putExtra("veri",adi.toString());
                    intent.putExtra("id",giris.id);



                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Giriş Başarısız!!!",Toast.LENGTH_LONG).show(); //Giriş başarısız olursa yapılacaklar.

            }
        });

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });


    }
}
