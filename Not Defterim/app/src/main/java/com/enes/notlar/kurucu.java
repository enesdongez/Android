package com.enes.notlar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class kurucu extends BroadcastReceiver {
    String msg="";
    @Override
    public void onReceive(Context context, Intent intent) {

        msg = intent.getStringExtra("msg");

        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        Intent a=new Intent(context,bildirim.class);
        a.putExtra("msg",msg);
        context.startService(a);

    }

}