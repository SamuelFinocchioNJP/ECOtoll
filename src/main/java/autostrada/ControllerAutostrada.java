package autostrada;

import java.sql.ResultSet;
import java.util.ArrayList;

import Controllers.ControllerInterface;
import utility.Database;

public class ControllerAutostrada implements ControllerInterface{
	@Override
	public  int[] idRetriever() {
		int [] arrayId=null;
		try {
			ResultSet counter = Database.getConnectionStatement().executeQuery ( "COUNT (*) FROM autostrada" );
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT id FROM autostrada" );
			int arraySize=counter.getInt(0);
			arrayId=new int[arraySize];
			for (int i=0; i<arraySize;i++) {
				arrayId[i]=rs.getInt("id");
				rs.next();
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayId;
	}

	
	public ArrayList <Autostrada> getAutostrade() {//retrieve automatico autostrade
		ArrayList <Autostrada> autobahn = new ArrayList<Autostrada>();
		try {	
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT id FROM autostrada" );
			while(rs.next()){
				Autostrada k = new Autostrada(rs.getInt("id"));
				autobahn.add(k);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return autobahn;
	}

	@Override
	public  void deleteRecord(int id) {//Con codice si intende l'Id o il nome dell'autostrada?
		try {
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT * FROM autostrada WHERE id = "+id );
			if(!rs.next()) {
				throw new Exception("The id that you have supplied does not correspond to an existing Autostrada");
			}
			Database.getConnectionStatement().executeUpdate ( "DELETE FROM autostrada WHERE id = "+id );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
