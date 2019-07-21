package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HighwaysEditController implements Initializable {

	@FXML
	private Button btn_Done;
	
	@FXML
	private Label lbl_Highway;
	
	@FXML
	private TextField txt_Name;
	
	@FXML
	private VBox scroll_vehicle_classes;
	
	private AdminHomeController admincontroller;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	public void onClick()
	{
		//query di inserimento
	}
	
	public void setHomeController(AdminHomeController controller)
	{
		admincontroller = controller;
	}

}
