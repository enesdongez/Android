package com.example.neekilir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class urunkayitistek extends AppCompatActivity {

    Button kayit,sil;
    ListView list_istek;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataBaseRef;

    ArrayList<String> ay_ad;
    ArrayList<String> urun_ad;

    adabter_list3 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urunkayitistek);

        kayit=findViewById(R.id.kayit_istek);
        list_istek=findViewById(R.id.list_istek);
        sil=findViewById(R.id.kayit_sil);


        icerik(); //Listeleniyor..

        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                urun_sil();
            }
        });




        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               urun_kayit();

            }
        });

    }
    public void icerik(){

        mDatabase = FirebaseDatabase.getInstance();
        mDataBaseRef = mDatabase.getReference().child("NeEkilir_istek");

        mDataBaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ay_ad=new ArrayList<>();
                urun_ad=new ArrayList<>();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String ay_tut=ds.child("kay").getValue().toString();
                    String isim_tut=ds.child("kisim").getValue().toString();
                    ay_ad.add(ay_tut);
                    urun_ad.add(isim_tut);
                }

                if(ay_ad.size() <= 0) {
                    Toast.makeText(getApplicationContext(),"Eklenecek Veri Yok!",Toast.LENGTH_LONG).show();

                    onBackPressed();

                }
                else {

                    adapter = new adabter_list3(urunkayitistek.this, ay_ad, urun_ad);

                    list_istek.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }

    public void urun_kayit(){
        mDatabase = FirebaseDatabase.getInstance();
        mDataBaseRef = mDatabase.getReference();
        for(int i=0;i<ay_ad.size();i++) {
            final DatabaseReference newChildRef = mDataBaseRef.push();

            String key = newChildRef.getKey();

            Urun urun = new Urun(ay_ad.get(i), urun_ad.get(i));
            mDataBaseRef.child("NeEkilir").child(ay_ad.get(i)).child(key).setValue(urun_ad.get(i));
        }

        urun_sil();
        ay_ad=new ArrayList<>();
        urun_ad=new ArrayList<>();



        onBackPressed();

    }

    public void urun_sil(){
        mDatabase = FirebaseDatabase.getInstance();
        mDataBaseRef = mDatabase.getReference();

        mDataBaseRef.child("NeEkilir_istek").setValue("");


    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(urunkayitistek.this,Anasayfa_Activity.class);
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


    public void kayittut(){

        mDatabase = FirebaseDatabase.getInstance();
        mDataBaseRef = mDatabase.getReference();
        String[] ocak = new String[] {"Dereotu",
                "Roka",
                "Tere",
                "Patlıcan",
                "Maydonoz",
                "Çeri Domates"



        };
        String[] mart = new String[] {"Kabak",
                "Karnabahar",
                "Karpuz",
                "Kavun",
                "Kereviz",
                "Lahana",
                "Marul",
                "Maydonoz",
                "Mercimek",
                "Nohut",
                "Pancar",
                "Patates",
                "Patlıcan",
                "Pazı",
                "Pırasa",
                "Rezene",
                "Roka",
                "Salatalık",
                "Sarımsak",
                "Soğan",
                "Tere",
                "Yer Elması",
                "Yer Fıstığı",
                "Bezelye"
        };
        String[] nisan = new String[] {"Bamya",
                "Börülce",
                "Dereotu",
                "Fasülye",
                "Japon Turpu",
                "Kabak",
                "Karnabahar",
                "Karpuz",
                "Kavun",
                "Lahana",
                "Marul",
                "Maydonoz",
                "Mısır",
                "Patates",
                "Salatalık",
                "Yeşil Soğan",
                "Domates",
                "Bezelye"

        };
        String[] mayis = new String[] {"Ayçiçeği","Bamya",
                "Brokoli",
                "Brüksel Lahana",
                "Fasülye",
                "Kabak",
                "Kara Lahana",
                "Karadeniz Lahanası",
                "Karnabahar",
                "Kereviz",
                "Kırmızı Lahana",
                "Lahana",
                "Mısır",
                "Pancar",
                "Pazı",
                "Roka",
                "Salatalık"


        };
        String[] haziran = new String[] {"Salatalık",
                "Barbunya",
                "Börülce",
                "Fasülye",
                "Brokoli",
                "Dere Otu"



        };
        String[] temmuz = new String[] {"Fasülye",
                "Havuç",
                "Ispanak",
                "Kabak",
                "Marul",
                "Mısır",
                "Brokoli"




        };
        String[] agustos = new String[] {"Roka",
                "Havuç",
                "Ispanak",
                "Marul",
                "Brokoli"





        };
        String[] eylul = new String[] {"Dereotu",
                "Ispanak",
                "Kara Turp",
                "Kırmızı Turp",
                "Marul",
                "Roka",
                "Tere",
                "Biberiye",
                "Kekik",
                "Lavanta"





        };
        String[] ekim = new String[] {"Bakla",
                "Mercimek",
                "Roka",
                "Sarımsak",
                "Soğan",
                "Tere"






        };
        String[] kasim = new String[] { "Bakla",
                "Buğday",
                "Yulaf",
                "Çavdar",
                "Çilek"







        };
        String[] aralik = new String[] { "Bakla",
                "Buğday",
                "Yulaf",
                "Çavdar",
                "Çilek",
                "Soğan"







        };
        String[] subat = new String[] { "Roka",
                "Sarımsak",
                "Soğan",
                "Bezelye",
                "Patlıcan",
                "Çeri Domates"








        };
        for (int i = 0; i < ocak.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Ocak").child(key).setValue(mart[i]);
        }
        for (int i = 0; i < subat.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Subat").child(key).setValue(mart[i]);
        }
        for (int i = 0; i < mart.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Mart").child(key).setValue(mart[i]);
        }
        for (int i = 0; i < nisan.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Nisan").child(key).setValue(mart[i]);
        }
        for (int i = 0; i < mayis.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Mayis").child(key).setValue(mart[i]);
        }
        for (int i = 0; i < haziran.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Haziran").child(key).setValue(mart[i]);
        }
        for (int i = 0; i < temmuz.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Temmuz").child(key).setValue(mart[i]);
        }
        for (int i = 0; i < agustos.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Agustos").child(key).setValue(mart[i]);
        }
        for (int i = 0; i < eylul.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Eylul").child(key).setValue(mart[i]);
        }
        for (int i = 0; i < ekim.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Ekim").child(key).setValue(mart[i]);
        }
        for (int i = 0; i < kasim.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Kasim").child(key).setValue(mart[i]);
        }
        for (int i = 0; i < aralik.length; ++i) {
            final DatabaseReference newChildRef = mDataBaseRef.push();
            String key = newChildRef.getKey();
            mDataBaseRef.child("NeEkilir").child("Aralik").child(key).setValue(mart[i]);
        }
    }
}
