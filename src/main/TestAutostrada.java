/**
 * 
 */
package main;

import java.util.*;

import autostrada.*;
import veicolo.*;
import utility.*;

/**
 * @author Luca
 *
 */
public class TestAutostrada {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Integer,Float> tariffaA25 = new HashMap<Integer,Float>();
		List<Casello> listCasello = new ArrayList<Casello>();
		Autostrada autostrada = new Autostrada("A25", tariffaA25, listCasello, Constants.PEDAGGIO_KM, 22);
		Veicolo veicolo = new ClasseA("E7843JK","AUDI","A5",2009,1);
		veicolo.setAssi(2); //per dimostrazione, richiamo metodo definito nella classe abstract
		
		//mappa tariffe
		tariffaA25.put(Constants.CLASSE_A, (float) 0.057);
		tariffaA25.put(Constants.CLASSE_B, (float) 0.067);
		tariffaA25.put(Constants.CLASSE_3, (float) 0.078);
		tariffaA25.put(Constants.CLASSE_4, (float) 0.088);
		tariffaA25.put(Constants.CLASSE_5, (float) 0.099);
		
		//lista caselli
		listCasello.add(new Casello("AQ OVEST",100));
		listCasello.add(new Casello("PE NORD", 250));
		listCasello.add(new Casello("TE OVEST", 180));
		
		Casello casello1 = new Casello("AQ OVEST",100);
		Casello casello2 = new Casello("PE NORD", 250);
		System.out.println("Il pedaggio per il veicolo e': "+ autostrada.stampaPedaggio(veicolo, casello1, casello2)+"€");
	
	}

}
