package com.example.usuario.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements  SensorEventListener {

    TextView lblvalor,lblreprod;
    SensorManager manager;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp=MediaPlayer.create(MainActivity.this,R.raw.bracula);
        mp.setLooping(true);
        lblvalor = findViewById(R.id.lblValor);
        lblreprod = findViewById(R.id.lblreprod);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        manager.registerListener((SensorEventListener) this,
                manager.getDefaultSensor(Sensor.TYPE_LIGHT),
                manager.SENSOR_DELAY_NORMAL);
        manager.registerListener((SensorEventListener) this,
                manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                manager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            lblvalor.setText(""+event.values[0]);
        }
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float z;
            mp.start();
            z=event.values[2];
            if(z<0){
                mp.pause();
                lblreprod.setText("En pausa");
            }else{
                mp.start();
                lblreprod.setText("Reproduciendo");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}