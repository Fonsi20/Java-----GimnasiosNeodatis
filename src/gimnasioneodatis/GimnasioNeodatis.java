package gimnasioneodatis;

import java.io.IOException;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBServer;

/**
 *
 * @author a16alfonsofa
 */
public class GimnasioNeodatis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {

        //Inicio del servidor: 
        System.out.println(" > Iniciando programa:");
        ODBServer server = ODBFactory.openServer(8000);
        server.addBase("Gimnasio", "Gimnasio.neo");
        server.startServer(true);
        System.out.println(" > Servidor Iniciado");
        Thread.sleep(100);

        Menu.mainMenu(server);
    }

}
