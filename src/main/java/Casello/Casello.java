/**
 * 
 */
package Casello;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;
import Models.ModelInterface;
import utility.Constants;
import utility.Database;

/**
 *	Class Casello
 *	Model for casello relation
 */
public class Casello implements ModelInterface {

	private String localita;
	private int km;
	private int id;
	private int autostradaId;
	
	/**
	 * Casello constructor
	 * Retrieves data from database to populate object
	 * @param id
	 */
	public Casello ( int id ) throws SQLException{
		this.id = id;
		this.retrieve( id );
	}
	
	/**
	 * Casello constructor
	 * Instantiates a new casello object
	 * @param localita
	 * @param km
	 * @param autostradaId
	 */
	public Casello ( String localita, int km, int autostradaId ) {
		this.localita = localita;
		this.km = km;	
		this.autostradaId = autostradaId;
	}
	
	/**
	 * Getter for località
	 * @return localita
	 */
	public String getLocalita() {
		return localita;
	}
	
	/**
	 * Getter for id
	 * @return id
	 */
	public int getId ( ) {
		return this.id;
	}

	/**
	 * Setter for localita
	 * @param localita
	 */
	public void setLocalita(String localita) {
		this.localita = localita;
	}

	/**
	 * Getter for autostradaId
	 * @return autostradaId
	 */
	public int getAutostradaId() {
		return autostradaId;
	}
	
	/**
	 * Setter for autostradaId
	 * @param autostradaId
	 */
	public void setAutostradaId(int autostradaId) {
		this.autostradaId = autostradaId;
	}
	
	/**
	 * Getter for km
	 * @return km
	 */
	public int getKm() {
		return km;
	}

	/**
	 * Setter for km
	 * @param km
	 */
	public void setKm(int km) {
		this.km = km;
	}
	
	/**
	 * @Override
	 * Saves current object to the database
	 */
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

	/**
	 * Populates current object with database informations
	 * @Override
	 */
	public void retrieve ( int id ) throws SQLException {
		ResultSet rs = null;
		try {
			 rs = Database.getConnectionStatement().executeQuery ( "SELECT id, kilometro, locazione, id_autostrada FROM casello WHERE id='" + id + "' LIMIT 1" );
		
			 if ( rs.next() == false ) {
				 throw new SQLException ( Constants.CASELLO_NOT_FOUND_ERROR );
				
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

	/**
	 * Deletes current object from database
	 */
	public void destroy() {
		try {
			Database.getConnectionStatement().executeUpdate ( "DELETE FROM casello WHERE id=" + id );
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}

}
