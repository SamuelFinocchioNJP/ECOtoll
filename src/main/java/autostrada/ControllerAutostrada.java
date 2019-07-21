package autostrada;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.AbstractMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	public void editTariffa (int idAutostrada,String nomeNuovo, Map <String,Float> tariffeNuove) {
		
		ArrayList <String> keys=new ArrayList();
		ArrayList <Float>  tariffs=new ArrayList();
		for(Map.Entry<String,Float> x: tariffeNuove.entrySet()) {
			keys.add(x.getKey());
			tariffs.add(tariffeNuove.get(x.getKey()));
		}
		try {
			Database.getConnectionStatement().executeUpdate("UPDATE autostrada SET nome= '"+nomeNuovo+"' WHERE id="+idAutostrada);
			int j=0;
			for(String x: keys) {
				Database.getConnectionStatement().executeUpdate("UPDATE tariffa SET prezzo= "+tariffs.get(j++)+"WHERE nome="+keys);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
		
		
	}
	public Map<String,Float>  getAutostradeTariffe(int id) {
		HashMap<String,Float> classToTariffs=new HashMap();
		try {
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT categoria.nome AS nome_categoria,tariffa.prezzo FROM autostrada  INNER JOIN tariffa ON tariffa.id_autostrada=autostrada.id INNER JOIN categoria ON categoria.id=tariffa.id_classe_veicolo WHERE autostrada.id= "+id );
			// 	put(K key, V value)
			while(rs.next()){
				classToTariffs.put(rs.getString("nome_categoria"), rs.getFloat("prezzo"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classToTariffs;
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
