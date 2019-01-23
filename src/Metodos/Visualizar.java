package Metodos;

import Objetos.Actividad;
import Objetos.Gimnasio;
import Objetos.Socio;
import Objetos.Uso;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

/**
 *
 * @author a16alfonsofa
 */
public class Visualizar {
    
    public static void VerGimnasios() {
        
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
    
    public static void VerActividades() {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");
        
        Values val = odb.getValues(new ValuesCriteriaQuery(Actividad.class)
                .field("Tipo")
                .field("NombreActividad")
                .field("Cuota")
                .field("Descuento"));
        
        while (val.hasNext()) {
            ObjectValues ov = (ObjectValues) val.next();
            
            System.out.println("Tipo: " + ov.getByAlias("Tipo")
                    + "\tNombreActividad: \t" + ov.getByAlias("NombreActividad")
                    + "\tCuota: \t" + ov.getByAlias("Cuota")
                    + "\tDescuento: \t" + ov.getByAlias("Descuento"));
        }
        odb.close();
    }
    
    static void VerActividadesDeUnGim(String CIFGim) {
        
        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");
        
        Gimnasio G;
        IQuery query;
        ICriterion crit = Where.equal("CIF", CIFGim);
        int contador = 0;
        
        query = new CriteriaQuery(Gimnasio.class, crit);
        Objects<Gimnasio> list = odb.getObjects(query);
        
        G = list.getFirst();
        do {
            for (Actividad A : G.getActividad()) {
                contador++;
                System.out.println(" " + contador + " - " + A.getTipo() + " // " + A.getNombreActividad());
            }
            G = list.next();
        } while (list.hasNext());
        
        odb.close();
        
    }
    
    static void VerSociosDeUnGim(String CIFGim) {
        
        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");
        
        Gimnasio G;
        IQuery query;
        ICriterion crit = Where.equal("CIF", CIFGim);
        int contador = 0;
        
        query = new CriteriaQuery(Gimnasio.class, crit);
        Objects<Gimnasio> list = odb.getObjects(query);
        
        G = list.getFirst();
        do {
            for (Socio S : G.getSocio()) {
                contador++;
                System.out.println(" " + contador + " - " + S.getCodigo() + " // " + S.getNombre());
            }
            G = list.next();
        } while (list.hasNext());
        
        odb.close();
    }
    
    public static void VerSocios() {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");
        
        Values val = odb.getValues(new ValuesCriteriaQuery(Socio.class)
                .field("Nombre")
                .field("Codigo"));
        
        while (val.hasNext()) {
            ObjectValues ov = (ObjectValues) val.next();
            
            System.out.println("Nombre: " + ov.getByAlias("Nombre")
                    + "\tCodigo: \t" + ov.getByAlias("Codigo"));
        }
        odb.close();
    }
    
    public static void VerUsos() {
        ODB odb = ODBFactory.openClient("localhost", 8000, "Gimnasio");
        
        Values val = odb.getValues(new ValuesCriteriaQuery(Uso.class)
                .field("precioUso")
                .field("NombreActividad")
                .field("fechaUso")
                .field("horaInicioUso")
                .field("horaFinUso"));
        
        while (val.hasNext()) {
            ObjectValues ov = (ObjectValues) val.next();
            
            System.out.println("precioUso: " + ov.getByAlias("precioUso")
                    + "\tNombreActividad: \t" + ov.getByAlias("NombreActividad")
                    + "\tfechaUso: \t" + ov.getByAlias("fechaUso")
                    + "\thoraInicioUso: \t" + ov.getByAlias("horaInicioUso")
                    + "\thoraFinUso: \t" + ov.getByAlias("horaFinUso"));
        }
        odb.close();
    }
    
}
