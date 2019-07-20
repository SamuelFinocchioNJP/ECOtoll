/**
 * 
 */
package autostrada;

/**
 * @author Luca
 *
 */

public class Casello {

	private String localita;
	private int km;
	
	public Casello(String localita, int km) {
		this.localita = localita;
		this.km = km;	
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public boolean equals(Object object) {
		if(object instanceof Casello)
			return this.getLocalita().equals(((Casello)object).getLocalita());
		else
			return false;
	}

}
