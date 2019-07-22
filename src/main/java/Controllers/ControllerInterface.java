package Controllers;


public interface ControllerInterface {
	/**
	 *Funzione comune che restituisce tutti gli id di un determinato oggetto gestito dal controller che la implementa 
	 **/
	public  int[] idRetriever();
	/**
	 *Metodo per la delete di un record
	 *@param id: id del record da eliminare 
	 **/
	public  void deleteRecord(int id) ;
}
