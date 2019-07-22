package veicolo;

import java.sql.ResultSet;

import utility.Database;
/**
 *Controller dell'oggetto Veicolo 
 **/
public class VeicoloController {
	/**
	 *Ottiene il veicolo data la targa
	 *@param targa: targa con cui fare la ricerca
	 *@return Veicolo ret: Veicolo restituito data la targa
	 **/
	public static Veicolo getVeicolo (String targa ) {
		Veicolo ret=null;
		try {
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT * FROM veicolo where targa LIKE \"%"+targa+"%\"" );
			ret=FactoryConcreteVeicolo.getVeicolo(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ret;
	}
}
	
	

