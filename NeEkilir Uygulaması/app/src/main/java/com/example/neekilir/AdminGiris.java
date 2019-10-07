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

public class AdminGiris extends AppCompatActivity {
    EditText ad, sifre;
    Button giris,kayit;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_giris);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        ad = findViewById(R.id.adi);
        sifre = findViewById(R.id.sifresi);
        giris = findViewById(R.id.giris_admin);
        //kayit=findViewById(R.id.admin_kayit);

       /* kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference newChildRef = mDatabase.push();

                String key = newChildRef.getKey();

                User user = new User(ad.getText().toString(), sifre.getText().toString());
                mDatabase.child("Kullanicilar").child(key).setValue(user);

                Toast.makeText(getApplicationContext(), "Kayıt Edildi", Toast.LENGTH_SHORT).show();
            }
        });*/

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kullanicikontol(ad.getText().toString(),sifre.getText().toString());
            }
        });
    }


    public void kullanicikontol(String ad, String sifre) {

        final String gelensifre = sifre;
        final String gelenad = ad;

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Kullanicilar");

        reference.orderByChild("kadi").equalTo(gelenad).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot datas : dataSnapshot.getChildren()) {

                    String sifrem = datas.child("ksifre").getValue().toString();

                    if (sifrem.equals(gelensifre)) {


                        Intent intent = new Intent(AdminGiris.this, urunkayitistek.class);

                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "Hoşgeldin " + gelenad, Toast.LENGTH_LONG).show();
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


    }
    class User {
        String kadi;
        String ksifre;

        public User() {

        }

        public User(String kAdi, String kSifre) {
            this.kadi = kAdi;
            this.ksifre = kSifre;
        }

        public String getKadi() {
            return kadi;
        }

        public void setKadi(String kadi) {
            this.kadi = kadi;
        }

        public String getKsifre() {
            return ksifre;
        }

        public void setKsifre(String ksifre) {
            this.ksifre = ksifre;
        }
    }
}
