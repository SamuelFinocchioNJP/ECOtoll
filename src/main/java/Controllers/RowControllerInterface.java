package Controllers;

/**
 * All row controllers must implement these methods
 *
 */
public interface RowControllerInterface {

	/**
	 * Method used to set the view labels
	 * @param strings
	 */
	public void setLabels(String...strings);
	
	/**
	 * Method used to decide the mouse entering event animation
	 */
	public void onMouseEntered();
	
	/**
	 * Method used to decide the mouse exiting event animation
	 */
	public void onMouseExited();
}
