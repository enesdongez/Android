package com.example.neekilir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icerik);

        Intent intent = getIntent();
        aylist = new ArrayList<>();
        ayiceriklistesi=findViewById(R.id.icerik_goster);
        yazi=findViewById(R.id.textView);

        String ayadi=intent.getStringExtra("ayadi");

        yazi.setText(ayadi+" ayı ekilebilecekler.");

        ayicerik(ayadi);






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
                ArrayAdapter<String> adapter = new  ArrayAdapter<>(Icerik.this, android.R.layout.simple_list_item_1,android.R.id.text1,aylist);

                ayiceriklistesi.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }
}
