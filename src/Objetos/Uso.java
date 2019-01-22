package Objetos;

import java.util.Date;

/**
 *
 * @author a16alfonsofa
 */
public class Uso {

    private Date fechaUso;
    private Date horaInicioUso;
    private Date horaFinUso;
    private String NombreActividad;
    private float precioUso;

    public Uso() {
    }

    public Uso(Date fechaUso, Date horaInicioUso, Date horaFinUso, String NombreActividad, float precioUso) {
        this.fechaUso = fechaUso;
        this.horaInicioUso = horaInicioUso;
        this.horaFinUso = horaFinUso;
        this.NombreActividad = NombreActividad;
        this.precioUso = precioUso;
    }

    public Date getFechaUso() {
        return fechaUso;
    }

    public void setFechaUso(Date fechaUso) {
        this.fechaUso = fechaUso;
    }

    public Date getHoraInicioUso() {
        return horaInicioUso;
    }

    public void setHoraInicioUso(Date horaInicioUso) {
        this.horaInicioUso = horaInicioUso;
    }

    public Date getHoraFinUso() {
        return horaFinUso;
    }

    public void setHoraFinUso(Date horaFinUso) {
        this.horaFinUso = horaFinUso;
    }

    public String getNombreActividad() {
        return NombreActividad;
    }

    public void setNombreActividad(String NombreActividad) {
        this.NombreActividad = NombreActividad;
    }

    public float getPrecioUso() {
        return precioUso;
    }

    public void setPrecioUso(float precioUso) {
        this.precioUso = precioUso;
    }

}
