package com.example.neekilir.ui.send;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.neekilir.Icerik;
import com.example.neekilir.R;
import com.example.neekilir.UrunKayit;
import com.example.neekilir.adabter_list2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataBaseRef;

    private ArrayList<String> aylist;
    ListView ayiceriklistesi;
    TextView yazi;
    ImageView resim;
    adabter_list2 adapter;
    Button Ekle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.activity_icerik, container, false);

        Intent intent = null;

        aylist = new ArrayList<>();
        ayiceriklistesi=root.findViewById(R.id.icerik_goster);
        yazi=root.findViewById(R.id.textView);
        resim=root.findViewById(R.id.imageView);
        Ekle=root.findViewById(R.id.ekle);

        resim.setImageResource(R.drawable.icerikresim);

        final String ayadi=intent.getStringExtra("ayadi")+"";

        yazi.setText(ayadi+" ayı ekilebilecekler.");

        ayicerik(ayadi);

        Ekle=root.findViewById(R.id.ekle);




        Ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), UrunKayit.class);
                intent.putExtra("ay",ayadi);
                startActivity(intent);
            }
        });


        return root;
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

                adapter = new adabter_list2((Activity) getContext(), aylist);

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