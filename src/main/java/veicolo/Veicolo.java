package veicolo;

public abstract class Veicolo {

	private String targa;
	private String marca;
	private String modello;
	private int annoImmatricolazione;
	private int assi;
	
	private String classeAmbientale;
	private int inquinamentoAcustico;
	
	//costruttore per Veicoli fino al 2021(veicoli pesanti) e 2026(tutti i veicoli)	
	protected Veicolo (String targa, String marca, String modello, int annoImmatricolazione, int assi) {
		super();
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.annoImmatricolazione = annoImmatricolazione;
		this.assi = assi;
	}

	//costruttore per moto (senza assi) fino al 2026
	protected Veicolo(String targa, String marca, String modello, int annoImmatricolazione) {
		super();
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.annoImmatricolazione = annoImmatricolazione;
	}
	
	//costruttore per veicoli pesanti dal 2021 e veicoli normali dal 2026
	protected Veicolo(String targa, String marca, String modello, int annoImmatricolazione, int assi,
			String classeAmbientale, int inquinamentoAcustico) {
		super();
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.annoImmatricolazione = annoImmatricolazione;
		this.assi = assi;
		this.classeAmbientale = classeAmbientale;
		this.inquinamentoAcustico = inquinamentoAcustico;
	}

	//costruttore per moto dal 2026
	protected Veicolo(String targa, String marca, String modello, int annoImmatricolazione, String classeAmbientale,
			int inquinamentoAcustico) {
		super();
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.annoImmatricolazione = annoImmatricolazione;
		this.classeAmbientale = classeAmbientale;
		this.inquinamentoAcustico = inquinamentoAcustico;
	}

	public String getTarga() {
		return targa;
	}


	public void setTarga(String targa) {
		this.targa = targa;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModello() {
		return modello;
	}


	public void setModello(String modello) {
		this.modello = modello;
	}


	public int getAnnoImmatricolazione() {
		return annoImmatricolazione;
	}


	public void setAnnoImmatricolazione(int annoImmatricolazione) {
		this.annoImmatricolazione = annoImmatricolazione;
	}


	public int getAssi() {
		return assi;
	}


	public void setAssi(int assi) {
		this.assi = assi;
	}


	public String getClasseAmbientale() {
		return classeAmbientale;
	}


	public void setClasseAmbientale(String classeAmbientale) {
		this.classeAmbientale = classeAmbientale;
	}


	public int getInquinamentoAcustico() {
		return inquinamentoAcustico;
	}


	public void setInquinamentoAcustico(int inquinamentoAcustico) {
		this.inquinamentoAcustico = inquinamentoAcustico;
	}
	
	
	
	
}
