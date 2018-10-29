package com.example.enesd_000.sqllitekayit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ad=(EditText) findViewById(R.id.editText);
        final EditText sifre=(EditText) findViewById(R.id.editText2);
        final Button giris=(Button) findViewById(R.id.button);
        final Sqlite sqliteHelper= new Sqlite(this);


        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adi=ad.getText().toString();
                String sifresi=sifre.getText().toString();

                sqliteHelper.kullanicikayit("deneme","123"); //Veritabanındaki kayıtlı kullanıcı oluşturup deneme yapıyoruz.

                Kullanici giris=sqliteHelper.Kontrol(new Kullanici(adi,sifresi));

                if(giris!=null)
                    Toast.makeText(getApplicationContext(),"Giriş Başarılı",Toast.LENGTH_LONG).show(); //Giriş başarılı olursa yapılacaklar.
                else
                    Toast.makeText(getApplicationContext(),"Giriş Başarısız!!!",Toast.LENGTH_LONG).show(); //Giriş başarısız olursa yapılacaklar.

            }
        });
    }
}
