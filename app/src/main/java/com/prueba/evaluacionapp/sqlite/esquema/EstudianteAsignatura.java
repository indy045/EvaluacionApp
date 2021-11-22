package com.prueba.evaluacionapp.sqlite.esquema;

import android.provider.BaseColumns;

public class EstudianteAsignatura {
    public static abstract class Esquema implements BaseColumns {

        public static final String TABLE_USER_ASIG = "usuario_asignatura";

        public static final String ID = "id_user_asig";
        public static final String ID_USUARIO = "id_usuario";
        public static final String ID_ASIGNATURA = "id_asignatura";

        public static String[] ALLCOLUMNAS = {ID, ID_USUARIO, ID_ASIGNATURA};

        public static final String CREA_TABLA_USER_ASIGNATURA =
                "CREATE TABLE " + TABLE_USER_ASIG + "("+
                        ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        ID_USUARIO+" INTEGER,"+
                        ID_ASIGNATURA+" INTEGER)";

        public static final
        String BORRAR_TABLA_USUARIO_ASIGNATURA =
                "DROP TABLE IF EXISTS " + TABLE_USER_ASIG;

    }
}
