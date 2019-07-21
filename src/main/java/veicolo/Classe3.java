/**
 * 
 */
package veicolo;

/**
 * @author Luca
 *
 */
public class Classe3 extends Veicolo {

	/**
	 * 
	 */
	protected Classe3() {}
	public String getType() {return "3";}
	//Costruttore fino al 2021
	public Classe3(String targa, String marca, String modello, int annoImmatricolazione, int assi) {
		super(targa,marca,modello,annoImmatricolazione,assi);
		//if(annoImmatricolazione > 2021) throw new IllegalArgumentException("Costruttore errato per questo anno di immatricolazione");
	}
	
	//Costruttore dal 2021
	public Classe3(String targa, String marca, String modello, int annoImmatricolazione, int assi, String classeAmbientale, int inquinamentoAcustico) {
		super(targa, marca, modello, annoImmatricolazione, assi, classeAmbientale, inquinamentoAcustico);
		//if(annoImmatricolazione <= 2021) throw new IllegalArgumentException("Costruttore errato per questo anno di immatricolazione");
	}

}
