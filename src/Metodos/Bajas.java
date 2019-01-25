package Metodos;

import Objetos.Actividad;
import Objetos.Gimnasio;
import Objetos.Socio;
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
public class Bajas {

    public static void Gimnasios() throws IOException {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");
        boolean opc = false;

        do {
            System.out.println("\nBorrar un Gimnasio.\nEscoge a un Gimnasio, escribe su CIF:");
            Visualizar.VerGimnasios();
            System.out.print(" > ");
            String CIF = read.readLine();

            IQuery query = new CriteriaQuery(Gimnasio.class, Where.equal("CIF", CIF));
            Objects<Gimnasio> objects = odb.getObjects(query);

            if (objects.isEmpty()) {
                opc = false;
                System.err.println("'ERROR' - No existe un Gimnasio con ese CIF.");

            } else {
                opc = true;
                Gimnasio G = (Gimnasio) odb.getObjects(query).getFirst();
                odb.delete(G);
            }
        } while (opc != true);
        odb.close();
    }

    public static void Actividades() throws IOException {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");
        boolean opc = false;

        do {
            System.out.println("\nBorrar una Actividad.\nEscoge a una Actividad, escribe su Nombre de Actividad:");
            Visualizar.VerActividades();
            System.out.print(" > ");
            String NombreActividad = read.readLine();

            IQuery query = new CriteriaQuery(Actividad.class, Where.equal("NombreActividad", NombreActividad));
            Objects<Actividad> objects = odb.getObjects(query);

            if (objects.isEmpty()) {
                opc = false;
                System.err.println("'ERROR' - No existe una Actividad con ese Nombre de Actividad.");

            } else {
                opc = true;
                Actividad A = (Actividad) odb.getObjects(query).getFirst();
                odb.delete(A);
            }
        } while (opc != true);
        odb.close();
    }

    public static void Socios() throws IOException {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");
        boolean opc = false;

        do {
            System.out.println("\nBorrar un Socio.\nEscoge a un Socio, escribe su Codigo:");
            Visualizar.VerSocios();
            System.out.print(" > ");
            String codigo = read.readLine();

            IQuery query = new CriteriaQuery(Socio.class, Where.equal("Codigo", codigo));
            Objects<Socio> objects = odb.getObjects(query);

            if (objects.isEmpty()) {
                opc = false;
                System.err.println("'ERROR' - No existe un Socio con ese Codigo.");

            } else {
                opc = true;
                Socio S = (Socio) odb.getObjects(query).getFirst();
                odb.delete(S);
            }
        } while (opc != true);
        odb.close();
    }

}
