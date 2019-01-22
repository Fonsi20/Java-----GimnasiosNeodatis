package Objetos;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a16alfonsofa
 */
public class Gimnasio {

    private String CIF;
    private String Nombre;
    private Set<Actividad> actividad;
    private Set<Socio> socio;

    public Gimnasio() {
    }

    public Gimnasio(String CIF, String Nombre) {
        this.CIF = CIF;
        this.Nombre = Nombre;
        this.actividad = new HashSet<>(0);
        this.socio = new HashSet<>(0);
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Set<Actividad> getActividad() {
        return actividad;
    }

    public void setActividad(Set<Actividad> actividad) {
        this.actividad = actividad;
    }

    public Set<Socio> getSocio() {
        return socio;
    }

    public void setSocio(Set<Socio> socio) {
        this.socio = socio;
    }

}
