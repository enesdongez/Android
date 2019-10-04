package com.example.neekilir;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Anasayfa extends AppCompatActivity {

    TextView kullaniciAdi;
    ListView Aylar;
    ArrayList<String> aylarListesi;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataBaseRef;
    Button ekle,cikis;

    adabter_list adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);


        Intent intent = getIntent();
        aylarListesi=new ArrayList<>();
        aylarListesi.add("Ocak");
        aylarListesi.add("Subat");
        aylarListesi.add("Mart");
        aylarListesi.add("Nisan");
        aylarListesi.add("Mayis");
        aylarListesi.add("Haziran");
        aylarListesi.add("Temmuz");
        aylarListesi.add("Agustos");
        aylarListesi.add("Eylul");
        aylarListesi.add("Ekim");
        aylarListesi.add("Kasim");
        aylarListesi.add("Aralik");





        Aylar=findViewById(R.id.aylar);

        adapter = new adabter_list(Anasayfa.this,aylarListesi);

       // ArrayAdapter<String> adapter = new  ArrayAdapter<>(Anasayfa.this, android.R.layout.simple_list_item_1,android.R.id.text1,aylarListesi);
        Aylar.setAdapter(adapter);

        Aylar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemim=(String) adapterView.getItemAtPosition(i);
                Intent intent=new Intent(Anasayfa.this,Icerik.class);
                intent.putExtra("ayadi",itemim);
                startActivity(intent);

            }
        });



    }

}
