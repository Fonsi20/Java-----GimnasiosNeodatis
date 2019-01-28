package Metodos;

import Objetos.Gimnasio;
import Objetos.Socio;
import Objetos.Uso;
import static gimnasioneodatis.EntradaTeclado.read;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author a16alfonsofa
 */
public class Consultas {

    public static void MAXGimCliente() {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");

        System.out.println("El gimnasio con más clientes registrados es:");
        Objects<Gimnasio> gim = odb.getObjects(Gimnasio.class);

        Gimnasio masgrande = null;
        int max = 0;

        while (gim.hasNext()) {
            Gimnasio g = gim.next();
            if (g.getSocio().size() > max) {
                masgrande = g;
                max = g.getSocio().size();
            }
        }

        System.out.println(masgrande.getNombre() + "    con " + masgrande.getSocio().size() + " Socios");

        System.out.println("Sus clientes son:");
        for (Socio c : masgrande.getSocio()) {
            System.out.println(c.toString());
        }
    }

    public static void GastoClienteFechas() throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        CriteriaQuery listado = odb.criteriaQuery(Socio.class);
        Objects<Socio> cuentas = odb.getObjects(listado);
        listado = odb.criteriaQuery(Socio.class);
        cuentas.addAll(odb.getObjects(listado));

        System.out.println("Introduzca el Codigo del Socio que desea consultar:");

        if (cuentas.isEmpty()) {
            System.out.println("--Error, no existen clientes registrados. Imposible continuar.\n Pulse ENTER para cancelar.");
        } else {
            Visualizar.VerSocios();
            Socio cliente = null;

            do {
                String dni = read.readLine();

                try {
                    ICriterion crit = Where.equal("Codigo", dni);
                    CriteriaQuery listado2 = odb.criteriaQuery(Socio.class, crit);
                    cliente = (Socio) odb.getObjects(listado2).getFirst();
                } catch (ODBRuntimeException e) {
                }

                if (cliente == null) {
                    try {
                        ICriterion crit = Where.equal("Codigo", dni);
                        CriteriaQuery listado2 = odb.criteriaQuery(Socio.class, crit);
                        cliente = (Socio) odb.getObjects(listado2).getFirst();
                    } catch (ODBRuntimeException e) {
                    }
                }

                if (cliente == null) {
                    System.out.println("--El Codigo introducido no se corresponde con ningún cliente.\n Introduzca un DNI válido:");
                }
            } while (cliente == null);

            System.out.println("El cliente seleccionado (" + cliente.getNombre() + ") es de tipo " + cliente.getCodigo());

            System.out.println("Introduzca la FECHA INICIAL de consulta\n"
                    + "formato: dd/mm/yyyy");

            Date fecha = null;
            do {
                try {
                    fecha = sdf.parse(read.readLine());
                } catch (ParseException e) {
                    fecha = null;
                    System.out.println("-- Error, la fecha introducida no respeta el formato. Introduzca una fecha válida.");
                }
            } while (fecha == null);

            System.out.println("Introduzca la FECHA FINAL de consulta\n"
                    + "formato: dd/mm/yyyy");

            Date fecha2 = null;
            do {
                try {
                    fecha2 = sdf.parse(read.readLine());
                    if (fecha2.before(fecha)) {
                        fecha = null;
                        System.out.println("--Error, la fecha final no puede preceder a la inicial");
                    }
                } catch (ParseException e) {
                    fecha = null;
                    System.out.println("-- Error, la fecha introducida no respeta el formato. Introduzca una fecha válida.");
                }
            } while (fecha == null);

            float importeTotal = 0;
            String acum = "";
            int i = 0;

            for (Uso u : cliente.getUso()) {
                if (u.getFechaUso().before(fecha2) && u.getFechaUso().after(fecha)) {
                    importeTotal = importeTotal + u.getPrecioUso();
                }
                acum = acum.concat("-Uso" + u.getNombreActividad() + "(" + u.getFechaUso() + ") ----- " + u.getFechaUso() + "€\n");
                i++;
            }

            if (cliente instanceof Socio) {
                System.out.println("El importe total de este socio para el periodo seleccionado es " + (importeTotal + ((Socio) cliente).getCuotaFijaMensual()) + "€ por su cuota fija (" + ((Socio) cliente).getCuotaFijaMensual() + ") más " + i + " usos registrados en ese periodo.");
            } else if (cliente instanceof Socio) {
                System.out.println("El ususario ha realizado las siguientes actividades en este periodo: \n" + acum);
            }
            odb.close();
        }
    }
}
