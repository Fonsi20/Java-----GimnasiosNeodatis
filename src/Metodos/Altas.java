package Metodos;

import Objetos.Actividad;
import Objetos.Cliente;
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

    public static void Actividades() throws IOException, InterruptedException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");

        Actividad A = null;
        Gimnasio G = null;

        String Tipo = null, NombreActividad;
        float Descuento, Cuota;
        boolean opc = false;

        IQuery query2, query;

        query = new CriteriaQuery(Gimnasio.class);
        Objects<Gimnasio> objects3 = odb.getObjects(query);

        if (objects3.isEmpty()) {

            System.err.println("\n\t'ERROR': No hay Gimnasios dados de alta.\n\tInserte un Gimnasio y vuelva a intentarlo:");
            Thread.sleep(100);

        } else {
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

                    System.out.println();
                    Visualizar.VerGimnasios();
                    System.out.print("A que gimnasio desea asignar esta actividad:\n > ");
                    String CIF = read.readLine();

                    query2 = new CriteriaQuery(Gimnasio.class, Where.equal("CIF", CIF));
                    Objects<Gimnasio> objects2 = odb.getObjects(query2);

                    if (objects2.isEmpty()) {

                        opc = false;
                        System.err.println("\n\t'ERROR':No existe ese gimnasio.\n\tVuelva a intentarlo:\n");
                        Thread.sleep(100);

                    } else {

                        opc = true;
                        G = (Gimnasio) odb.getObjects(query2).getFirst();
                        G.getActividad().add(A);
                        odb.store(G);
                    }
                } else {
                    opc = false;
                    System.err.println("\n\t'ERROR': Ya existe esa actividad en la BBDD.\n\tVuelva a intentarlo:");
                }
            } while (opc != true);

            odb.close();
        }
    }

    public static void Socios() throws InterruptedException, IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");

        Socio S = null;
        Gimnasio G = null;
        String Codigo, Nombre;

        boolean opc = false;

        IQuery query2, query;

        query = new CriteriaQuery(Gimnasio.class);
        Objects<Gimnasio> objects3 = odb.getObjects(query);

        if (objects3.isEmpty()) {

            System.err.println("\n\t'ERROR': No hay Gimnasios dados de alta.\n\tInserte un Gimnasio y vuelva a intentarlo:");
            Thread.sleep(100);

        } else {
            do {

                System.out.print("Codigo del cliente:\n > ");
                Codigo = read.readLine();

                System.out.print("Nombre del cliente:\n > ");
                Nombre = read.readLine();

                query = new CriteriaQuery(Socio.class, Where.equal("Codigo", Codigo));
                Objects<Actividad> objects = odb.getObjects(query);

                if (objects.isEmpty()) {
                    opc = true;
                    S = new Socio(Codigo, Nombre);

                    System.out.println();
                    Visualizar.VerGimnasios();
                    System.out.print("A que gimnasio desea asignar este socio:\n > ");
                    String CIF = read.readLine();

                    query2 = new CriteriaQuery(Gimnasio.class, Where.equal("CIF", CIF));
                    Objects<Gimnasio> objects2 = odb.getObjects(query2);

                    if (objects2.isEmpty()) {

                        opc = false;
                        System.err.println("\n\t'ERROR':No existe ese gimnasio.\n\tVuelva a intentarlo:\n");
                        Thread.sleep(100);

                    } else {

                        opc = true;
                        G = (Gimnasio) odb.getObjects(query2).getFirst();
                        G.getSocio().add(S);
                        odb.store(G);
                    }
                } else {
                    opc = false;
                    System.err.println("\n\t'ERROR': Ya existe esa actividad en la BBDD.\n\tVuelva a intentarlo:");
                }
            } while (opc != true);

            odb.close();
        }
    }

    public static void Uso() throws InterruptedException, IOException, ParseException {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");

        Socio S = null;
        Uso U = null;
        Date fechaUso, horaInicioUso, horaFinUso;
        String NombreActividad, CIFGim, CodClie;
        float precioUso;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfH = new SimpleDateFormat("HH:mm:ss");
        boolean opc = false, opc2 = false;

        IQuery query2, query;

        query = new CriteriaQuery(Socio.class);
        Objects<Gimnasio> objects3 = odb.getObjects(query);

        if (objects3.isEmpty()) {

            System.err.println("\n\t'ERROR': No hay Clientes dados de alta.\n\tInserte un Cliente y vuelva a intentarlo:");
            Thread.sleep(100);

        } else {
            do {

                do {
                    Visualizar.VerGimnasios();
                    System.out.print("Selecciona un Gimnasio:(Copiar un CIF)\n > ");
                    CIFGim = read.readLine();

                    query = new CriteriaQuery(Gimnasio.class, Where.equal("CIF", CIFGim));
                    Objects<Actividad> objects = odb.getObjects(query);

                    if (objects.isEmpty()) {
                        System.err.println("\n\t'ERROR': No existe ningun Gimnasio con ese CIF.\n\tInserte un nuevo Gimnasio y vuelva a intentarlo:");
                        opc2 = false;
                    } else {
                        opc2 = true;
                    }
                } while (opc2 != true);

                do {
                    Visualizar.VerActividadesDeUnGim(CIFGim);
                    System.out.print("Selecciona una Actividad:(Copiar el nombre de actividad)\n > ");
                    NombreActividad = read.readLine();

                    query = new CriteriaQuery(Actividad.class, Where.equal("NombreActividad", NombreActividad));
                    Objects<Actividad> objects = odb.getObjects(query);

                    if (objects.isEmpty()) {
                        System.err.println("\n\t'ERROR': No existe ninguna Actividad con ese nombre en ese Gim.\n\tInserte un nuevo Gimnasio y vuelva a intentarlo:");
                        opc2 = false;
                    } else {
                        opc2 = true;
                    }
                } while (opc2 != true);

                do {
                    Visualizar.VerSociosDeUnGim(CIFGim);
                    System.out.print("Selecciona un Socio del gim:(Copiar el Codigo)\n > ");
                    CodClie = read.readLine();

                    query = new CriteriaQuery(Socio.class, Where.equal("Codigo", CodClie));
                    Objects<Actividad> objects = odb.getObjects(query);

                    if (objects.isEmpty()) {
                        System.err.println("\n\t'ERROR': No existe ningun Gimnasio con ese CIF.\n\tInserte un nuevo Gimnasio y vuelva a intentarlo:");
                        opc2 = false;
                    } else {
                        opc2 = true;
                    }
                } while (opc2 != true);

                System.out.print("Introduce precio de Uso:\n > ");
                precioUso = Float.parseFloat(read.readLine());

                System.out.print("Introduce Fecha del Uso: (dd/MM/yyyy)\n > ");
                String fechaIni = read.readLine();
                fechaUso = sdf.parse(fechaIni);

                System.out.print("Introduce precio de Uso: (HH:mm:ss)\n > ");
                String horaIni = read.readLine();
                horaInicioUso = sdfH.parse(horaIni);

                System.out.print("Introduce precio de Uso: (HH:mm:ss)\n > ");
                String horaFin = read.readLine();
                horaFinUso = sdfH.parse(horaFin);

                query2 = new CriteriaQuery(Socio.class);
                Objects<Socio> objects2 = odb.getObjects(query2);

                U = new Uso(fechaUso, horaInicioUso, horaFinUso, NombreActividad, precioUso);

                S = (Socio) odb.getObjects(query2).getFirst();
                S.getUso().add(U);
                odb.store(S);

            } while (opc2 != true);

            odb.close();
        }
    }
}
