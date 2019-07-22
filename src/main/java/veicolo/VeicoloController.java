package veicolo;

import java.sql.ResultSet;

import utility.Database;
/**
 *Veicolo's Controller
 **/
public class VeicoloController {
	/**
	 *Get a vehicle from a given license plate
	 *@param targa: license plate that is used for the researcj
	 *@return Veicolo ret: object of type Veicolo (Vehicle) 
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
	
	

