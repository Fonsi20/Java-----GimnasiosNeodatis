package Objetos;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a16alfonsofa
 */
public abstract class Cliente {

    private String Codigo;
    private String Nombre;
    private Set<Uso> uso;

    public Cliente() {
    }

    public Cliente(String Codigo, String Nombre) {
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.uso = new HashSet<>(0);
    }

    abstract double importeUsos(String Tipo);

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Set<Uso> getUso() {
        return uso;
    }

    public void setUso(Set<Uso> uso) {
        this.uso = uso;
    }

}
