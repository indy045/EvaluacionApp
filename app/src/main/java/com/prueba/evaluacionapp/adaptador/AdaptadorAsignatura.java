package com.prueba.evaluacionapp.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prueba.evaluacionapp.R;
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
    public AsignaturaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asignaturas, parent, false);
        AsignaturaHolder holder = new AsignaturaHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AsignaturaHolder holder, int position) {

        AsignaturaI item = asignaturaDesplegar.get(position);
        holder.codigo.setText(item.getId_asignatura()+": "
                +item.getCodigo());
        holder.descripcion.setText(item.getDescripcion());

        holder.eliminar.setId(item.getId_asignatura());
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String condicion = "id_asignatura=?";
                String valores[] = {""+item.getId_asignatura()};
                int cant_regs_eliminados = 0;

                OperacionesCRUD instancia = new OperacionesCRUD(view.getContext(),"BDPROGRAMA", null, 2);

                cant_regs_eliminados = instancia.borrarRegistro(Asignatura.Esquema.TABLE_ASIGNATURA,condicion,valores);

                if(cant_regs_eliminados>0){
                    Toast.makeText(view.getContext(), "Asignatura eliminada", Toast.LENGTH_SHORT).show();
                    AdaptadorAsignatura.this.asignaturaDesplegar.remove(holder.getAdapterPosition());
                    AdaptadorAsignatura.this.notifyDataSetChanged();
                }else{
                    Toast.makeText(view.getContext(), "Error al eliminar Asignatura", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return asignaturaDesplegar.size();
    }

    public class AsignaturaHolder extends RecyclerView.ViewHolder {

        public TextView codigo;
        public TextView descripcion;
        public ImageButton eliminar;


        public AsignaturaHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.codigo);
            descripcion = itemView.findViewById(R.id.descripcion);
            eliminar = itemView.findViewById(R.id.eliminar);
        }
    }
}
