package com.example.evaluacionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class inicioApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_app);

    }
    public void bienvenida(View view){
    Intent i = new Intent(this, MainActivity.class);
    startActivity(i);
    }
}