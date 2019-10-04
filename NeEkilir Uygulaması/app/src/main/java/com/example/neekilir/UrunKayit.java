package com.example.neekilir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UrunKayit extends AppCompatActivity {
    private EditText urunad;
    private TextView urunay;
    private Button buttonLogin,et;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        Intent intent = getIntent();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        urunad = findViewById(R.id.urunAd);
        urunay = findViewById(R.id.urunAy);
        buttonLogin = findViewById(R.id.kayit);
        et=findViewById(R.id.et);

        urunay.setText(intent.getStringExtra("ay"));

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference newChildRef = mDatabase.push();
                String key = newChildRef.getKey();
                mDatabase.child("NeEkilir").child(urunay.getText().toString()).child(key).setValue(urunad.getText().toString());
                urunad.setText("");

                onBackPressed();


            }
        });


    }


}
