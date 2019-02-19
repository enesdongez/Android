package com.enes.notlar;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;


public class bildirim extends IntentService {
    private NotificationManager alarmNotificationManager;

    public static final int NOTIFICATION_ID = 1;
    String msg;
    public bildirim() {
        super("bildirim");
    }
    public void onHandleIntent(Intent intent) {
        msg=intent.getStringExtra("msg");
        sendNotification(msg);
    }
    private void sendNotification(String msg) {
        alarmNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(msg);
        builder.setContentText("....alarm alarm alarm....");
        builder.setSound(soundUri);
        //builder.setTicker("Bildirim geliyor");
        builder.setAutoCancel((true));
        Intent intent = new Intent(this, bildirim.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 0);
        builder.setContentIntent(pendingIntent);

        alarmNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}
