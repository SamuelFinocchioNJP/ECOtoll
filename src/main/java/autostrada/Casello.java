/**
 * 
 */
package autostrada;

import java.sql.ResultSet;
import Models.ModelInterface;
import utility.Database;

public class Casello implements ModelInterface {

	private String localita;
	private int km;
	
	public Casello(String localita, int km) {
		this.localita = localita;
		this.km = km;	
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public boolean equals(Object object) {
		if(object instanceof Casello)
			return this.getLocalita().equals(((Casello)object).getLocalita());
		else
			return false;
	}
	/** (
      id BIGINT AUTO_INCREMENT,
      locazione VARCHAR ( 255 ) NOT NULL,
      kilometro INT NOT NULL,
      PRIMARY KEY ( id )
	);*/
	@Override
	public void save() {
		ResultSet rs = null;
		try {
			 rs = Database.getConnectionStatement().executeQuery ( "SELECT id FROM casello WHERE locazione='" + this.getLocalita() + "' LIMIT 1" );
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if ( rs.next() == false ) {
					Database.getConnectionStatement().executeUpdate ( "INSERT INTO casello ( locazione, kilometro )"
							+ " VALUES ('" + this.getLocalita() + "','" + this.getKm() + "')" );
			} else {
				/// Result found in query
				int id;
				id = rs.getInt("id");
				//System.out.println( "UPDATE casello SET kilometro = '" + this.getKm ( ) + "' WHERE id=" + id );
			    Database.getConnectionStatement().executeUpdate ( "UPDATE casello SET kilometro = '" + this.getKm( ) + "' WHERE id=" + id );
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	@Override
	public void retrieve(int id) {
		ResultSet rs = null;
		try {
			 rs = Database.getConnectionStatement().executeQuery ( "SELECT id FROM casello WHERE locazione='" + this.getLocalita() + "' LIMIT 1" );
		
			 if ( rs.next() == false ) {
				 throw new Exception ( "Casello not found Exception" );
			 }else {
				 this.km = rs.getInt("kilometro");
				 this.localita = rs.getString("locazione");
			 }
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
