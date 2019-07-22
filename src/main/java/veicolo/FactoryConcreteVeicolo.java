package veicolo;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *Abstract factory per la generazione di un oggetto veicolo 
 **/
public class FactoryConcreteVeicolo {
	public FactoryConcreteVeicolo() {}
	/***
	 *Metodo generatore di veicoli con categoria;Il veicolo verrà popolato
	 *@param ResultSet ogg: Il metodo prende in input un ResultSet generato da una query per ottenere un singolo veicolo.Tale query sarà sfruttata per popolare l'oggetto
	 *@throws Exception
	 *@return  Veicolo returnVal: Restituisce un veicolo astratto che però è stato istanziato come un veicolo figlio
	 **/
	public static Veicolo getVeicolo(ResultSet ogg) throws Exception {
		String type=null;
		Veicolo returnVal=null;
		try {
			ogg.next();
			type = ogg.getString("classe_veicolo");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(type) {
		case "A":
			returnVal=new ClasseA();
			break;
		case "B":
			returnVal=new ClasseB();
			break;
		case "3":
			returnVal=new Classe3();
			break;
		case "4":
			returnVal=new Classe4();
			break;
		case "5":
			returnVal=new Classe5();
			break;
		default: throw new Exception("No type of vehicle exception");
	}
	
		returnVal.setTarga(ogg.getString("targa"));
		returnVal.setModello(ogg.getString("modello"));
		returnVal.setAssi(ogg.getInt("assi"));
		returnVal.setClasseAmbientale(ogg.getString("classe_ambientale"));
		returnVal.setAnnoImmatricolazione(ogg.getInt("anno_immatricolazione"));
		returnVal.setClasseAmbientale(ogg.getString("classe_ambientale"));
		returnVal.setInquinamentoAcustico(ogg.getInt("inquinamentoAcustico"));
		
		return returnVal;
}}
