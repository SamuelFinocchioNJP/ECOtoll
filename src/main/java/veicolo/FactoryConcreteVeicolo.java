package veicolo;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *Abstract factory used for generating an object of type "Veicolo"
 **/
public class FactoryConcreteVeicolo {
	public FactoryConcreteVeicolo() {}
	/***
	 *Method that generates a Veicolo of a certain type trough the abstract class Veicolo; this method also populates the instance generated
	 *@param ResultSet ogg: This method takes a result set of a single record that describes a Veicolo and uses the data for generating the object
	 *@throws Exception
	 *@return  Veicolo returnVal: It returns a generic Veicolo that has a Veicolo of a certain category inside
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
