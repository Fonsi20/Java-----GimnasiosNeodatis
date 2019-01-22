package Objetos;

/**
 *
 * @author a16alfonsofa
 */
public class Socio extends Cliente {

    private int CuotaFijaMensual = 30;
    double importeTotal = 0;

    public Socio() {
    }

    public Socio(String Codigo, String Nombre) {
        super(Codigo, Nombre);
    }

    public int getCuotaFijaMensual() {
        return CuotaFijaMensual;
    }

    public void setCuotaFijaMensual(int CuotaFijaMensual) {
        this.CuotaFijaMensual = CuotaFijaMensual;
    }

    @Override
    double importeUsos(String tipo) {

        if (tipo.equals("Libre Horario")) {
            importeTotal = importeTotal + CuotaFijaMensual;
        }
        if (tipo.equals("Actividad Grupal")) {
            importeTotal = importeTotal + (2 * 0.8);
        }
        if (tipo.equals("Alquiler de Espacio")) {
            importeTotal = importeTotal + (4 * 0.7);
        }
        return importeTotal;
    }

}
