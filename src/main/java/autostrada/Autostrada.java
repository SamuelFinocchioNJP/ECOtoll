/**
 * 
 */
package autostrada;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import Models.ModelInterface;
import pedaggio.IPedaggio;
import pedaggio.PedaggioEco;
import pedaggio.PedaggioKm;
import utility.Constants;
import utility.Database;
import veicolo.Veicolo;

public class Autostrada implements ModelInterface {
	
	private String nome;
	private int id;
	private Map<Integer,Float> tariffaUnitaria;
	private List<Casello> listCasello;
	private int iva;
	private IPedaggio pedaggio;
	private String tipoPedaggio;
		
	/** TODO: Replace old constructor **/
	public Autostrada ( String nome, Map<Integer,Float> tariffaUnitaria, List<Casello> listCasello, String tipoPedaggio, int iva) {
		this.nome = nome;
		this.tariffaUnitaria = tariffaUnitaria;
		this.listCasello = listCasello;
		this.tipoPedaggio = tipoPedaggio;
		this.iva = iva;
		buildPedaggio();
	}
	
	public Autostrada ( String nome, int iva ) {
		this.nome = nome;
		this.iva = iva;
		
		this.save();
	}
	
	public Autostrada ( int id ) {
		this.id = id;
		this.retrieve( id );
	}

	private void buildPedaggio ( ) {
		switch(tipoPedaggio) {
			case Constants.PEDAGGIO_KM:
				pedaggio = new PedaggioKm();
				break;
			case Constants.PEDAGGIO_ECO:
				pedaggio = new PedaggioEco();
				break;
			default:
				throw new IllegalArgumentException("Il tipo di pedaggio non è ammesso"); 		
		}
	}
	
	public String stampaPedaggio(Veicolo veicolo, Casello caselloIngresso, Casello caselloUscita ) {
		return pedaggio.calcoloPedaggio(veicolo, caselloIngresso, caselloUscita, listCasello, tariffaUnitaria, iva);	
	}

	public String getNome() {
		return this.nome;
	}
	
	public int getId ( ) {
		return this.id;
	}
	
	public int getIva( ) {
		return this.iva;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<Integer, Float> getTariffaUnitaria() {
		return tariffaUnitaria;
	}
	
	public void setTariffaUnitaria(Map<Integer, Float> tariffaUnitaria) {
		this.tariffaUnitaria = tariffaUnitaria;
	}

	public List<Casello> getListCasello() {
		return listCasello;
	}

	public void setListCasello(List<Casello> listCasello) {
		this.listCasello = listCasello;
	}

	public IPedaggio getPedaggio() {
		return pedaggio;
	}
	
	public void setPedaggio(IPedaggio pedaggio) {
		this.pedaggio = pedaggio;
	}

	@Override
	public void save ( ) {
		/***
		 * DB Schema for Autostrada:
		 *  id BIGINT AUTO_INCREMENT,
      	 *	nome VARCHAR ( 255 ) NOT NULL,
      	 *  iva NOT NULL,
      	 *  PRIMARY KEY ( id )
		 */
		
		/** Acknowledgement of previous existence of the same instance of autostrada **/
		if ( this.id == 0 ) {
			try {
				ResultSet rs = Database.getConnectionStatement ( ).executeQuery ( "SELECT id FROM autostrada WHERE nome='" + this.getNome ( ) + "' LIMIT 1" );
				
				/** If result set is empty, go for insert query **/
				if ( rs.next() == false ) {
					PreparedStatement preparedStatement = Database.getConnectionObject().prepareStatement ( "INSERT INTO autostrada ( nome, iva )"
							+ " VALUES ('" + this.getNome() + "','" + this.getIva() + "')", Statement.RETURN_GENERATED_KEYS );
					
					if ( preparedStatement.executeUpdate() != 0 ) {
						this.save();
					} else {
						throw new Exception ( "Cant save Autostada exception!" );
					}
				} else {
					/// Result found in query
					this.id = rs.getInt ( "id" );
					System.out.println( "UPDATE autostrada SET iva = '" + this.getIva ( ) + "' WHERE id=" + this.id  );
					Database.getConnectionStatement().executeUpdate ( "UPDATE autostrada SET iva = '" + this.getIva ( ) + "' WHERE id=" + this.id  );
				}
			    
				this.retrieve ( id );
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				Database.getConnectionStatement().executeUpdate ( "UPDATE autostrada SET nome = '" + this.getNome ( ) + "', iva = '" + this.getIva ( ) + "' WHERE id=" + this.id  );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void retrieve ( int id ) {
		
		try {
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT id, nome, iva FROM autostrada WHERE id='" + id + "' LIMIT 1" );
			
			/** If result set is empty, go for insert query **/
			if ( rs.next() == false ) {
				throw new Exception ( "Autostrada not found Exception" );
			} else {
				/// Result found in query
				this.nome  = rs.getString ( "nome" );
				this.iva  = rs.getInt ( "iva" );
				this.id = rs.getInt ( "id" );
			}
		    
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setIva ( int iva ) {
		if ( iva > 0 )
			this.iva = iva;
	}

	public void destroy ( ) {
		try {
			Database.getConnectionStatement().executeUpdate ( "DELETE FROM autostrada WHERE nome='" + this.getNome() + "'" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
