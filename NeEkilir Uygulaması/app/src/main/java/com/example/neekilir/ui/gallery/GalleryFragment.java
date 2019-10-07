package com.example.neekilir.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.neekilir.AdminGiris;
import com.example.neekilir.R;
import com.example.neekilir.urunkayitistek;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.activity_admin_giris, container, false);
        final EditText ad=root.findViewById(R.id.adi);
        final EditText sifre=root.findViewById(R.id.sifresi);
        Button giris=root.findViewById(R.id.giris_admin);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kullanicikontol(ad.getText().toString(),sifre.getText().toString());
            }
        });

        return root;
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


                        Intent intent = new Intent(getContext(), urunkayitistek.class);

                        startActivity(intent);

                        Toast.makeText(getContext(), "Hoşgeldin " + gelenad, Toast.LENGTH_LONG).show();
                        return;

                    }
                }
                Toast.makeText(getContext(), "Başarısız", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Hata", Toast.LENGTH_LONG).show();
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