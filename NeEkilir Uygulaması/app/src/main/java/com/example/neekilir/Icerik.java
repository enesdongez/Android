package com.example.neekilir;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Icerik extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataBaseRef;

    private ArrayList<String> aylist;
    ListView ayiceriklistesi;
    TextView yazi;
    ImageView resim;
    adabter_list2 adapter;
    Button Ekle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icerik);

        Intent intent = getIntent();

        aylist = new ArrayList<>();
        ayiceriklistesi=findViewById(R.id.icerik_goster);
        yazi=findViewById(R.id.textView);
        resim=findViewById(R.id.imageView);
        Ekle=findViewById(R.id.ekle);

        resim.setImageResource(R.drawable.icerikresim);

        final String ayadi=intent.getStringExtra("ayadi");

        yazi.setText(ayadi+" ayı ekilebilecekler.");

        ayicerik(ayadi);

        Ekle=findViewById(R.id.ekle);




        Ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Icerik.this,UrunKayit.class);
                intent.putExtra("ay",ayadi);
                startActivity(intent);
            }
        });



    }


    public void ayicerik(String ay){

        mDatabase = FirebaseDatabase.getInstance();
        mDataBaseRef = mDatabase.getReference().child("NeEkilir").child(ay);

        mDataBaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    aylist.add(ds.getValue()+"");
                }

                adapter = new adabter_list2(Icerik.this,aylist);

                ayiceriklistesi.setAdapter(adapter);
                aylist=new ArrayList<>();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }
}
