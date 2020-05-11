package com.example.magistore.modelo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.IBinder;

import com.example.magistore.R;

import java.util.Random;

public class Serve extends Service {
MediaPlayer miReproductor;

public void onCreate(){

 int sonido =new Random().nextInt(3)+1;
    super.onCreate();
    if(sonido==1)
         miReproductor= MediaPlayer.create(this, R.raw.explosion);
    else if(sonido==2)
         miReproductor= MediaPlayer.create(this, R.raw.gato);
    else
         miReproductor= MediaPlayer.create(this, R.raw.laser);

    miReproductor.setLooping(true);
    miReproductor.setVolume(100, 100);

}


public int onStartCommand(Intent intent, int flags, int startId){
//Iniciar sservicio
 miReproductor.start();
 return START_STICKY;

}

public void onDestroy(){
   super.onDestroy();
   //Detenr el servicio
   if(miReproductor.isPlaying())
       miReproductor.start();
   miReproductor.release();
   miReproductor=null;

}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
