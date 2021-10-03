package com.example.evaluacionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ingresoestudiantes extends AppCompatActivity implements View.OnClickListener {
    Button btnAgregar;

    ListView mLista;
    TextView tv2;
    EditText txtLista;
    List<String> m_lista = new ArrayList<>();
    ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresoestudiantes);

        tv2 = (TextView) findViewById(R.id.tV2);

        btnAgregar = (Button) findViewById(R.id.btnagregar);
        mLista = (ListView) findViewById(R.id.lista);
        txtLista = (EditText) findViewById(R.id.txtlista);

        btnAgregar.setOnClickListener(this);


        //Muestra el texto de la activiy anterior
        String dato = getIntent().getStringExtra("dato");
        tv2.setText("" + dato);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnagregar:
                String texto = txtLista.getText().toString().trim();
                m_lista.add(texto);
                txtLista.getText().clear();
                mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, m_lista);
                mLista.setAdapter(mAdapter);
        }

    }

    public void siguiente(View view) {
        Intent i = new Intent(this, PresenteApp.class);
        startActivity(i);
    }
}