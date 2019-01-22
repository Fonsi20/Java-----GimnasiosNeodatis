package Objetos;

/**
 *
 * @author a16alfonsofa
 */
public class Actividad {

    private String Tipo;
    private String NombreActividad;
    private float Cuota;
    private float Descuento;

    public Actividad() {
    }

    public Actividad(String Tipo, float Cuota, float Descuento, String NombreActividad) {
        this.Tipo = Tipo;
        this.Cuota = Cuota;
        this.Descuento = Descuento;
        this.NombreActividad = NombreActividad;
    }

    public String getNombreActividad() {
        return NombreActividad;
    }

    public void setNombreActividad(String NombreActividad) {
        this.NombreActividad = NombreActividad;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public float getCuota() {
        return Cuota;
    }

    public void setCuota(float Cuota) {
        this.Cuota = Cuota;
    }

    public float getDescuento() {
        return Descuento;
    }

    public void setDescuento(float Descuento) {
        this.Descuento = Descuento;
    }

}
