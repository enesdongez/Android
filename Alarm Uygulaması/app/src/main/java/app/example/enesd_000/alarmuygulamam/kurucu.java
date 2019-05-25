package app.example.enesd_000.alarmuygulamam;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class kurucu extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("msg");
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

        //context.startService(new Intent(context, AlarmSoundService.class));


        Intent a=new Intent(context,bildirim.class);
        a.putExtra("msg",msg);
        context.startService(a);


    }

}