/**
 * 
 */
package veicolo;

/**
 * @author Luca
 *
 */
public class ClasseA extends Veicolo {
	
	private int cilindrata;

	//costruttore fino al 2026
	public ClasseA(String targa, String marca, String modello, int annoImmatricolazione, int assi) throws IllegalArgumentException{
		super(targa,marca,modello,annoImmatricolazione,assi);
		//if(annoImmatricolazione > 2026) throw new IllegalArgumentException("Costruttore errato per questo anno di immatricolazione");
	}
	
	//costruttore per moto (senza assi) fino al 2026
	public ClasseA(String targa, String marca, int cilindrata, String modello, int annoImmatricolazione) {
		super(targa,marca,modello,annoImmatricolazione);
		this.cilindrata = cilindrata;
		//if(annoImmatricolazione > 2026) throw new IllegalArgumentException("Costruttore errato per questo anno di immatricolazione");
	}
	
	//costruttore dal 2026
	public ClasseA(String targa, String marca, String modello, int annoImmatricolazione, int assi, String classeAmbientale, int inquinamentoAcustico) {
		super(targa, marca, modello, annoImmatricolazione, assi, classeAmbientale, inquinamentoAcustico);
		//if(annoImmatricolazione <= 2026) throw new IllegalArgumentException("Costruttore errato per questo anno di immatricolazione");
	}
	
	//costruttore per moto dal 2026
	public ClasseA(String targa, String marca, String modello, int annoImmatricolazione, String classeAmbientale, int inquinamentoAcustico, int cilindrata) {
		super(targa, marca, modello,  annoImmatricolazione,  classeAmbientale,  inquinamentoAcustico);
		this.cilindrata = cilindrata;
		//if(annoImmatricolazione <= 2026) throw new IllegalArgumentException("Costruttore errato per questo anno di immatricolazione");
	}

	public int getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}
	
	
	
	

}
