/**
 * 
 */
package Homework1;

/**
 * @author Luca
 *
 */
public class Classe4 extends Veicolo {

	/**
	 * 
	 */
	//Costruttore fino al 2021
	public Classe4(String targa, String marca, String modello, int annoImmatricolazione, int assi) {
		// TODO Auto-generated constructor stub
		super(targa,marca,modello,annoImmatricolazione,assi);
	}
	
	//Costruttore dal 2021
	public Classe4(String targa, String marca, String modello, int annoImmatricolazione, int assi, String classeAmbientale, int inquinamentoAcustico) {
		super(targa, marca, modello, annoImmatricolazione, assi, classeAmbientale, inquinamentoAcustico);
	}
}
