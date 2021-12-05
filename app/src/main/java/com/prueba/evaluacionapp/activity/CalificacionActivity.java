package com.prueba.evaluacionapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prueba.evaluacionapp.R;
import com.prueba.evaluacionapp.objeto.CalificacionI;

import java.util.UUID;

public class CalificacionActivity extends AppCompatActivity {
    EditText fecha;
    EditText calificacion;
    EditText comentario;

    FirebaseDatabase database;
    DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);

        fecha = (EditText) findViewById(R.id.fecha);
        calificacion = (EditText) findViewById(R.id.calificacion);
        comentario = (EditText) findViewById(R.id.comentario);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        referencia = database.getReference().child("calificacion");
    }
    public void insertar(View view){
        try {
            CalificacionI nuevaCalificacion = new CalificacionI();
            String id_registro = UUID.randomUUID().toString();
            nuevaCalificacion.setFecha(fecha.getText().toString());
            nuevaCalificacion.setCalificacion(calificacion.getText().toString());
            nuevaCalificacion.setComentario(comentario.getText().toString());
            nuevaCalificacion.setId_calificacion(id_registro);

            referencia.child(id_registro).setValue(nuevaCalificacion);
            Toast.makeText(this, "Datos insertados", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, CalificacionES.class);
            startActivity(i);

        }catch (Exception e){
            Toast.makeText(this, "Error de ingreso de datos", Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    public void verlista(View view) {
        Intent i = new Intent(this, CalificacionES.class);
        startActivity(i);
    }


}