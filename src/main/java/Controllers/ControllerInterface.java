package Controllers;


public interface ControllerInterface {
	/**
	 *Common method used to retrieve all the identifiers of the objects related to the controller that implements this interface from the database
	 *@return It returns an array containing the identifiers 
	 **/
	public  int[] idRetriever();
	/**
	 *Method used for the delete of a single record
	 *@param id: id of the record that needs to be deleted 
	 **/
	public  void deleteRecord(int id) ;
}
