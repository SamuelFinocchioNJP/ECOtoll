package Casello;

import java.sql.ResultSet;
import java.util.ArrayList;

import utility.Database;

public class CaselloController {
	
	public ArrayList<Casello> getCaselliFromAutostrada ( int autostradaId ) {
		ResultSet rs = null;
		try {
			rs = Database.getConnectionStatement().executeQuery( "SELECT id FROM casello WHERE id_autostrada = " + autostradaId );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		ArrayList<Casello> caselli = new ArrayList<Casello> ();
		try {
			if ( rs.next() )
				do {
					Casello c = new Casello ( rs.getInt( "id" ) );
					caselli.add ( c );
				} while ( rs.next () );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return caselli;
	}
}
