package com.prueba.evaluacionapp.sqlite.esquema;

import android.provider.BaseColumns;

public class Asignatura {
    public static abstract class Esquema implements BaseColumns {

        public static final String TABLE_ASIGNATURA ="asignatura";
        public static final String ID ="id_asignatura";
        public static final String CODIGO ="codigo";
        public static final String DESCRIPCION ="descripcion";

        public static String[] ALLCOLUMNAS = {ID, CODIGO, DESCRIPCION};

        public static final String CREA_TABLA_ASIGNATURA =
                "CREATE TABLE "+ TABLE_ASIGNATURA + " ( " +
                        ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        CODIGO+" TEXT,"+
                        DESCRIPCION+" TEXT)";

        public  static final
        String BORRAR_TABLA_ASIGNATURA =
                "DROP TABLE IF EXISTS " + TABLE_ASIGNATURA;
    }
}
