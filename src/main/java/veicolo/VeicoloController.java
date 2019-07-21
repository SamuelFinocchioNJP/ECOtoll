package veicolo;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.Database;

public class VeicoloController {

	public static Veicolo getVeicolo (String targa ) {
		Veicolo ret=null;
		try {
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT * FROM veicolo where targa='"+targa+"'" );
			ret=FactoryConcreteVeicolo.getVeicolo(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ret;
	}
}
	
	

