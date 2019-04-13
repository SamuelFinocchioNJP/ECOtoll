/**
 * 
 */
package veicolo;

/**
 * @author Luca
 *
 */
public class ClasseB extends Veicolo {

	/**
	 * 
	 */
	
	//Costruttore fino al 2021
	public ClasseB(String targa, String marca, String modello, int annoImmatricolazione, int assi) {
		// TODO Auto-generated constructor stub
		super(targa,marca,modello,annoImmatricolazione,assi);
	}
	
	//Costruttore dal 2021
	public ClasseB(String targa, String marca, String modello, int annoImmatricolazione, int assi, String classeAmbientale, int inquinamentoAcustico) {
		super(targa, marca, modello, annoImmatricolazione, assi, classeAmbientale, inquinamentoAcustico);
	}

}
