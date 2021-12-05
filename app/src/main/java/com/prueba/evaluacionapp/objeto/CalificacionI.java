package com.prueba.evaluacionapp.objeto;

public class CalificacionI {

    private String fecha= "";
    private String calificacion = "";
    private String comentario = "";
    private String id_calificacion = "";

    public CalificacionI(String fecha, String calificacion, String comentario, String id_calificacion) {
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.id_calificacion = id_calificacion;
    }

    public CalificacionI() {
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getId_calificacion() {
        return id_calificacion;
    }

    public void setId_calificacion(String id_calificacion) {
        this.id_calificacion = id_calificacion;
    }
}
