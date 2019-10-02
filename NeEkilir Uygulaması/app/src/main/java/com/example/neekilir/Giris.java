package com.example.neekilir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Giris extends AppCompatActivity {

    Boolean girisKontrol;
    Button giris,kayit;
    EditText kadi;
    EditText ksifre;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        girisKontrol=false;
        kadi = findViewById(R.id.kullaniciAdi);
        ksifre = findViewById(R.id.kullaniciSifre);
        giris = findViewById(R.id.kullaniciGiris);
        kayit=findViewById(R.id.kullaniciKayit);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kullanicikontol(kadi.getText().toString(),ksifre.getText().toString());


            }
        });

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference newChildRef = mDatabase.push();
                String key = newChildRef.getKey();
                User user = new User(kadi.getText().toString(),ksifre.getText().toString());
                mDatabase.child("Kullanicilar").child(key).setValue(user);
                Toast.makeText(getApplicationContext(),"Başarılı",Toast.LENGTH_LONG).show();

            }
        });

    }

    public void kullanicikontol(final String ad,final String sifre){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Kullanicilar");

        reference.orderByChild("kadi").equalTo(ad).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String sifrem=datas.child("ksifre").getValue().toString();
                    if(sifrem.equals(sifre)) {
                        girisKontrol=true;

                        Intent intent=new Intent(Giris.this,Anasayfa.class);
                        intent.putExtra("kullaniciadi",kadi.getText().toString());
                        startActivity(intent);


                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Hata",Toast.LENGTH_LONG).show();
            }
        });

    }

    class User{
        String kadi;
        String ksifre;
        public User(){

        }
        public User(String kAdi,String kSifre){
            this.kadi=kAdi;
            this.ksifre=kSifre;
        }
        public String getKadi(){
            return kadi;
        }
        public void setKadi(String kadi){
            this.kadi=kadi;
        }
        public String getKsifre(){
            return ksifre;
        }
        public void setKsifre(String ksifre){
            this.ksifre=ksifre;
        }
    }













}
