/**
 * 
 */
package Casello;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import Models.ModelInterface;
import utility.Database;

public class Casello implements ModelInterface {

	private String localita;
	private int km;
	private int id;
	private int autostradaId;
	
	public Casello ( int id ) {
		this.id = id;
		this.retrieve( id );
	}
	
	public Casello ( String localita, int km, int autostradaId ) {
		this.localita = localita;
		this.km = km;	
		this.autostradaId = autostradaId;
	}

	public String getLocalita() {
		return localita;
	}
	
	public int getId ( ) {
		return this.id;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public int getAutostradaId() {
		return autostradaId;
	}

	public void setAutostradaId(int autostradaId) {
		this.autostradaId = autostradaId;
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
		if ( this.id != 0 ) {
			try {
				 rs = Database.getConnectionStatement().executeQuery ( "SELECT * FROM casello WHERE id='" + this.getId() + "' LIMIT 1" );
			}catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if ( rs.next() == false ) {
					if ( Database.getConnectionStatement().executeUpdate ( "INSERT INTO casello ( locazione, kilometro, id_autostrada )"
							+ " VALUES ('" + this.getLocalita() + "','" + this.getKm() + "', '" + this.getAutostradaId ( ) + "')" ) != 0 )
						this.save();
					else 
						throw new Exception ( "Can not create casello exception" );
				} else {
					/// Result found in query
					int id;
					id = rs.getInt("id");
					//System.out.println( "UPDATE casello SET kilometro = '" + this.getKm ( ) + "' WHERE id=" + id );
				    Database.getConnectionStatement().executeUpdate ( "UPDATE casello SET kilometro = '" + this.getKm( ) + "', locazione = '" + this.getLocalita() + "', id_autostrada = '" + this.autostradaId + "' WHERE id=" + id );
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				PreparedStatement preparedStatement = Database.getConnectionObject().prepareStatement ( "INSERT INTO casello ( locazione, kilometro, id_autostrada )"
						+ " VALUES ('" + this.getLocalita() + "','" + this.getKm() + "', '" + this.getAutostradaId ( ) + "')", Statement.RETURN_GENERATED_KEYS );
				
				preparedStatement.executeUpdate();
				rs = preparedStatement.getGeneratedKeys();
				
				if ( rs.next() ) {
					this.id = rs.getInt(1); 
				}
					
				
				rs.close();
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void retrieve ( int id ) {
		ResultSet rs = null;
		try {
			 rs = Database.getConnectionStatement().executeQuery ( "SELECT id, kilometro, locazione, id_autostrada FROM casello WHERE id='" + id + "' LIMIT 1" );
		
			 if ( rs.next() == false ) {
				 throw new Exception ( "Casello not found Exception" );
			 }else {
				 this.id = rs.getInt( "id" );
				 this.km = rs.getInt( "kilometro" );
				 this.localita = rs.getString( "locazione" );
				 this.autostradaId = rs.getInt("id_autostrada");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		try {
			Database.getConnectionStatement().executeUpdate ( "DELETE FROM casello WHERE id=" + id );
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}

}
