package Metodos;

import static gimnasioneodatis.EntradaTeclado.read;
import java.io.IOException;

/**
 *
 * @author a16alfonsofa
 */
public class Validaciones {

    public static int validaMenu(int minimo, int maximo) throws IOException {
        int op = 0;
        boolean er;
        do {
            er = false;
            try {
                System.out.print(" > ");
                op = Integer.parseInt(read.readLine());
                if (op < minimo || op > maximo) {
                    er = true;
                    System.err.println("\n\t'ERROR': Formato no válido\n\tVuelva a intentarlo:");
                }
            } catch (NumberFormatException | IOException e) {
                er = true;
                System.err.println("\n\t'ERROR': Formato no válido\n\tVuelva a intentarlo:");
            }
        } while (er == true);
        return op;
    }

    static Boolean validarCIF(String cif) {
        String letraMayuscula = "";
        if (cif.length() != 9 || Character.isLetter(cif.charAt(8)) == false) {
            System.err.println("\n\t'ERROR': Formato no válido\n\tVuelva a intentarlo:");
            return false;
        }

        letraMayuscula = (cif.substring(8)).toUpperCase();

        if (soloNumeros(cif) == true) {
            return true;
        } else {
            System.err.println("\n\t'ERROR': Formato no válido\n\tVuelva a intentarlo:");
            return false;
        }
    }

    public static boolean soloNumeros(String dni) {

        int i, j = 0;
        String numero = "";
        String miDNI = "";
        String[] unoNueve = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (i = 0; i < dni.length() - 1; i++) {
            numero = dni.substring(i, i + 1);

            for (j = 0; j < unoNueve.length; j++) {
                if (numero.equals(unoNueve[j])) {
                    miDNI += unoNueve[j];
                }
            }
        }

        if (miDNI.length() != 8) {
            return false;
        } else {
            return true;
        }
    }
}
