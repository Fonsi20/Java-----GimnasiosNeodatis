package Metodos;

import Objetos.Gimnasio;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

/**
 *
 * @author a16alfonsofa
 */
public class Visualizar {

    static void VerGimnasios() {

        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");

        Values val = odb.getValues(new ValuesCriteriaQuery(Gimnasio.class)
                .field("CIF")
                .field("Nombre"));

        while (val.hasNext()) {
            ObjectValues ov = (ObjectValues) val.next();

            System.out.println("CIF: " + ov.getByAlias("CIF")
                    + "\tNombre: \t" + ov.getByAlias("Nombre"));
        }
        odb.close();

    }

}
