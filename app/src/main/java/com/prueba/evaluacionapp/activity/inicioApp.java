package com.prueba.evaluacionapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

import com.prueba.evaluacionapp.R;

public class inicioApp extends AppCompatActivity {

    ImageView iv1;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_app);
        // conexion de la parte logica con la grafica
        iv1 = (ImageView) findViewById(R.id.iv1);

        //animacion del logo
        iv1.animate()
                .alpha(1f)
                .scaleY(1f)
                .scaleX(1f)
                .setDuration(5000);

        // ejecutamos el hilo
        Thread t = new Thread(
                new Runnable() {
                    //creamos el hilo intent explicito
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(7000);
                            startActivity(new Intent(inicioApp.this, MainActivity.class));

                            finish();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );
        t.start();
        // inicia con la activity_inicio. Audio intent implicito
        mp = MediaPlayer.create(this, R.raw.inicio);
        mp.start();

    }

}
