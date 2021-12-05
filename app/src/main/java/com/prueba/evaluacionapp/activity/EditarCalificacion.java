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

public class EditarCalificacion extends AppCompatActivity {

    EditText edfecha;
    EditText edcalificacion;
    EditText edcomentario;

    String id_calificacion_e = "";
    String fecha_ed = "";
    String calificacion_ed = "";
    String comentario_ed = "";

    FirebaseDatabase database;
    DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_calificacion);

        if (null != this.getIntent()) {

            if (null != this.getIntent().getExtras()) {

                Bundle paramEntrada = this.getIntent().getExtras();
                id_calificacion_e = paramEntrada.getString("id");
                fecha_ed = paramEntrada.getString("fecha");
                calificacion_ed = paramEntrada.getString("calificacion");
                comentario_ed = paramEntrada.getString("comentario");
            }
        }

        edfecha = findViewById(R.id.editfecha);
        edfecha.setText(fecha_ed);

        edcalificacion = findViewById(R.id.editcalificacion);
        edcalificacion.setText(calificacion_ed);

        edcomentario = findViewById(R.id.editcomentario);
        edcomentario.setText(comentario_ed);

    }

    public void editarcalificacion(View view) {
        try {
            FirebaseApp.initializeApp(this);
            database = FirebaseDatabase.getInstance();
            referencia = database.getReference().child("calificacion");

            CalificacionI califica = new CalificacionI();

            califica.setId_calificacion(id_calificacion_e);
            califica.setFecha(edfecha.getText().toString());
            califica.setCalificacion(edcalificacion.getText().toString());
            califica.setComentario(edcomentario.getText().toString());

            referencia.child(id_calificacion_e).setValue(califica);
            Toast.makeText(this, "Registro Actualizado", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            Toast.makeText(this, "Error de edicion" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void volver(View view) {
        Intent i = new Intent(this, CalificacionES.class);
        startActivity(i);
    }
}