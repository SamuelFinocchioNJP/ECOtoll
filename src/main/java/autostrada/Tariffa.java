/**
 * 
 */
package autostrada;

import java.sql.ResultSet;
import Models.ModelInterface;
import utility.Database;

public class Tariffa implements ModelInterface {
	private int id;
	private String classeVeicolo;
	private double prezzo;
	private int idAutostrada;
	
	public String getClasseVeicolo() {
		return classeVeicolo;
	}

	public void setClasseVeicolo( String classeVeicolo ) {
		this.classeVeicolo = classeVeicolo;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getIdAutostrada() {
		return idAutostrada;
	}

	public void setIdAutostrada(int idAutostrada) {
		this.idAutostrada = idAutostrada;
	}

	public int getId() {
		return id;
	}
		
	/** TODO: Replace old constructor **/
	public Tariffa ( String classeVeicolo, double prezzo, int idAutostrada ) {
		this.classeVeicolo = classeVeicolo;
		this.prezzo = prezzo;
		this.idAutostrada = idAutostrada;
	}

	@Override
	public void save ( ) {
		/***
		 * DB Schema for Tariffa:
		 *  id BIGINT AUTO_INCREMENT,
      		classe_veicolo ENUM( 'A', 'B', '3', '4', '5' ) NOT NULL,
      		prezzo DECIMAL ( 9, 2 ) NOT NULL,
      		PRIMARY KEY ( id ),
			id_autostrada BIGINT,
		 */
		
		/** Acknowledgement of previous existence of the same instance of tariffa **/
		try {
			/// Sostituizione precedente tariffa con nuova previa eliminazione
			Database.getConnectionStatement ( ).executeUpdate ( "DELETE FROM tariffa WHERE id_autostrada=" + "'" + this.idAutostrada + "' AND classe_veicolo =" + "'" + this.classeVeicolo + "'" );
			
			/** If result set is empty, go for insert query **/
			Database.getConnectionStatement().executeUpdate ("INSERT INTO tariffa ( id_autostrada, classe_veicolo, prezzo ) "
					+ "VALUES ('" + this.idAutostrada + "','" + this.classeVeicolo + "', '" + this.prezzo + "')");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void retrieve ( int id ) {
		
		try {
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT id, id_autostrada, classe_veicolo, prezzo FROM tariffa WHERE id='" + id + "' LIMIT 1" );
			
			/** If result set is empty, go for insert query **/
			if ( rs.next() == false ) {
				throw new Exception ( "Tariffa not found Exception" );
			} else {
				/// Result found in query
				this.idAutostrada = rs.getInt ( "id_autostrada" );
				this.classeVeicolo = rs.getString ( "classe_veicolo" );
				this.prezzo = rs.getInt("prezzo");
				this.id = rs.getInt ( "id" );
			}
		    
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy ( ) {
		try {
			Database.getConnectionStatement().executeUpdate ( "DELETE FROM tariffa WHERE id='" + this.getId() + "'" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
