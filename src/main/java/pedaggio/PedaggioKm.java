/**
 * 
 */
package pedaggio;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import Casello.Casello;
import utility.Constants;
import veicolo.*;

/**
 * @author Luca
 *
 */
public class PedaggioKm implements IPedaggio{
	
	public PedaggioKm() {
		// TODO Auto-generated constructor stub
	}
	
	public String calcoloPedaggio(Veicolo veicolo, Casello caselloIngresso, Casello caselloUscita, List<Casello> listCasello, Map<Integer,Float> tariffaUnitaria, int iva) throws IllegalArgumentException {
		int tipoVeicolo;
		if(listCasello.contains(caselloIngresso) && listCasello.contains(caselloUscita)) { //TODO equals() in casello su String nome
			if(veicolo instanceof ClasseA)
				tipoVeicolo = Constants.CLASSE_A;
			else
				if(veicolo instanceof ClasseB)
					tipoVeicolo = Constants.CLASSE_B;
				else
					if(veicolo instanceof Classe3)
						tipoVeicolo = Constants.CLASSE_3;
					else
						if(veicolo instanceof Classe4)
							tipoVeicolo = Constants.CLASSE_4;
						else
							if(veicolo instanceof Classe5)
								tipoVeicolo = Constants.CLASSE_5;
							else
								throw new IllegalArgumentException("Tipo di veicolo non riconosciuto"); //sollevo eccezione
			
			float mul = this.distanzaPuntiPagamento(caselloIngresso, caselloUscita)*tariffaUnitaria.get(tipoVeicolo);
			float percentage = mul*iva/100;
			mul += percentage;
			DecimalFormat decForm = new DecimalFormat("#.#", new DecimalFormatSymbols());
		    decForm.setRoundingMode(RoundingMode.HALF_EVEN);
			return decForm.format(mul);
		} else
			throw new IllegalArgumentException("Lista dei caselli non sono valida."); //sollevo eccezione	
	}
	
		
	private int distanzaPuntiPagamento(Casello caselloIngresso, Casello caselloUscita){
		if(caselloUscita.getKm() >= caselloIngresso.getKm()) 
			return caselloUscita.getKm()-caselloIngresso.getKm();
		else
			return -(caselloUscita.getKm()-caselloIngresso.getKm());
	}
	
	
}
