package com.prueba.evaluacionapp.sqlite.esquema;

import android.provider.BaseColumns;

public class Asignatura {
    //clase para definir las columnas de tablas
    public static abstract class Esquema implements BaseColumns {

        //definimos las variables. Nombre de la tabla
        public static final String TABLE_ASIGNATURA ="asignatura";

        //definimos los nombres de las columnas
        public static final String ID ="id_asignatura";
        public static final String CODIGO ="codigo";
        public static final String DESCRIPCION ="descripcion";

        //variable en caso de uso de select
        public static final String[] ALLCOLUMNAS = {ID, CODIGO, DESCRIPCION};

        //creamos la tabla segun tipo de dato
        public static final String CREA_TABLA_ASIGNATURA =
                "CREATE TABLE "+TABLE_ASIGNATURA +"("+
                        ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        CODIGO+" TEXT,"+
                        DESCRIPCION+" TEXT)";

        //borramos la tabla
        public  static final
        String BORRAR_TABLA_ASIGNATURA =
                "DROP TABLE IF EXISTS " + TABLE_ASIGNATURA;
    }
}
