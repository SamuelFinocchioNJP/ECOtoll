package view;

import java.net.URL;
import java.util.ResourceBundle;

import Casello.Casello;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * This is the controller for the tollbooth insert panel
 *
 */
public class TollboothInsertController implements Initializable {

	
	@FXML
	private Button btn_Done;
	
	@FXML
	private TextField txt_Name;
	
	@FXML
	private TextField txt_KM;
	
	private AdminHomeController admincontroller;
	
	private int code;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * This method handles the click on the Done button
	 * Queries the database to add a new tollbooth, 
	 * closes the window and refreshes the rows in the parent view
	 */
	public void onClick()
	{
		Casello casello = new Casello (txt_Name.getText(),Integer.valueOf(txt_KM.getText()),code);
		
		casello.save();
		
		admincontroller.onRefreshClickTollbooths();
		
		Stage stage = (Stage) btn_Done.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * <p>This method is used to give this controller a reference 
	 * to the controlled that created it
	 * </p>
	 * @param controller
	 */
	public void setHomeController(AdminHomeController controller)
	{
		admincontroller = controller;
	}
	
	/**
	 * Setter for the highway code used to query the database
	 * @param code
	 */
	public void setHighwayCode(int code)
	{
		this.code = code;
	}

}
