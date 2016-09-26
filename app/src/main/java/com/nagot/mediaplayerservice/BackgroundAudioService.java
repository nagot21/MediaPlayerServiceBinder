package com.nagot.mediaplayerservice;

/**
 * Created by Nagot on 25/09/2016.
 */
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.text.method.Touch;
import android.widget.Toast;

public class BackgroundAudioService extends Service {
    MediaPlayer mediaPlayer;
    String text;
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        BackgroundAudioService getService() {
            return BackgroundAudioService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Bundle extras = intent.getExtras();
        text = extras.getString("passou");
        mediaPlayer = MediaPlayer.create(this, R.raw.s);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.stop();
        mediaPlayer.release();
        return super.onUnbind(intent);
    }

    public void play(){
        mediaPlayer.start();
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void pause(){
        mediaPlayer.pause();
    }

    public int getCurrentPosition(){
        return mediaPlayer.getCurrentPosition();
    }
}
