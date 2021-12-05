package com.prueba.evaluacionapp.adaptador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prueba.evaluacionapp.R;
import com.prueba.evaluacionapp.activity.EditarCalificacion;
import com.prueba.evaluacionapp.objeto.CalificacionI;

import java.util.ArrayList;

public class AdaptadorCalificacion extends RecyclerView.Adapter<AdaptadorCalificacion.CalificacionHolder> {

    private ArrayList<CalificacionI> calificacionDesplegar;

    FirebaseDatabase database;
    DatabaseReference referencia;
    public AdaptadorCalificacion(ArrayList<CalificacionI> calificacionDesplegarIn){
        calificacionDesplegar = calificacionDesplegarIn;
    }


    @NonNull
    @Override
    public AdaptadorCalificacion.CalificacionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calificacion, parent,false);
        CalificacionHolder holder = new CalificacionHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCalificacion.CalificacionHolder holder, int position) {

        CalificacionI item = calificacionDesplegar.get(position);
        holder.fecha.setText(item.getId_calificacion()+" :");
        holder.fecha.setText(item.getFecha());
        holder.calificacion.setText(item.getCalificacion());

        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseApp.initializeApp(view.getContext());
                database = FirebaseDatabase.getInstance();
                referencia = database.getReference().child("calificacion");

                referencia.child(String.valueOf(item.getId_calificacion())).removeValue();
                AdaptadorCalificacion.this.calificacionDesplegar.remove(holder.getAdapterPosition());
                AdaptadorCalificacion.this.notifyDataSetChanged();
            }
        });

        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editarCalificacion = new Intent(view.getContext(), EditarCalificacion.class);

                editarCalificacion.putExtra("id",item.getId_calificacion());
                editarCalificacion.putExtra("fecha",item.getFecha());
                editarCalificacion.putExtra("calificacion",item.getCalificacion());
                editarCalificacion.putExtra("comentario",item.getComentario());

                view.getContext().startActivity(editarCalificacion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return calificacionDesplegar.size();
    }
    public class CalificacionHolder extends RecyclerView.ViewHolder{
        public TextView fecha;
        public TextView calificacion;
        public ImageButton eliminar;
        public ImageButton editar;

        public CalificacionHolder(@NonNull View itemView) {
            super(itemView);

            fecha = itemView.findViewById(R.id.fecha);
            calificacion = itemView.findViewById(R.id.calificacion);
            eliminar = itemView.findViewById(R.id.eliminar);
            editar = itemView.findViewById(R.id.editar);
        }
    }
}
