package com.prueba.evaluacionapp.adaptador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prueba.evaluacionapp.R;
import com.prueba.evaluacionapp.activity.ActualizarEstudiantes;
import com.prueba.evaluacionapp.activity.ListaEstudianteAsignatura;
import com.prueba.evaluacionapp.objeto.EstudiantesI;
import com.prueba.evaluacionapp.sqlite.OperacionesCRUD;
import com.prueba.evaluacionapp.sqlite.esquema.Estudiante;

import java.util.ArrayList;

public class AdaptadorEstudiante extends RecyclerView.Adapter<AdaptadorEstudiante.EstudianteHolder> {
    private ArrayList<EstudiantesI> estudianteDesplegar;

    public AdaptadorEstudiante(ArrayList<EstudiantesI> estudianteDesplegarIn) {
        estudianteDesplegar = estudianteDesplegarIn;
    }

        @NonNull
        @Override
        public EstudianteHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiantes, parent, false);
            EstudianteHolder holder = new EstudianteHolder(item);
            return holder;
        }

        @Override
        public void onBindViewHolder (@NonNull EstudianteHolder holder,int position){

            EstudiantesI item = estudianteDesplegar.get(position);
            if (item.getGenero().toUpperCase().equals("MASCULINO"))
                holder.avatar.setImageResource(R.drawable.avatar_mas);
            else
                holder.avatar.setImageResource(R.drawable.avatar_fem);

            holder.nombre.setText(item.getId_usuario()+": "
                    +item.getNombre()+ " "
                    +item.getApePaterno()+" "
                    +item.getApeMaterno());

            holder.email.setText(item.getEmail());
            holder.detalle.setId(item.getId_usuario());

            //boton eliminar
            holder.eliminar.setId(item.getId_usuario());

            holder.eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String condicion = "id_usuario=?";
                    String valores[] = {""+item.getId_usuario()};
                    int cant_regs_eliminados=0;

                    OperacionesCRUD instancia = new OperacionesCRUD(view.getContext(),"BDPROGRAMA", null,2);

                    cant_regs_eliminados = instancia.borrarRegistro(Estudiante.Esquema.TABLE_NAME,condicion,valores);

                    if(cant_regs_eliminados>0){
                        Toast.makeText(view.getContext(), "Usuario eliminado", Toast.LENGTH_SHORT).show();
                        AdaptadorEstudiante.this.estudianteDesplegar.remove(holder.getAdapterPosition());
                        AdaptadorEstudiante.this.notifyDataSetChanged();
                    }else{
                        Toast.makeText(view.getContext(), "Error eliminado usuario", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            holder.editar.setId(item.getId_usuario());
            holder.editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editarUsuario = new Intent(view.getContext(), ActualizarEstudiantes.class);

                    editarUsuario.putExtra("id", item.getId_usuario());
                    editarUsuario.putExtra("nombre",item.getNombre().toString());
                    editarUsuario.putExtra("apePaterno",item.getApePaterno().toString());
                    editarUsuario.putExtra("apeMaterno",item.getApeMaterno().toString());
                    editarUsuario.putExtra("email",item.getEmail().toString());
                    editarUsuario.putExtra("edad",item.getEdad().toString());
                    editarUsuario.putExtra("direccion",item.getDireccion().toString());
                    editarUsuario.putExtra("telefono",item.getTelefono().toString());
                    editarUsuario.putExtra("genero",item.getGenero().toString());

                    view.getContext().startActivity(editarUsuario);

                }
            });

            holder.detalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent userAsignatura = new Intent(view.getContext(), ListaEstudianteAsignatura.class);

                    userAsignatura.putExtra("id_usuario", item.getId_usuario());

                    view.getContext().startActivity(userAsignatura);

                }
            });
        }

        @Override
        public int getItemCount () {
            return estudianteDesplegar.size();
        }

        public class EstudianteHolder extends RecyclerView.ViewHolder {
            public ImageView avatar;
            public TextView nombre;
            public TextView email;
            public ImageButton eliminar;
            public ImageButton detalle;
            public ImageButton editar;

            public EstudianteHolder(@NonNull View itemView) {
                super(itemView);

                avatar = itemView.findViewById(R.id.avatar);
                nombre = itemView.findViewById(R.id.nombre);
                email = itemView.findViewById(R.id.email);
                eliminar = itemView.findViewById(R.id.eliminar);
                detalle = itemView.findViewById(R.id.detalle);
                editar = itemView.findViewById(R.id.editar);
            }
        }
    }

