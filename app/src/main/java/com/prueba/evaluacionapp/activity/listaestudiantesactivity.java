package com.prueba.evaluacionapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.prueba.evaluacionapp.R;
import com.prueba.evaluacionapp.adaptador.AdaptadorEstudiante;
import com.prueba.evaluacionapp.objeto.EstudiantesI;
import com.prueba.evaluacionapp.sqlite.OperacionesCRUD;
import com.prueba.evaluacionapp.sqlite.esquema.Estudiante;

import java.util.ArrayList;
import java.util.List;


public class listaestudiantesactivity extends AppCompatActivity {

    RecyclerView.LayoutManager manejador;
    RecyclerView.Adapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaestudiantesactivity);

        OperacionesCRUD instancia = new OperacionesCRUD(this, "BDPROGRAMA", null, 2);

        String[] columnasEstudiantes = Estudiante.Esquema.ALLCOLUMNAS;
        String condicion = "";
        String[] valCondicion = {};

        List<ContentValues> usuariosObtenidos = instancia.obtenerDatos(columnasEstudiantes, condicion
                , valCondicion, Estudiante.Esquema.TABLE_NAME);

        ArrayList<EstudiantesI> listaUsuarios = new ArrayList<>();
        if (usuariosObtenidos == null) {
            Toast.makeText(this, "No se obtuvieron usuarios desde base de datos", Toast.LENGTH_LONG).show();
        } else {
            for (int i = 0; i < usuariosObtenidos.size(); i++) {

                ContentValues auxiliar = usuariosObtenidos.get(i);
                EstudiantesI nuevoItemUsuario = new EstudiantesI();
                for (String key : auxiliar.keySet()) {
                    switch (key.toString()) {
                        case Estudiante.Esquema.ID:
                            nuevoItemUsuario.setId_usuario(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case Estudiante.Esquema.NOMBRE:
                            nuevoItemUsuario.setNombre(auxiliar.get(key).toString());
                            break;
                        case Estudiante.Esquema.APEPATERNO:
                            nuevoItemUsuario.setApePaterno(auxiliar.get(key).toString());
                            break;
                        case Estudiante.Esquema.APEMATERNO:
                            nuevoItemUsuario.setApeMaterno(auxiliar.get(key).toString());
                            break;
                        case Estudiante.Esquema.EMAIL:
                            nuevoItemUsuario.setEmail(auxiliar.get(key).toString());
                            break;
                        case Estudiante.Esquema.EDAD:
                            nuevoItemUsuario.setEdad(auxiliar.get(key).toString());
                            break;
                        case Estudiante.Esquema.DIRECCION:
                            nuevoItemUsuario.setDireccion(auxiliar.get(key).toString());
                            break;
                        case Estudiante.Esquema.GENERO:
                            nuevoItemUsuario.setGenero(auxiliar.get(key).toString());
                            break;
                        case Estudiante.Esquema.TELEFONO:
                            nuevoItemUsuario.setTelefono(auxiliar.get(key).toString());
                            break;
                    }

                }
                listaUsuarios.add(nuevoItemUsuario);
            }

            RecyclerView lista = findViewById(R.id.rv1);
            lista.setHasFixedSize(true);

            manejador = new LinearLayoutManager(this);
            adaptador = new AdaptadorEstudiante(listaUsuarios);
            lista.setLayoutManager(manejador);
            lista.setAdapter(adaptador);

        }
    }
    public void asignatura(View view){
        Intent i = new Intent(listaestudiantesactivity.this, AsignaturaES.class);
        startActivity(i);
    }

    public void agregarUsuario(View view){
        Intent i = new Intent(listaestudiantesactivity.this, ingresoestudiantes.class);
        startActivity(i);
    }


}