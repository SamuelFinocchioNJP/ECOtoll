/**
 * 
 */
package autostrada;

import java.util.*;

import pedaggio.IPedaggio;
import pedaggio.PedaggioEco;
import pedaggio.PedaggioKm;
import utility.Constants;
import veicolo.Veicolo;



/**
 * @author Luca
 *
 */

public class Autostrada {

	private String nome;
	private Map<Integer,Float> tariffaUnitaria;
	private List<Casello> listCasello;
	private int iva;
	private IPedaggio pedaggio;
	private String tipoPedaggio;
	
		
	public Autostrada(String nome, Map<Integer,Float> tariffaUnitaria, List<Casello> listCasello, String tipoPedaggio, int iva) {
		// TODO Auto-generated constructor stub
		this.nome = nome;
		this.tariffaUnitaria = tariffaUnitaria;
		this.listCasello = listCasello;
		this.tipoPedaggio = tipoPedaggio;
		this.iva = iva;
		buildPedaggio();
	}
	
	private void buildPedaggio() {
		switch(tipoPedaggio) {
			case Constants.PEDAGGIO_KM:
				pedaggio = new PedaggioKm();
				break;
			case Constants.PEDAGGIO_ECO:
				pedaggio = new PedaggioEco();
				break;
			default:
				System.out.println("Il tipo di pedaggio non è ammesso"); //throw exception		
		}
	}
	
	
	public String stampaPedaggio(Veicolo veicolo, Casello caselloIngresso, Casello caselloUscita ) {
	
			return pedaggio.calcoloPedaggio(veicolo, caselloIngresso, caselloUscita, listCasello, tariffaUnitaria, iva);
			
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<Integer, Float> getTariffaUnitaria() {
		return tariffaUnitaria;
	}
	
	public void setTariffaUnitaria(Map<Integer, Float> tariffaUnitaria) {
		this.tariffaUnitaria = tariffaUnitaria;
	}

	public List<Casello> getListCasello() {
		return listCasello;
	}

	public void setListCasello(List<Casello> listCasello) {
		this.listCasello = listCasello;
	}

	public IPedaggio getPedaggio() {
		return pedaggio;
	}
	
	public void setPedaggio(IPedaggio pedaggio) {
		this.pedaggio = pedaggio;
	}
	
	

}
