package com.example.evaluacionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtNombre;
    TextView txtPass;
    ProgressBar progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (TextView) findViewById(R.id.txt_nombre);
        txtPass = (TextView) findViewById(R.id.txt_pass);

        progress = (ProgressBar) findViewById(R.id.progress);


    }

    //Metodo para el Boton Ingresar

    public void ingresar (View view) {

        String nombre = txtNombre.getText().toString();
        String pass = txtPass.getText().toString();

        //length contar caracteres de nombre // validacion de registro
        if (nombre.length() == 0) {
            Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_LONG).show();
        }
        if (pass.length() == 0) {
            Toast.makeText(this, "Debes ingresar un password", Toast.LENGTH_LONG).show();

        }
        if (nombre.length() != 0 && pass.length() != 0) {
            Toast.makeText(this, "Registro en Proceso....", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, Ingresoasignatura.class);
            i.putExtra("datos", txtNombre.getText().toString());
            startActivity(i);

        }

    }

}