package com.enes.notlar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.enes.notlar.db.DbClient;
import com.enes.notlar.db.DbClientInterface;



public class Main7Activity extends AppCompatActivity {
    RadioButton saniye;
    RadioButton dakika;
    RadioButton saat;
    EditText veri;
    String veriadi="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        final DbClientInterface dbClient = DbClient.createClient(this);

        saniye=(RadioButton) findViewById(R.id.radioButton);
        dakika=(RadioButton) findViewById(R.id.radioButton2);
        saat=(RadioButton) findViewById(R.id.radioButton3);
        final EditText notum=(EditText) findViewById(R.id.editText8);
        veriadi=getIntent().getExtras().getString("basliktext");
        notum.setText(veriadi);
        veri = (EditText) findViewById(R.id.editText7);

        final Button kur=(Button) findViewById(R.id.button11);

        kur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veriadi=notum.getText().toString();
                alarmkur(zaman(veri.getText().toString()),veriadi);
                dbClient.alarmkayit(getIntent().getExtras().getInt("id"),veriadi);
                notum.setText("");
                veri.setText("");
                Intent intent2=new Intent(Main7Activity.this,Main3Activity.class);
                intent2.putExtra("veri",getIntent().getExtras().getString("veri"));
                intent2.putExtra("id",getIntent().getExtras().getInt("id"));
                startActivity(intent2);
            }
        });
    }
    private int zaman(String a) {
        int sure = Integer.parseInt(a);

        if (saniye.isChecked()==true)
            return sure;
        if (dakika.isChecked()==true)
            return sure * 60;
        if (saat.isChecked()==true)
            return sure * 60 * 60;

        return 0;
    }

    private void alarmkur(int a,String msg){
        Intent intent = new Intent(this,kurucu.class );
        intent.putExtra("msg", msg);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(Main7Activity.this, 234324243, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (a * 1000), pendingIntent);

        Toast.makeText(this, "Alarm Kuruldu!!", Toast.LENGTH_LONG).show();

        //alarmManager.cancel();


    }
}
