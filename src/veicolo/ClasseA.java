/**
 * 
 */
package Homework1;

/**
 * @author Luca
 *
 */
public class ClasseA extends Veicolo {
	
	private int cilindrata;

	//costruttore fino al 2026
	public ClasseA(String targa, String marca, String modello, int annoImmatricolazione, int assi) {
		// TODO Auto-generated constructor stub
		super(targa,marca,modello,annoImmatricolazione,assi);
	}
	
	//costruttore per moto (senza assi) fino al 2026
	public ClasseA(String targa, String marca, int cilindrata, String modello, int annoImmatricolazione) {
		// TODO Auto-generated constructor stub
		super(targa,marca,modello,annoImmatricolazione);
		this.cilindrata = cilindrata;
	}
	
	//costruttore dal 2026
	public ClasseA(String targa, String marca, String modello, int annoImmatricolazione, int assi, String classeAmbientale, int inquinamentoAcustico) {
		super(targa, marca, modello, annoImmatricolazione, assi, classeAmbientale, inquinamentoAcustico);
	}
	
	//costruttore per moto dal 2026
	public ClasseA(String targa, String marca, String modello, int annoImmatricolazione, String classeAmbientale, int inquinamentoAcustico, int cilindrata) {
		super(targa, marca, modello,  annoImmatricolazione,  classeAmbientale,  inquinamentoAcustico);
		this.cilindrata = cilindrata;
	}

	public int getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}
	
	
	
	

}
