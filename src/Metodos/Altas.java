package Metodos;

import Objetos.Actividad;
import Objetos.Gimnasio;
import static gimnasioneodatis.EntradaTeclado.read;
import java.io.IOException;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author a16alfonsofa
 */
public class Altas {

    public static void Gimnasios() throws IOException {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");

        Gimnasio G = null;

        String CIF, Nombre;
        boolean opc = false;

        IQuery query;

        do {
            System.out.print("Nombre del Gimnasio:\n > ");
            Nombre = read.readLine();

            do {
                System.out.print("CIF del Gimnasio:  8 números y una letra al final (00000000A)\n > ");
                CIF = read.readLine();
                opc = Metodos.Validaciones.validarCIF(CIF);
            } while (opc != true);

            query = new CriteriaQuery(Gimnasio.class, Where.equal("CIF", CIF));
            Objects<Gimnasio> objects = odb.getObjects(query);

            if (objects.isEmpty()) {
                opc = true;
                G = new Gimnasio(CIF, Nombre);

            } else {
                opc = false;
                System.err.println("\n\t'ERROR': Ya existe un gimnasio con ese CIF en la BBDD.\n\tVuelva a intentarlo:");
            }
        } while (opc != true);

        odb.store(G);
        odb.close();

    }

    public static void Actividades() throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");

        Actividad A = null;

        String Tipo = null, NombreActividad;
        float Descuento, Cuota;
        boolean opc = false;

        IQuery query;

        do {

            System.out.print("Selecciona un tipo de ACTIVIDAD:\n"
                    + " 1 - Libre Horario\n"
                    + " 2 - Actividad Grupal\n"
                    + " 3 - Alquiler de Espacio\n");

            int result = Metodos.Validaciones.validaMenu(1, 3);

            if (result == 1) {
                Tipo = "Libre Horario";
            }
            if (result == 2) {
                Tipo = "Actividad Grupal";
            }
            if (result == 3) {
                Tipo = "Alquiler de Espacio";
            }

            System.out.print("Nombre de la Actividad:\n > ");
            NombreActividad = read.readLine();

            System.out.print("Cuota de la Actividad:\n > ");
            Cuota = Float.parseFloat(read.readLine());

            System.out.print("Descuento de la Actividad:\n > ");
            Descuento = Float.parseFloat(read.readLine());

            query = new CriteriaQuery(Actividad.class, Where.equal("NombreActividad", NombreActividad));
            Objects<Actividad> objects = odb.getObjects(query);

            if (objects.isEmpty()) {
                opc = true;
                A = new Actividad(Tipo, Cuota, Descuento, NombreActividad);

            } else {
                opc = false;
                System.err.println("\n\t'ERROR': Ya existe esa actividad en la BBDD.\n\tVuelva a intentarlo:");
            }
        } while (opc != true);

        odb.store(A);
        odb.close();
    }

}
