package com.prueba.evaluacionapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.prueba.evaluacionapp.R;
import com.prueba.evaluacionapp.sqlite.OperacionesCRUD;
import com.prueba.evaluacionapp.sqlite.esquema.Asignatura;

public class AsignaturaES extends AppCompatActivity {
    EditText etcodigo;
    EditText etdescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura_es);

        etcodigo = findViewById(R.id.etcodigo);
        etdescripcion = findViewById(R.id.etdescripcion);
    }
    public void insertAsig(View view) {

        OperacionesCRUD instancia = new OperacionesCRUD(this, "BDPROGRAMA", null, 2);

        ContentValues datosAsignatura = new ContentValues();
        datosAsignatura.put(Asignatura.Esquema.CODIGO, etcodigo.getText().toString());
        datosAsignatura.put(Asignatura.Esquema.DESCRIPCION, etdescripcion.getText().toString());
        long id_asignatura_insertado = 0;
        id_asignatura_insertado = instancia.insertarTabla(datosAsignatura, Asignatura.Esquema.TABLE_ASIGNATURA);

        Toast.makeText(this, "id insertado " + id_asignatura_insertado, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(AsignaturaES.this, AsignaturaMain.class);
        startActivity(i);
    }
    public void volver(View view){
        Intent i = new Intent(AsignaturaES.this, ingresoestudiantes.class);
        startActivity(i);
    }
}