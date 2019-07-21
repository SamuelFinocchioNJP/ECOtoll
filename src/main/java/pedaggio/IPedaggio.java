package pedaggio;

import java.util.List;
import java.util.Map;

import Casello.Casello;
import veicolo.Veicolo;

public interface IPedaggio {
	
	public String calcoloPedaggio(Veicolo veicolo, Casello puntoPagamentoIn, Casello puntoPagamentoOut, List<Casello> listCasello, Map<Integer,Float> tariffaUnitaria, int iva);
		
}
