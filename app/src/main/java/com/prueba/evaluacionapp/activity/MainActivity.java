package com.prueba.evaluacionapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.prueba.evaluacionapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //acelerometro
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int latigo=0;
    //Login
    TextInputLayout txtNombre;
    TextInputLayout txtPass;
    //cargador
    ProgressBar progress;
    //acceso url
    ImageView ivGoogle, ivFace, ivTwitter;
    private final static String GOOGLE_URL = "https://www.google.cl/";
    private final static String FACEBOOK_URL = "https://www.facebook.com/";
    private final static String TWITTER_URL = "https://twitter.com/?lang=es";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        txtNombre = (TextInputLayout) findViewById(R.id.txt_nombre);
        txtPass = (TextInputLayout) findViewById(R.id.txt_pass);
        progress = (ProgressBar) findViewById(R.id.progress);
        ivGoogle = (ImageView)findViewById(R.id.ivgoogle);
        ivFace = (ImageView)findViewById(R.id.ivfb);
        ivTwitter = (ImageView)findViewById(R.id.ivtwitter);

        ivGoogle.setOnClickListener(this);
        ivFace.setOnClickListener(this);
        ivTwitter.setOnClickListener(this);

        //Sensor Acelerometro no existe no funciona el jefe virtual :)
        if(sensor==null)
            finish();

        // de acuerdo al movimiento del celular en x el jefe virtual funciona :)
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                if(x<-5 && latigo==0){
                    System.out.println("valor de giro "+ x);
                    if (x < -5 && latigo == 0){
                        latigo++;
                    }

                }else if(x > 5 && latigo == 1){
                    latigo++;
                }
                if (latigo==2){
                    sonido();
                    latigo = 0;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        start();
    }

    //valida el usuario
    private boolean validarnombre() {
        String nombre = txtNombre.getEditText().getText().toString().trim();
        if (nombre.isEmpty()){
            txtNombre.setError("Debes ingresar un nombre");
            return false;
        } else if (nombre.length()>15){
            txtNombre.setError("Nombre debe tener 15 caracteres");
            return false;
        } else{
            txtNombre.setError(null);
            return true;
        }
    }
    // validar password
    private boolean validarpass(){
        String passw = txtPass.getEditText().getText().toString().trim();

        if (passw.isEmpty()){
            txtPass.setError("Debes ingresar una contraseña");
            return false;
        } else {
            txtPass.setError(null);
            return true;
        }
    }

    //Metodo para el Boton Ingresar

    public void ingresar (View view) {
        if (!validarnombre() | !validarpass())
            return;
        Intent i = new Intent(this, Ingresoasignatura.class);
        i.putExtra("datos", txtNombre.getEditText().getText().toString());
        startActivity(i);


        String ingreso = "Nombre: " + txtNombre.getEditText().getText().toString();
        ingreso += "\n";

        Toast.makeText(this, ingreso, Toast.LENGTH_SHORT).show();
    }
    // Intent para ingreso al mapa de ubicacion de la escuela
    public void ubicacion (View view) {
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

    // Intent para acceso a url de internet
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch(view.getId()){

            case R.id.ivgoogle:
                intent.setData(Uri.parse(GOOGLE_URL));
                startActivity(intent);
                break;

            case R.id.ivfb:
                intent.setData(Uri.parse(FACEBOOK_URL));
                startActivity(intent);

                break;

            case R.id.ivtwitter:
                intent.setData(Uri.parse(TWITTER_URL));
                startActivity(intent);

                break;
        }

    }

    // metodos para sonido de latigo o jefe virtual y activacion del sensor

    @Override
    protected void onPause() {
        stop();
        super.onPause();

    }

    @Override
    protected void onResume() {
        start();
        super.onResume();
    }

    private void sonido(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.latigo);
        mediaPlayer.start();
    }
    private void start(){
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    private void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }
}