package pedaggio;

import java.util.Map;

import Casello.Casello;
import veicolo.Veicolo;

public interface IPedaggio {
	
	public String calcoloPedaggio(Veicolo veicolo, Casello puntoPagamentoIn, Casello puntoPagamentoOut, Map<String,Float> tariffaUnitaria, int iva);
		
}
