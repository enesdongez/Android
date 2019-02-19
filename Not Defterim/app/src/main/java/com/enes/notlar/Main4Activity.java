package com.enes.notlar;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.enes.notlar.db.DbClient;
import com.enes.notlar.db.DbClientInterface;


public class Main4Activity extends AppCompatActivity {

    FragmentManager fr=getSupportFragmentManager();
    final DbClientInterface dbClient = DbClient.createClient(this);
    int id;
    String basliktext="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Button notkayit=(Button) findViewById(R.id.button4);            //Not kayıt arayüz bağlantısı!!
        final EditText notbaslik=(EditText) findViewById(R.id.baslik);
        final EditText notyazi=(EditText) findViewById(R.id.not);
        final ListView liste=(ListView) findViewById(R.id.list);
        final CheckBox konrol=(CheckBox) findViewById(R.id.checkBox2) ;

        final CheckBox alrm=(CheckBox) findViewById(R.id.checkBox) ;

        id=getIntent().getExtras().getInt("id");


       notkayit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(alrm.isChecked()==true)
               {
                   if(dbClient.notbaslikkontrol(notbaslik.getText().toString())==1){
                       Toast.makeText(getApplicationContext(), "Başlığı değiştir!!", Toast.LENGTH_LONG).show();
                   }
                   else{
                       if(konrol.isChecked()==true)
                           dbClient.Notkaydetme(id,notbaslik.getText().toString(),notyazi.getText().toString(),1);
                       else
                           dbClient.Notkaydetme(id,notbaslik.getText().toString(),notyazi.getText().toString(),0);

                       Intent intent=new Intent(Main4Activity.this,Main7Activity.class);
                       intent.putExtra("veri",getIntent().getExtras().getString("veri2"));
                       intent.putExtra("id",id);
                       basliktext=notbaslik.getText().toString();
                       intent.putExtra("basliktext",basliktext.toString());
                       startActivity(intent);
                       Toast.makeText(getApplicationContext(), "Not kayıt edildi.", Toast.LENGTH_LONG).show();
                   }


               }
               else {
                   if(dbClient.notbaslikkontrol(notbaslik.getText().toString())==1){
                       Toast.makeText(getApplicationContext(), "Başlığı değiştir!!", Toast.LENGTH_LONG).show();
                   }
                   else{
                       if(konrol.isChecked()==true)
                           dbClient.Notkaydetme(id,notbaslik.getText().toString(),notyazi.getText().toString(),1);
                       else
                           dbClient.Notkaydetme(id,notbaslik.getText().toString(),notyazi.getText().toString(),0);

                       Intent intent=new Intent(Main4Activity.this,Main3Activity.class);
                       intent.putExtra("veri",getIntent().getExtras().getString("veri2"));
                       intent.putExtra("id",id);

                       startActivity(intent);
                       Toast.makeText(getApplicationContext(), "Not kayıt edildi.", Toast.LENGTH_LONG).show();
                   }
               }

           }
       });


    }



}
