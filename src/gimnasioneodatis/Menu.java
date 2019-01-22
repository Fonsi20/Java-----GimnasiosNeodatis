package gimnasioneodatis;

import Metodos.Altas;
import Metodos.Validaciones;
import java.io.IOException;
import org.neodatis.odb.ODBServer;

/**
 *
 * @author a16alfonsofa
 */
public class Menu {

    static void mainMenu(ODBServer server) throws IOException {

        int op = 0;
        do {
            System.out.println("\n\nGestión de Gimnasios -- Neodatis\n"
                    + "Proyecto propiedad de Alfonso Fernández Álvarez\n"
                    + "--------------------------------------------------------------------------------------------\n"
                    + "Seleccione una opción:\n"
                    + " 1 -\t Altas[+]\n"
                    + " 2 -\t Bajas[+]\n"
                    + " 3 -\t Consultas[+]\n\n"
                    + " 4 -\t Salir\n");
            op = Validaciones.validaMenu(1, 4);
            switch (op) {
                case 1:
                    altasMenu();
                    break;
                case 2:
                    bajasMenu();
                    break;
                case 3:
                    consultasMenu();
                    break;
                case 4:
                    System.out.println(" > Finalizando programa");
                    server.close();
                    System.out.println(" > Servidor Cerrado\n");
                    System.exit(0);
                    break;
            }
        } while (op != 4);
    }

    private static void altasMenu() throws IOException {
        int op = 0;
        do {
            System.out.println("\n\nMenú de Altas:\n"
                    + "--------------------------------------------------------------------------------------------\n"
                    + "Seleccione una opción:\n"
                    + " 1 -\t Gimnasios\n"
                    + " 2 -\t Actividades\n"
                    + " 3 -\t Socios\n\n"
                    + " 4 -\t Usos\n\n"
                    + " 5 -\t Salir\n");
            op = Validaciones.validaMenu(1, 5);
            switch (op) {
                case 1:
                    Altas.Gimnasios();
                    break;
                case 2:
                    Altas.Actividades();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println(" > Volvemos al menú principal");
                    break;
            }
        } while (op != 5);
    }

    private static void bajasMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void consultasMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}