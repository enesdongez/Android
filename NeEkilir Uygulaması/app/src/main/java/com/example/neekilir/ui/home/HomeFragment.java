package com.example.neekilir.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.neekilir.Anasayfa_Activity;
import com.example.neekilir.Icerik;
import com.example.neekilir.R;
import com.example.neekilir.adabter_list;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button aralik=root.findViewById(R.id.button2);
        aralik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Aralik");
                startActivity(intent);
            }
        });

        Button ocak=root.findViewById(R.id.button3);
        ocak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Ocak");
                startActivity(intent);
            }
        });
        Button subat=root.findViewById(R.id.button4);
        subat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Subat");
                startActivity(intent);
            }
        });
        Button mart=root.findViewById(R.id.button5);
        mart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Mart");
                startActivity(intent);
            }
        });
        Button nisan=root.findViewById(R.id.button6);
        nisan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Nisan");
                startActivity(intent);
            }
        });
        Button mayis=root.findViewById(R.id.button7);
        mayis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Mayis");
                startActivity(intent);
            }
        });
        Button haziran=root.findViewById(R.id.button8);
        haziran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Haziran");
                startActivity(intent);
            }
        });
        Button temmuz=root.findViewById(R.id.button9);
        temmuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Temmuz");
                startActivity(intent);
            }
        });
        Button agustos=root.findViewById(R.id.button10);
        agustos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Agustos");
                startActivity(intent);
            }
        });
        Button eylul=root.findViewById(R.id.button11);
        eylul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Eylul");
                startActivity(intent);
            }
        });
        Button ekim=root.findViewById(R.id.button12);
        ekim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Ekim");
                startActivity(intent);
            }
        });

        Button kasim=root.findViewById(R.id.button13);
        kasim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Icerik.class);
                intent.putExtra("ayadi","Kasim");
                startActivity(intent);
            }
        });

        /*final ListView list_aylar=root.findViewById(R.id.list_aylar);

        ArrayList<String> aylarListesi=new ArrayList<>();
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

        adabter_list adapter = new adabter_list((Activity) getContext(),aylarListesi);

        // ArrayAdapter<String> adapter = new  ArrayAdapter<>(Anasayfa.this, android.R.layout.simple_list_item_1,android.R.id.text1,aylarListesi);
        list_aylar.setAdapter(adapter);

        list_aylar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemim=(String) adapterView.getItemAtPosition(i);
                Intent intent=new Intent(getContext(), Icerik.class);
                intent.putExtra("ayadi",itemim);
                startActivity(intent);

            }
        });*/
        return root;
    }

}