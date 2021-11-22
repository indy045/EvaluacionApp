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
import com.prueba.evaluacionapp.adaptador.AdaptadorAsignatura;
import com.prueba.evaluacionapp.objeto.AsignaturaI;
import com.prueba.evaluacionapp.sqlite.OperacionesCRUD;
import com.prueba.evaluacionapp.sqlite.esquema.Asignatura;

import java.util.ArrayList;
import java.util.List;

public class AsignaturaMain extends AppCompatActivity {
    RecyclerView.LayoutManager manejador1;
    RecyclerView.Adapter adaptador1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura_main);

        OperacionesCRUD instancia = new OperacionesCRUD(this, "BDPROGRAMA", null, 2);
        String[] columnasAsignatura = Asignatura.Esquema.ALLCOLUMNAS;
        String condicion = "";
        String[] valCondicion = {};

        List<ContentValues> asignaturasObtenidas = instancia.obtenerDatos(columnasAsignatura, condicion,
                valCondicion, Asignatura.Esquema.TABLE_ASIGNATURA);

        ArrayList<AsignaturaI> listaAsignatura = new ArrayList<>();

        if (asignaturasObtenidas == null) {
            Toast.makeText(this, "No se obtuvieron Asignaturas", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < asignaturasObtenidas.size(); i++) {

                ContentValues auxiliar = asignaturasObtenidas.get(i);
                System.out.println("Entra a for");
                AsignaturaI nuevoItemAsignatura = new AsignaturaI();
                for (String key : auxiliar.keySet()) {
                    switch (key.toString()) {
                        case Asignatura.Esquema.ID:
                            nuevoItemAsignatura.setId_asignatura(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case Asignatura.Esquema.CODIGO:
                            nuevoItemAsignatura.setCodigo(auxiliar.get(key).toString());
                            break;
                        case Asignatura.Esquema.DESCRIPCION:
                            nuevoItemAsignatura.setDescripcion(auxiliar.get(key).toString());
                    }

                }
                listaAsignatura.add(nuevoItemAsignatura);
            }
            RecyclerView lista = findViewById(R.id.rv3);
            lista.setHasFixedSize(true);

            manejador1 = new LinearLayoutManager(this);
            adaptador1 = new AdaptadorAsignatura(listaAsignatura);
            lista.setLayoutManager(manejador1);
            lista.setAdapter(adaptador1);

        }

    }
    public void agregarasignatura(View view){
        Intent i = new Intent(AsignaturaMain.this, AsignaturaES.class);
        startActivity(i);
    }

}