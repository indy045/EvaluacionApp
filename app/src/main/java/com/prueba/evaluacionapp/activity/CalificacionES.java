package com.prueba.evaluacionapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prueba.evaluacionapp.R;
import com.prueba.evaluacionapp.adaptador.AdaptadorCalificacion;
import com.prueba.evaluacionapp.objeto.CalificacionI;

import java.util.ArrayList;


public class CalificacionES extends AppCompatActivity {
    RecyclerView recycler;
    AdaptadorCalificacion adaptador;
    ArrayList<CalificacionI> data;

    FirebaseDatabase mibd;
    DatabaseReference referencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion_es);

        FirebaseApp.initializeApp(this);
        mibd = FirebaseDatabase.getInstance();
        referencia = mibd.getReference().child("calificacion");

        recycler = findViewById(R.id.rv3);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        data = new ArrayList<>();
        adaptador = new AdaptadorCalificacion(data);
        recycler.setAdapter(adaptador);

        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot auxiliar : snapshot.getChildren()) {
                        CalificacionI objeto = auxiliar.getValue(CalificacionI.class);
                        data.add(objeto);
                    }
                    adaptador.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void volver(View view) {
        Intent i = new Intent(this, CalificacionActivity.class);
        startActivity(i);


    }
}