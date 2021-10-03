package com.example.evaluacionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class PresenteApp extends AppCompatActivity implements View.OnClickListener {
    TextView tv7;
    Button btnFecha;
    Button btnHora;
    EditText txthora;
    EditText txtfecha;
    int dia, mes, ano, hora, minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presente_app);

        btnFecha = (Button) findViewById(R.id.btnfecha);
        txtfecha = (EditText) findViewById(R.id.txtfecha);
        btnHora = (Button) findViewById(R.id.btnhora);
        txthora = (EditText) findViewById(R.id.txthora);


        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);


    }

    public void onClick(View view) {
        if (view == btnFecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txtfecha.setText(dayOfMonth + "/" + month + 1 + "/" + year);
                }

            }, dia, mes, ano);
            datePickerDialog.show();
        }
        if (view == btnHora) {
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txthora.setText(hourOfDay + ":" + minute);
                }
            }, hora, minutos, false);
            timePickerDialog.show();
        }

    }


}