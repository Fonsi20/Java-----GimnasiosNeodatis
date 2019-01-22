package Objetos;

/**
 *
 * @author a16alfonsofa
 */
public class Usuario extends Cliente {

    private double importeTotal = 0;

    public Usuario() {
    }

    public Usuario(String Codigo, String Nombre) {
        super(Codigo, Nombre);
    }

    @Override
    double importeUsos(String tipo) {

        if (tipo.equals("Libre Horario")) {
            importeTotal = importeTotal + 1;
        }
        if (tipo.equals("Actividad Grupal")) {
            importeTotal = importeTotal + 2;
        }
        if (tipo.equals("Alquiler de Espacio")) {
            importeTotal = importeTotal + 4;
        }
        return importeTotal;
    }

}
