package com.example.evaluacionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Ingresoasignatura extends AppCompatActivity {

    TextView tV1;
    Spinner sp1;
    RadioButton rB1;
    RadioButton rB2;
    EditText eT1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresoasignatura);
        tV1 = (TextView) findViewById(R.id.tv1);
        sp1 = (Spinner) findViewById(R.id.spinner);
        rB1 = (RadioButton) findViewById(R.id.rb1);
        rB2 = (RadioButton) findViewById(R.id.rb2);
        eT1 = (EditText) findViewById(R.id.mtresultado);


        //Arreglo unidimensional
        String [] opciones = {"Lenguaje", "Matemáticas", "Historia y Geografía", "Ciencias Naturales"};

        //Comunicacion entre el arreglo y control spinner
        ArrayAdapter<String> adapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_dropdown_item, opciones);
        sp1.setAdapter(adapter);


        //Muestra el texto de la activiy anterior
        String datos = getIntent().getStringExtra("datos");
        tV1.setText("Bienvenido " + datos);

    }

    //Metodo ingreso informacion
    public void jornada(View view){
        if(view.getId()==R.id.rb1){
            if(rB1.isChecked()){
                rB1.setChecked(false);
            }else {
                rB1.setChecked(true);
            }
        }
        if(view.getId()==R.id.rb2){
            if(rB2.isChecked()){
                rB2.setChecked(false);
            }else {
                rB2.setChecked(true);
            }
        }
    }

    public void obtenerinformacion (View view){

        String opciones = sp1.getSelectedItem().toString();
        if (opciones.equals("Lenguaje")){
            eT1.setText("La Asignatura es: "+ opciones +"\n");
        } else if (opciones.equals("Matemáticas")) {
            eT1.setText("La Asignatura es: "+ opciones +"\n");
        }else if (opciones.equals("Historia y Geografía")) {
            eT1.setText("La Asignatura es: "+ opciones +"\n");
        }else if (opciones.equals("Ciencias Naturales")) {
            eT1.setText("La Asignatura es: "+ opciones +"\n");
        }


        if (rB1.isChecked()){
            eT1.append(rB1.getText().toString()+ "\n");
        }
        if (rB2.isChecked()){
            eT1.append(rB2.getText().toString()+ "\n");
        }

    }
    public void siguiente (View view){
    //Seguir a la siguiente activity y llevar dato de nombre de asignatura desde el spinner
        Intent i = new Intent(this, ingresoestudiantes.class);
        i.putExtra("dato", eT1.getText().toString());
        startActivity(i);

    }
}