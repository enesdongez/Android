package com.example.enesd_000.yenisayfa2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=(Button) findViewById(R.id.button);
        final EditText ad=(EditText) findViewById(R.id.editText);
        final EditText soyad=(EditText) findViewById(R.id.editText2);
        final TextView txt=(TextView) findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ad.getText().toString().equals("A") && soyad.getText().toString().equals("B")){
                    Intent ikincisayfagecis=new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(ikincisayfagecis);
                }
                else{
                    txt.setText("Giriş Başarısız");
                }

            }
        });
    }

}
