package com.prueba.evaluacionapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.prueba.evaluacionapp.R;
import com.prueba.evaluacionapp.adaptador.AdaptadorAsignatura;
import com.prueba.evaluacionapp.objeto.AsignaturaI;
import com.prueba.evaluacionapp.sqlite.OperacionesCRUD;
import com.prueba.evaluacionapp.sqlite.esquema.Asignatura;
import com.prueba.evaluacionapp.sqlite.esquema.EstudianteAsignatura;

import java.util.ArrayList;
import java.util.List;

public class ListaEstudianteAsignatura extends AppCompatActivity {

    Spinner asignaturasAgregar;
    RecyclerView listauserAsignaturas;
    int id_usuario;
    RecyclerView.LayoutManager manejador;
    RecyclerView.Adapter adaptadorRecycler;
    ArrayList<AsignaturaI> listAsignatura = new ArrayList<>();
    OperacionesCRUD instancia;
    List<String> dataSpinner = new ArrayList<String>();
    ArrayAdapter adaptadorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estudiante_asignatura);

        if (null != this.getIntent()) {
            if (null != this.getIntent().getExtras()) {
                Bundle parametroEntrada = this.getIntent().getExtras();
                id_usuario = parametroEntrada.getInt("id_usuario");
            }
        }

        asignaturasAgregar = findViewById(R.id.spinner);
        listauserAsignaturas = findViewById(R.id.recyclerasig);
        instancia = new OperacionesCRUD(this, "BDPROGRAMA", null, 2);

        llenarSpinner();
        llenarRecyclerView();


    }

    public void llenarSpinner() {
        String columnasObtenerSpinner[] = {Asignatura.Esquema.ID,
                Asignatura.Esquema.CODIGO,
                Asignatura.Esquema.DESCRIPCION};

        String condicionSpinner = Asignatura.Esquema.ID + " not in (select id_asignatura from usuario_asignatura where id_usuario = ?)";

        String valoresCondicionSpinner[] = {String.valueOf(id_usuario)};

        List<ContentValues> asignaturasNoAsociadas =
                instancia.obtenerDatos(columnasObtenerSpinner, condicionSpinner, valoresCondicionSpinner, Asignatura.Esquema.TABLE_ASIGNATURA);

        if (asignaturasNoAsociadas == null) {
            Toast.makeText(this, "No se obtuvieron registros de asignaturas asociadas", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < asignaturasNoAsociadas.size(); i++) {
                ContentValues auxiliar = asignaturasNoAsociadas.get(i);
                String opcionSpinner = "";

                for (String key : auxiliar.keySet()) {
                    opcionSpinner += auxiliar.get(key).toString() + ":";
                }
                dataSpinner.add(opcionSpinner);
            }
        }
        adaptadorSpinner = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dataSpinner);
        asignaturasAgregar.setAdapter(adaptadorSpinner);
    }

    public void llenarRecyclerView() {

        String columnasObtener[] = {Asignatura.Esquema.ID,
                Asignatura.Esquema.CODIGO,
                Asignatura.Esquema.DESCRIPCION};

        String condicion = Asignatura.Esquema.ID +
                " in (select id_asignatura from usuario_asignatura where id_usuario = ?)";

        String valoresCondicion[] = {String.valueOf(id_usuario)};

        List<ContentValues> asignaturaUsuario = instancia.obtenerDatos(columnasObtener,
                condicion,
                valoresCondicion,
                Asignatura.Esquema.TABLE_ASIGNATURA);
        if (asignaturaUsuario == null) {
            Toast.makeText(this, "No se obtuvieron asignatura asociadas al usuario", Toast.LENGTH_SHORT).show();

        } else {

            for (int i = 0; i < asignaturaUsuario.size(); i++) {

                ContentValues auxiliar = asignaturaUsuario.get(i);
                AsignaturaI nuevaAsig = new AsignaturaI();
                for (String key : auxiliar.keySet()) {
                    switch (key.toString()) {
                        case Asignatura.Esquema.ID:
                            nuevaAsig.setId_asignatura(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case Asignatura.Esquema.CODIGO:
                            nuevaAsig.setCodigo(auxiliar.get(key).toString());
                            break;
                        case Asignatura.Esquema.DESCRIPCION:
                            nuevaAsig.setDescripcion(auxiliar.get(key).toString());
                            break;
                    }
                }
                listAsignatura.add(nuevaAsig);
            }
        }
        listauserAsignaturas.setHasFixedSize(true);
        manejador = new LinearLayoutManager(this);
        adaptadorRecycler = new AdaptadorAsignatura(listAsignatura);

        listauserAsignaturas.setLayoutManager(manejador);
        listauserAsignaturas.setAdapter(adaptadorRecycler);
    }

    public void ingresoAsigUsuario(View view) {

        ContentValues nuevo_user_asignatura = new ContentValues();
        nuevo_user_asignatura.put("id_usuario", id_usuario);
        String itemseleccionadoSpinner = asignaturasAgregar.getSelectedItem().toString();

        int posicionItemSeleccionado = asignaturasAgregar.getSelectedItemPosition();

        String dataItem[] = itemseleccionadoSpinner.split(":");
        nuevo_user_asignatura.put("id_asignatura", dataItem[2]);

        long ret = instancia.insertarTabla(nuevo_user_asignatura, EstudianteAsignatura.Esquema.TABLE_USER_ASIG);

        if (ret == 0) {
            Toast.makeText(this, "No logro insertar usuario asignatura", Toast.LENGTH_SHORT).show();
        } else {
            AsignaturaI nueva = new AsignaturaI(
                    dataItem[0], dataItem[1], Integer.parseInt(dataItem[2]));

            listAsignatura.add(nueva);
            adaptadorRecycler = new AdaptadorAsignatura(listAsignatura);

            listauserAsignaturas.setAdapter(adaptadorRecycler);

            dataSpinner.remove(posicionItemSeleccionado);
            adaptadorSpinner = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dataSpinner);
            asignaturasAgregar.setAdapter(adaptadorSpinner);

        }

    }
}