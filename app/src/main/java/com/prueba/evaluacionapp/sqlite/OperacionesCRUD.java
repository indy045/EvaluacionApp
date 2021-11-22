package com.prueba.evaluacionapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.prueba.evaluacionapp.sqlite.esquema.Asignatura;
import com.prueba.evaluacionapp.sqlite.esquema.Estudiante;
import com.prueba.evaluacionapp.sqlite.esquema.EstudianteAsignatura;

import java.util.ArrayList;
import java.util.List;

public class OperacionesCRUD extends SQLiteOpenHelper {
    public OperacionesCRUD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Estudiante.Esquema.CREAR_TABLA_USUARIO);
        sqLiteDatabase.execSQL(Asignatura.Esquema.CREA_TABLA_ASIGNATURA);
        sqLiteDatabase.execSQL(EstudianteAsignatura.Esquema.CREA_TABLA_USER_ASIGNATURA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(EstudianteAsignatura.Esquema.BORRAR_TABLA_USUARIO_ASIGNATURA);
        sqLiteDatabase.execSQL(Estudiante.Esquema.BORRAR_TABLA_USUARIO);
        sqLiteDatabase.execSQL(Asignatura.Esquema.BORRAR_TABLA_ASIGNATURA);
        onCreate(sqLiteDatabase);

    }

    public long insertarTabla(ContentValues columnas_valores_insertar, String nombre_tabla) {

        long id_reg_insertado = 0;
        try {
            SQLiteDatabase baseDatos = this.getWritableDatabase();

            id_reg_insertado = baseDatos.insert(nombre_tabla, null, columnas_valores_insertar);

        } catch (Exception e) {

            System.out.println("error en metodo insertar : " + e.getMessage());

        }
        return id_reg_insertado;
    }

    public List<ContentValues> obtenerDatos(String columnasObtener[], String columnasFiltro,
                                            String valoresFiltro[], String nomTabla) {
        Cursor registroret = null;
        List<ContentValues> ListaRegistro = new ArrayList<ContentValues>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            registroret = db.query(nomTabla,
                    columnasObtener,
                    columnasFiltro,
                    valoresFiltro,
                    null,
                    null,
                    null);
            if (null != registroret) {

                registroret.moveToFirst();
                while (registroret.isAfterLast() == false) {
                    ContentValues auxiliar = new ContentValues();
                    for (int i = 0; i < registroret.getColumnCount(); i++) {
                        auxiliar.put(registroret.getColumnName(i), registroret.getString(i));
                    }
                    ListaRegistro.add(auxiliar);

                    registroret.moveToNext();

                }
                registroret.close();
            }

        } catch (Exception e) {
            System.out.println("Error metodo obtener datos");
        }
        return ListaRegistro;
    }

    //borrar registro
    public int borrarRegistro(String nombre_tabla, String condicion, String[] val_condicion) {
        int registro_eliminados = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            registro_eliminados = db.delete(nombre_tabla, condicion, val_condicion);
        } catch (Exception e) {
            System.out.println("Error metodo borrar : " + e.getMessage());
        }
        return registro_eliminados;
    }

    //actualizar registro
    public int actualizarRegistro(ContentValues columna_valores, String condicion, String[] val_condicion, String nombre_tabla) {
        int cantidad_actualizados = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            cantidad_actualizados = db.update(nombre_tabla, columna_valores, condicion, val_condicion);
        } catch (Exception e) {
            System.out.println("Error metodo actualizar registro : " + e.getMessage());
        }
        return cantidad_actualizados;
    }
}
