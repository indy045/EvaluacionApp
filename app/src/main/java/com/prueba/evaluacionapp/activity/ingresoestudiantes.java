package com.prueba.evaluacionapp.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


import com.prueba.evaluacionapp.R;
import com.prueba.evaluacionapp.sqlite.OperacionesCRUD;
import com.prueba.evaluacionapp.sqlite.esquema.Estudiante;


public class ingresoestudiantes extends AppCompatActivity {
    EditText etnombre;
    EditText etApellidoP;
    EditText etApellidoM;
    EditText etDireccion;
    EditText etEmail;
    EditText etEdad;
    RadioButton femenino;
    RadioButton masculino;
    EditText etcelular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresoestudiantes);

        etnombre = (EditText) findViewById(R.id.etnombre);
        etApellidoP = (EditText) findViewById(R.id.etapellidoP);
        etApellidoM = (EditText) findViewById(R.id.etApellidoM);
        etDireccion = (EditText) findViewById(R.id.etdireccion);
        etEmail = (EditText) findViewById(R.id.etemail);
        etEdad = (EditText) findViewById(R.id.etedad);
        femenino = (RadioButton) findViewById(R.id.rbfem);
        masculino = (RadioButton) findViewById(R.id.rbmas);
        etcelular = (EditText) findViewById(R.id.ettelefono);
    }
    //se crea el metodo insertar
    public void insert(View view) {
        //instacia a la clase operacionescrud /entregando los parametros de entrada
        OperacionesCRUD instancia = new OperacionesCRUD(this, "BDPROGRAMA", null, 2);
        //inicializar y generar mapeo de datos
        ContentValues datosusuario = new ContentValues();
        //insertadmos los datos con el metodo put, clase, esquema, nombre de columnas
        datosusuario.put(Estudiante.Esquema.NOMBRE, etnombre.getText().toString());
        datosusuario.put(Estudiante.Esquema.APEPATERNO, etApellidoP.getText().toString());
        datosusuario.put(Estudiante.Esquema.APEMATERNO, etApellidoM.getText().toString());
        datosusuario.put(Estudiante.Esquema.DIRECCION, etDireccion.getText().toString());
        datosusuario.put(Estudiante.Esquema.EMAIL, etEmail.getText().toString());
        datosusuario.put(Estudiante.Esquema.EDAD, etEdad.getText().toString());

        if (masculino.isChecked()) {
            datosusuario.put(Estudiante.Esquema.GENERO, masculino.getText().toString());
        }
        if (femenino.isChecked()) {
            datosusuario.put(Estudiante.Esquema.GENERO, femenino.getText().toString());
        }

        datosusuario.put(Estudiante.Esquema.TELEFONO, etcelular.getText().toString());
        //inicializamos en 0
        long id_user_insertado = 0;

        //insertamos en la base de datos los datos de la tabla
        id_user_insertado = instancia.insertarTabla(datosusuario, Estudiante.Esquema.TABLE_NAME);
        // mensaje de insertado agregando el id
        Toast.makeText(this, "id insertado " + id_user_insertado, Toast.LENGTH_SHORT).show();
        //metodo instent para que vaya a la activity lista de estudiantes
        Intent i = new Intent(ingresoestudiantes.this, listaestudiantesactivity.class);
        startActivity(i);
    }

    public void asignatura(View view) {
        Intent i = new Intent(ingresoestudiantes.this, AsignaturaES.class);
        startActivity(i);
    }

    public void lista(View view) {
        Intent i = new Intent(ingresoestudiantes.this, listaestudiantesactivity.class);
        startActivity(i);


    }
}