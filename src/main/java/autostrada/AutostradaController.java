package autostrada;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Tariffa.Tariffa;
import utility.Database;

/**
 * Autostrada's controller
 */
public class AutostradaController {
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
	 * Method that gets every single Autostrada in the database
	 * @return Returns an ArrayList<Autostrada>
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
	/**
	 * Method to edit an autostrada with the given tariffs
	 * @param idAutostrada: The id of the Autostrada's record to edit
	 * @param nomeNuovo: New name for the highway
	 * @param tariffeNuove: Map of tariffs
	 **/
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
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that adds an Autostrada with the related tariffs
	 * @param nomeNuovo: The Autostrada's name
	 * @param tariffeNuove:A map from the categories (String) to the tariffs (Float)   
	 **/
	public void addAutostradaWithTariff ( String nomeNuovo, Map <String,Float> tariffeNuove ) {
		Autostrada a = new Autostrada ( nomeNuovo, 22 );
		a.save();
		
		editAutostradaWithTariff ( a.getId(), a.getNome(), tariffeNuove );
	}
	
	/**
	 * Fetches the tariffs from a given Autostrada
	 * @param idAutostrada Autostrada's Id passed from the view
	 * @return This method returns A map that maps  categories (String) to  tariffs (Float)  
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
				
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return classToTariffs;
	}
	
	/**
	 * @Override
	 * Method for the delete of a record
	 * @param id: id of the record that needs to be removed 
	 **/
	public void deleteRecord ( int id ) {
		try {
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT * FROM autostrada WHERE id = " + id );
			if(!rs.next()) {
				throw new Exception( "The id that you have supplied does not correspond to an existing Autostrada" );
			}
			Database.getConnectionStatement().executeUpdate ( "DELETE FROM autostrada WHERE id = " + id );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
