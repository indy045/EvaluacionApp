package com.prueba.evaluacionapp.adaptador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.prueba.evaluacionapp.R;
import com.prueba.evaluacionapp.activity.CalificacionActivity;
import com.prueba.evaluacionapp.objeto.AsignaturaI;
import com.prueba.evaluacionapp.sqlite.OperacionesCRUD;
import com.prueba.evaluacionapp.sqlite.esquema.Asignatura;

import java.util.ArrayList;

public class AdaptadorAsignatura extends RecyclerView.Adapter<AdaptadorAsignatura.AsignaturaHolder> {

    private ArrayList<AsignaturaI> asignaturaDesplegar;

    public AdaptadorAsignatura(ArrayList<AsignaturaI>asignaturaDesplegarIn){
        asignaturaDesplegar = asignaturaDesplegarIn;

    }
    @NonNull
    @Override
    //se crean las vistas sin personalizar
    public AsignaturaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asignaturas, parent, false);
        AsignaturaHolder holder = new AsignaturaHolder(item);
        return holder;
    }
    //asocia el asignaturaholder con los datos. para personlizar la tarjeta
    @Override
    public void onBindViewHolder(@NonNull AsignaturaHolder holder, int position) {

        AsignaturaI item = asignaturaDesplegar.get(position);
        holder.codigo.setText(item.getId_asignatura() + ": "
                + item.getCodigo());
        holder.descripcion.setText(item.getDescripcion());
        //seteamos un listener  al eliminar para cuando se haga click
        holder.eliminar.setId(item.getId_asignatura());
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String condicion = "id_asignatura=?";//se borraran los datos del id_asignatura q elija
                String valores[] = {"" + item.getId_asignatura()};//damos el id del item seleccionado
                int cant_regs_eliminados = 0;

                //instanciamos la clase operacionescrud
                OperacionesCRUD instancia = new OperacionesCRUD(view.getContext(), "BDPROGRAMA", null, 2);
                //borramos los registros pasando los valores de la tablaAsig
                cant_regs_eliminados = instancia.borrarRegistro(Asignatura.Esquema.TABLE_ASIGNATURA, condicion, valores);

                if (cant_regs_eliminados > 0) { //si cantidad de registro es > 0 pasamos el mensaje
                    Toast.makeText(view.getContext(), "Asignatura eliminada", Toast.LENGTH_SHORT).show();
                    AdaptadorAsignatura.this.asignaturaDesplegar.remove(holder.getAdapterPosition());
                    AdaptadorAsignatura.this.notifyDataSetChanged();
                } else { //sino error
                    Toast.makeText(view.getContext(), "Error al eliminar Asignatura", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    //se obtiene el numero de elementos
    @Override
    public int getItemCount() {
        return asignaturaDesplegar.size();
    }


    //indicamos todos los elementos que queremos personalizar
    public class AsignaturaHolder extends RecyclerView.ViewHolder {

        public TextView codigo;
        public TextView descripcion;
        public ImageButton eliminar;


        //conectamos la parte logica con la parte grafica
        public AsignaturaHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.codigo);
            descripcion = itemView.findViewById(R.id.descripcion);
            eliminar = itemView.findViewById(R.id.eliminar);

        }
    }
}
