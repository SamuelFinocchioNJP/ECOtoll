/**
 * 
 */
package autostrada;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import Models.ModelInterface;
import Settings.Config;
import pedaggio.IPedaggio;
import pedaggio.PedaggioEco;
import pedaggio.PedaggioKm;
import utility.Constants;
import utility.Database;
import veicolo.Veicolo;



/**
 * @author Luca
 *
 */

public class Autostrada implements ModelInterface {

	private String nome;
	private Map<Integer,Float> tariffaUnitaria;
	private List<Casello> listCasello;
	private int iva;
	private IPedaggio pedaggio;
	private String tipoPedaggio;
	
		
	public Autostrada ( String nome, Map<Integer,Float> tariffaUnitaria, List<Casello> listCasello, String tipoPedaggio, int iva) {
		this.nome = nome;
		this.tariffaUnitaria = tariffaUnitaria;
		this.listCasello = listCasello;
		this.tipoPedaggio = tipoPedaggio;
		this.iva = iva;
		buildPedaggio();
	}
	
	private void buildPedaggio() {
		switch(tipoPedaggio) {
			case Constants.PEDAGGIO_KM:
				pedaggio = new PedaggioKm();
				break;
			case Constants.PEDAGGIO_ECO:
				pedaggio = new PedaggioEco();
				break;
			default:
				throw new IllegalArgumentException("Il tipo di pedaggio non � ammesso"); 		
		}
	}
	
	public String stampaPedaggio(Veicolo veicolo, Casello caselloIngresso, Casello caselloUscita ) {
		return pedaggio.calcoloPedaggio(veicolo, caselloIngresso, caselloUscita, listCasello, tariffaUnitaria, iva);	
	}

	public String getNome() {
		return this.nome;
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
		
		try {
			ResultSet rs = Database.getConnection().executeQuery ( "SELECT id FROM autostrada WHERE nome='" + this.getNome ( ) + "' LIMIT 1" );
			
			/** If result set is empty, go for insert query **/
			if ( rs.next() == false ) {
				Database.getConnection().executeUpdate ( "INSERT INTO autostrada ( nome, iva )"
						+ " VALUES ('" + this.getNome() + "','" + this.getIva() + "')" );
			} else {
				
				/// Result found in query
				int id  = rs.getInt("id");
				
				System.out.println( "UPDATE autostrada SET iva = '" + this.getIva ( ) + "' WHERE id=" + id );
				Database.getConnection().executeUpdate ( "UPDATE autostrada SET iva = '" + this.getIva ( ) + "' WHERE id=" + id );
			}
		    
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void retrieve ( int id ) {
		try {
			ResultSet rs = Database.getConnection().executeQuery ( "SELECT nome, iva FROM autostrada WHERE id='" + id + "' LIMIT 1" );
			
			/** If result set is empty, go for insert query **/
			if ( rs.next() == false ) {
				throw new Exception ( "Autostrada not found Exception" );
			} else {
				/// Result found in query
				this.nome  = rs.getString("nome");
				this.iva  = rs.getInt("iva");
			}
		    
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setIva(int iva ) {
		if ( iva > 0 )
			this.iva = iva;
	}	
}
