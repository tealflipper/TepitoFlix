package com.example.tepitoflix;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.io.File;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ClearFromRecentService", "Service Started");
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        File fileM = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "movie.json");
        File fileS = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "serie.json");
        File fileC = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "cd.json");
        File fileA = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "artist.json");
        File fileG = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "genre.json");
        File fileD = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "Director.json");
        Log.d("ClearFromRecentService", "Service Destroyed");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("ClearFromRecentService", "END");
        File fileM = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "movie.json");
        File fileS = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "serie.json");
        File fileC = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "cd.json");
        File fileA = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "artist.json");
        File fileG = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "genre.json");
        File fileD = new File(ContextCompat.getExternalFilesDirs(this,null)[1],
                "Director.json");
        stopSelf();
    }

}
