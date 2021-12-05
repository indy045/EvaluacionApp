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

public class ActualizarEstudiantes extends AppCompatActivity {

    EditText ednombre;
    EditText edapellidoP;
    EditText edapellidoM;
    EditText eddireccion;
    EditText edemail;
    EditText ededad;
    RadioButton femenino;
    RadioButton masculino;
    EditText edtelefono;

    int id_user_entrada = 0;
    String edad_user_entrada = "";
    String telefono_user_entrada = "";
    String etnombre_user_entrada = "";
    String etApellidoP_user_entrada = "";
    String etApellidoM_user_entrada = "";
    String etDireccion_user_entrada = "";
    String etEmail_user_entrada = "";
    String etgenero_user_entrada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_estudiantes);

        //agregamos las varialbes para guardar los parametros q pasamos al adptador
        //para obtener los parametros segun su clave
        if (null != this.getIntent()) {
            if (null != this.getIntent().getExtras()) {
                Bundle parametrosEntrada = this.getIntent().getExtras();
                id_user_entrada = parametrosEntrada.getInt("id");
                edad_user_entrada = parametrosEntrada.getString("edad");
                telefono_user_entrada = parametrosEntrada.getString("telefono");
                etnombre_user_entrada = parametrosEntrada.getString("nombre");
                etApellidoP_user_entrada = parametrosEntrada.getString("apePaterno");
                etApellidoM_user_entrada = parametrosEntrada.getString("apeMaterno");
                etEmail_user_entrada = parametrosEntrada.getString("email");
                etDireccion_user_entrada = parametrosEntrada.getString("direccion");
                etgenero_user_entrada = parametrosEntrada.getString("genero");

            }
//obtenemos hacemos la conexion logica y grafica para obtener los datos por id
            ednombre = (EditText) findViewById(R.id.ednombre);
            ednombre.setText(etnombre_user_entrada);

            edapellidoP = (EditText) findViewById(R.id.edapellidoP);
            edapellidoP.setText(etApellidoP_user_entrada);

            edapellidoM = (EditText) findViewById(R.id.edapellidoM);
            edapellidoM.setText(etApellidoM_user_entrada);

            eddireccion = (EditText) findViewById(R.id.eddireccion);
            eddireccion.setText(etDireccion_user_entrada);

            edemail = (EditText) findViewById(R.id.edemail);
            edemail.setText(etEmail_user_entrada);

            ededad = findViewById(R.id.ededad);
            ededad.setText(edad_user_entrada);

            edtelefono = (EditText) findViewById(R.id.edtelefono);
            edtelefono.setText(telefono_user_entrada);

            femenino = (RadioButton) findViewById(R.id.rbfem);
            masculino = (RadioButton) findViewById(R.id.rbmas);

            if (etgenero_user_entrada.toUpperCase().equals("MASCULINO")) {
                masculino.setChecked(true);
                femenino.setChecked(false);
            } else {
                masculino.setChecked(false);
                femenino.setChecked(true);
            }
        }
    }

    public void editarUsuario(View view) {
        //instancia a la clase operacionescrud
        OperacionesCRUD instancia = new OperacionesCRUD(this, "BDPROGRAMA", null, 2);
        //seteamos el objeto clave y valor en cada elemento del formulario
        ContentValues datosNuevosUsuario = new ContentValues();

        datosNuevosUsuario.put("nombre", ednombre.getText().toString());
        datosNuevosUsuario.put("apePaterno", edapellidoP.getText().toString());
        datosNuevosUsuario.put("apeMaterno", edapellidoM.getText().toString());
        datosNuevosUsuario.put("email", edemail.getText().toString());
        datosNuevosUsuario.put("edad", ededad.getText().toString());
        datosNuevosUsuario.put("direccion", eddireccion.getText().toString());

        if (masculino.isChecked())
            datosNuevosUsuario.put("genero", masculino.getText().toString());
        if (femenino.isChecked())
            datosNuevosUsuario.put("genero", femenino.getText().toString());

        datosNuevosUsuario.put("telefono", edtelefono.getText().toString());

        //modificaremos el registro por id_usuario elegido desde la lista
        String condicion = "id_usuario = ?";
        String valores[] = {id_user_entrada + ""};
        int cantidad_actualizados = 0;
        //actualiza los datos del usuario segun la condicion y valores
        //devuelve la cantidad de registros modificados
        cantidad_actualizados = instancia.actualizarRegistro(datosNuevosUsuario,
                condicion, valores, Estudiante.Esquema.TABLE_NAME);

        if (cantidad_actualizados > 0) {
            Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error actualizando usuario", Toast.LENGTH_LONG).show();
        }
    }

    public void volver(View view) {
        Intent i = new Intent(this, listaestudiantesactivity.class);
        startActivity(i);

    }

    public void asignatura(View view) {
        Intent i = new Intent(this, AsignaturaES.class);
        startActivity(i);


    }
}
