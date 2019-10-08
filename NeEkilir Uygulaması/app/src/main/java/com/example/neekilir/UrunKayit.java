package com.example.neekilir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UrunKayit extends AppCompatActivity {
    private EditText urunad;
    private TextView urunay;
    private Button buttonLogin,et;
    private DatabaseReference mDatabase;
    private ImageView gorsel1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        Intent intent = getIntent();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        urunad = findViewById(R.id.urunAd);
        urunay = findViewById(R.id.urunAy);
        buttonLogin = findViewById(R.id.kayit);
        gorsel1=findViewById(R.id.gorsel1);

        gorsel1.setImageResource(R.drawable.logom);

        final String ayadi=intent.getStringExtra("ay");
        urunay.setText(ayadi.toString()+" Ayı urun girişi");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final DatabaseReference newChildRef = mDatabase.push();
                String key = newChildRef.getKey();
                mDatabase.child("NeEkilir_istek").child(urunay.getText().toString()).child(key).setValue(urunad.getText().toString());
                urunad.setText("");*/

                final DatabaseReference newChildRef = mDatabase.push();

                String key = newChildRef.getKey();

                Urun urun = new Urun(ayadi, urunad.getText().toString());
                mDatabase.child("NeEkilir_istek").child(key).setValue(urun);
                Toast.makeText(getApplicationContext(), "İsteğiniz alınmıştır en kısa sürede eklenecektir.", Toast.LENGTH_LONG).show();
                onBackPressed();


            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(UrunKayit.this,Anasayfa_Activity.class);
        startActivity(intent);
        finish();
    }


    class Urun{
        String ay;
        String isim;
        public Urun(){

        }
        public Urun(String kAy,String kIsim){
            this.ay=kAy;
            this.isim=kIsim;
        }
        public String getKAy(){
            return ay;
        }
        public void setKAy(String ay){
            this.ay=ay;
        }
        public String getKIsim(){
            return isim;
        }
        public void setKIsim(String isim){
            this.isim=isim;
        }
    }


}
