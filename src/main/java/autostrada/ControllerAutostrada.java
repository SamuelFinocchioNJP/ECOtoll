package autostrada;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Casello.Casello;
import Controllers.ControllerInterface;
import utility.Database;
/**
 * Controllore dell'oggetto Autostrada 
 */
public class ControllerAutostrada implements ControllerInterface {
	/**
	 * @deprecated
	 * Use getAutostrade() instead
	 */
	public  int[] idRetriever ( ) {
		int [] arrayId = null;
		try {
			ResultSet counter = Database.getConnectionStatement().executeQuery ( "COUNT (*) FROM autostrada" );
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT id FROM autostrada" );
			int arraySize = counter.getInt(0);
			arrayId = new int[arraySize];
			for ( int i = 0; i < arraySize; i++ ) {
				arrayId[i]=rs.getInt("id");
				rs.next();
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayId;
	}
	/**
	 * Metodo che restituisce tutte le autostrade presenti nella base di dati
	 * @return Restituisce un ArrayList<Autostrada>
	 */
	public ArrayList <Autostrada> getAutostrade() { 
		ArrayList <Autostrada> autobahn = new ArrayList<Autostrada>();
		try {	
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT id FROM autostrada" );
			while( rs.next() ){
				Autostrada k = new Autostrada( rs.getInt("id") );
				autobahn.add(k);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return autobahn;
	}
	
	public void editAutostradaWithTariff ( int idAutostrada, String nomeNuovo, Map <String,Float> tariffeNuove ) {
		editTariffa ( idAutostrada, nomeNuovo, tariffeNuove );
	}
	
	/** 
	 * @deprecated
	 * Use editAutostradaWithTariff ( int idAutostrada, String nomeNuovo, Map <String,Float> tariffeNuove ) instead
	 **/
	public void editTariffa ( int idAutostrada, String nomeNuovo, Map <String,Float> tariffeNuove ) {
		try {
			/// Modifica nome autostrada
			Autostrada a = new Autostrada ( idAutostrada );
			a.setNome( nomeNuovo );
			a.save();
			
			/// Inserimento tariffe
			Tariffa ta = new Tariffa ( "A", tariffeNuove.get( "A" ), idAutostrada );
			ta.save();
			
			Tariffa tb = new Tariffa ( "B", tariffeNuove.get( "B" ), idAutostrada );
			tb.save();
			
			Tariffa t3 = new Tariffa ( "3", tariffeNuove.get( "3" ), idAutostrada );
			t3.save();
			
			Tariffa t4 = new Tariffa ( "4", tariffeNuove.get( "4" ), idAutostrada );
			t4.save();
			
			Tariffa t5 = new Tariffa ( "5", tariffeNuove.get( "5" ), idAutostrada );
			t5.save();
			
		} catch ( Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *Metodo di aggiunta dell'autostrada con le relative tariffe
	 * @param nomeNuovo: Il nome dell'autostrada
	 * @param tariffeNuove: mappa delle tariffe che mappa il nome delle categorie alle tariffe
	 **/
	public void addAutostradaWithTariff ( String nomeNuovo, Map <String,Float> tariffeNuove ) {
		Autostrada a = new Autostrada ( nomeNuovo, 22 );
		a.save();
		
		editAutostradaWithTariff ( a.getId(), a.getNome(), tariffeNuove );
	}
	/**
	 *Prende le tariffe data l'autostrada
	 * @param idAutostrada id passato da view
	 * @return Restituisce una mappa che ha come chiave i nomi delle categorie(String) e come valori le tariffe(Float) 
	 **/
	public Map<String,Float> getAutostradeTariffe ( int idAutostrada ) {
		HashMap<String,Float> classToTariffs = new HashMap<String, Float>();
		try {
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT classe_veicolo, prezzo FROM tariffa WHERE id_autostrada = '" + idAutostrada + "'" );
            
			if ( rs.next() != false ) {
				do {
					classToTariffs.put ( rs.getString("classe_veicolo"), rs.getFloat("prezzo") );
				} while( rs.next() );
			} else {
				throw new Exception ( "Cant retrieve tariffe from autostrada exception" );
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classToTariffs;
	}
	/**
	 *Metodo per la delete di un record
	 *@param id: id del record da eliminare 
	 **/
	@Override
	public void deleteRecord ( int id ) {
		try {
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT * FROM autostrada WHERE id = "+id );
			if(!rs.next()) {
				throw new Exception("The id that you have supplied does not correspond to an existing Autostrada");
			}
			Database.getConnectionStatement().executeUpdate ( "DELETE FROM autostrada WHERE id = " + id );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}
