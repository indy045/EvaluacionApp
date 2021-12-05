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
        //se crean las vistas sin personalizar
        public EstudianteHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiantes, parent, false);
            EstudianteHolder holder = new EstudianteHolder(item);
            return holder;
        }
    //asocia el asignaturaholder con los datos. para personlizar la tarjeta
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

            //seteamos un listener  al eliminar para cuando se haga click
            holder.eliminar.setId(item.getId_usuario());

            holder.eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String condicion = "id_usuario=?"; //se borraran los datos del id_usuario q elija
                    String valores[] = {""+item.getId_usuario()};//damos el id del item seleccionado
                    int cant_regs_eliminados=0;
                    //instanciamos la clase operacionescrud
                    OperacionesCRUD instancia = new OperacionesCRUD(view.getContext(),"BDPROGRAMA", null,2);
                    //borramos los registros pasando los valores de la tablaUsu
                    cant_regs_eliminados = instancia.borrarRegistro(Estudiante.Esquema.TABLE_NAME,condicion,valores);

                    if(cant_regs_eliminados>0){//si cantidad de registro es > 0 pasamos el mensaje
                        Toast.makeText(view.getContext(), "Usuario eliminado", Toast.LENGTH_SHORT).show();
                        AdaptadorEstudiante.this.estudianteDesplegar.remove(holder.getAdapterPosition());
                        AdaptadorEstudiante.this.notifyDataSetChanged();
                    }else{//sino error
                        Toast.makeText(view.getContext(), "Error eliminado usuario", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            holder.editar.setId(item.getId_usuario());
            //seteamos listener para boton editar
            holder.editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //creamos el intent para que nos lleve a la clase edicion
                    Intent editarEstudiante = new Intent(view.getContext(), ActualizarEstudiantes.class);

                    editarEstudiante.putExtra("id", item.getId_usuario());
                    editarEstudiante.putExtra("nombre", item.getNombre());
                    editarEstudiante.putExtra("apePaterno",item.getApePaterno().toString());
                    editarEstudiante.putExtra("apeMaterno",item.getApeMaterno().toString());
                    editarEstudiante.putExtra("email",item.getEmail().toString());
                    editarEstudiante.putExtra("edad",item.getEdad().toString());
                    editarEstudiante.putExtra("direccion",item.getDireccion().toString());
                    editarEstudiante.putExtra("telefono",item.getTelefono().toString());
                    editarEstudiante.putExtra("genero",item.getGenero().toString());
                    //iniciamos la actividad
                    view.getContext().startActivity(editarEstudiante);
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
    //se obtiene el numero de elementos
        @Override
        public int getItemCount () {
            return estudianteDesplegar.size();
        }

    //indicamos todos los elementos que queremos personalizar
        public class EstudianteHolder extends RecyclerView.ViewHolder {
            public ImageView avatar;
            public TextView nombre;
            public TextView email;
            public ImageButton eliminar;
            public ImageButton detalle;
            public ImageButton editar;

        //conectamos la parte logica con la parte grafica
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

