package app.example.enesd_000.alarmuygulamam;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;

import static java.lang.Thread.*;

/**
 * Created by sonu on 10/04/17.
 */

public class AlarmSoundService extends Service {
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //Start media player
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound);
        mediaPlayer.start();

        mediaPlayer.setLooping(true);//set looping true to run it infinitely
      // mediaPlayer.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //On destory stop and release the media player
        if ( mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
    }
}