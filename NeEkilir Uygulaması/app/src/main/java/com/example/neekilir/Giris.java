package com.example.neekilir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Giris extends AppCompatActivity {

    int girisKontrol=0;
    Button giris,kayit;
    EditText kadi;
    EditText ksifre;
    private DatabaseReference mDatabase;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

       /* mDatabase = FirebaseDatabase.getInstance().getReference();

        kadi = findViewById(R.id.kullaniciAdi);
        ksifre = findViewById(R.id.kullaniciSifre);
        giris = findViewById(R.id.kullaniciGiris);
        kayit=findViewById(R.id.kullaniciKayit);
        logo=findViewById(R.id.logo);

        logo.setImageResource(R.drawable.logom);



*/
        logo=findViewById(R.id.logo);

        logo.setImageResource(R.drawable.splash);
        Thread welcomeThread = new Thread() {
        @Override
        public void run() {
            try {
                super.run();
                sleep(1000);  //Delay of 10 seconds
            } catch (Exception e) {

            } finally {

                Intent i = new Intent(getApplicationContext(),
                        Anasayfa.class);

                startActivity(i);
                finish();
            }
        }
    };
        welcomeThread.start();

    }

   /* public void kayitOnclick(View v){
        if(kadi.getText().toString()!="" && ksifre.getText().toString()!="") {
            final DatabaseReference newChildRef = mDatabase.push();

            String key = newChildRef.getKey();

            User user = new User(kadi.getText().toString(), ksifre.getText().toString());
            mDatabase.child("Kullanicilar").child(key).setValue(user);

            Toast.makeText(getApplicationContext(), "Kayıt Edildi", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Kullanıcı adı ve şifreyi doldurunuz!", Toast.LENGTH_SHORT).show();
        }
    }

    public void girisOnclick(View v){

        if(!kadi.getText().toString().equals("") && !ksifre.getText().toString().equals("")) {
            kullanicikontol(kadi.getText().toString(), ksifre.getText().toString());
        }else{
            Toast.makeText(getApplicationContext(), "Kullanıcı adı ve şifreyi doldurunuz!", Toast.LENGTH_SHORT).show();
        }

    }

    public void kullanicikontol(String ad, String sifre){

            final String gelensifre=sifre;
            final String gelenad=ad;

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Kullanicilar");

            reference.orderByChild("kadi").equalTo(gelenad).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot datas : dataSnapshot.getChildren()) {

                        String sifrem = datas.child("ksifre").getValue().toString();

                        if (sifrem.equals(gelensifre)) {

                            kadi.setText("");
                            ksifre.setText("");
                            Intent intent = new Intent(Giris.this, Anasayfa.class);
                            intent.putExtra("kullaniciadi",gelenad);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(), "Hoşgeldin "+gelenad, Toast.LENGTH_LONG).show();
                            return;

                        }
                    }
                    Toast.makeText(getApplicationContext(), "Başarısız", Toast.LENGTH_LONG).show();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Hata", Toast.LENGTH_LONG).show();
                }
            });
    }*/


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
