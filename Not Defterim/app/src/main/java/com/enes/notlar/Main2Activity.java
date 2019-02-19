package com.enes.notlar;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.enes.notlar.db.DbClient;
import com.enes.notlar.db.DbClientInterface;


public class Main2Activity extends AppCompatActivity {
 final int PICK_IMAGE_REQUEST=1;
    String i="";
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final EditText isim=(EditText) findViewById(R.id.editText9);
        final EditText ad=(EditText) findViewById(R.id.editText3);
        final EditText sifre=(EditText) findViewById(R.id.editText4);
        final EditText soyad=(EditText) findViewById(R.id.kullanicisoyad);
        final Button kayit=(Button) findViewById(R.id.button3);
        final DbClientInterface dbClient = DbClient.createClient(this);
      //  final CheckBox chc=(CheckBox) findViewById(R.id.checkBox2);

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adi=ad.getText().toString();
                String sifresi=sifre.getText().toString();
                String soyadi=soyad.getText().toString();
                String isimim=isim.getText().toString();
                if(adi.equals("") && sifresi.equals("")){
                    Toast.makeText(getApplicationContext(),"Hata!!",Toast.LENGTH_LONG).show();
                }
                else {
                    int kontrol = dbClient.kayitkontrol(adi);
                    if (kontrol == -1) {
                        Toast.makeText(getApplicationContext(),"Hata oldu, tekrar deneyin!",Toast.LENGTH_LONG).show();
                    } else if (kontrol == 1) {
                        Toast.makeText(getApplicationContext(), "Bu Kayıt Mevcuttur!", Toast.LENGTH_LONG).show();
                    } else {
                        dbClient.kullanicikayit(adi, sifresi,soyadi,isimim);
                        Toast.makeText(getApplicationContext(),"Veritabanına Kayıt Edildi",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });

           /* final Button resimsec = (Button) findViewById(R.id.button12);

            resimsec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent();
                    intent2.setType("image/*");
                    intent2.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent2, "Select Picture"), PICK_IMAGE_REQUEST);
                }
            });



    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();
            i=uri.toString();
            Toast.makeText(getApplicationContext(),i,Toast.LENGTH_LONG).show();
        }*/
    }
}
