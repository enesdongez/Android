package app.example.enesd_000.alarmuygulamam;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    RadioButton saniye;
    RadioButton dakika;
    RadioButton saat;
    EditText veri;
    EditText gir;

    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gir=(EditText) findViewById(R.id.editText5);
        saniye=(RadioButton) findViewById(R.id.radioButton);
        dakika=(RadioButton) findViewById(R.id.radioButton2);
        saat=(RadioButton) findViewById(R.id.radioButton3);

        veri = (EditText) findViewById(R.id.editText);

        final Button kur=(Button) findViewById(R.id.button);
        kur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=gir.getText().toString();
            alarmkur(zaman(veri.getText().toString()),a.toString());
            veri.setText("");
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

    private void alarmkur(int a,String b){
        Intent intent = new Intent(this,kurucu.class );


        intent.putExtra("msg", b);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 234324243, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (a * 1000), pendingIntent);

        Toast.makeText(this, "Alarm Kuruldu!!", Toast.LENGTH_LONG).show();

    }





}
