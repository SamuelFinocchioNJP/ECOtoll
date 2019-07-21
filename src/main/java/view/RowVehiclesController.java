package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/*
 * @author: Pietro
 * 
 * */

public class RowVehiclesController implements Initializable, RowControllerInterface {

	@FXML
	private Label lbl_Name;
	
	@FXML
	private Label lbl_Examples;
	
	@FXML
	private Button btn_Edit;
	
	@FXML
	private Button btn_Delete;
	
	@FXML
	private HBox box_Row;
	
	private String id_Vehicle;
	
	private AdminHomeController admincontroller;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	

	@Override
	public void onEditClick() {
		//TODO: Da decidere, apriamo un pannello per editare?

	}

	@Override
	public void onDeleteClick() {
		//TODO: Chiediamo una conferma?

	}

	public void setID(String id)
	{
		this.id_Vehicle = id;
	}
	
	
	@Override
	public void setLabels(String... strings) {

		lbl_Name.setText(strings[0]);
		lbl_Examples.setText(strings[1]);
	}
	
	//Metodi per animazione bellina :)
	
	@Override
	public void onMouseEntered()
	{
		box_Row.setStyle("-fx-background-color : #0A0E3F");
	}
		
	@Override
	public void onMouseExited()
	{
		box_Row.setStyle("-fx-background-color : #02030A");
	}
	
	@Override
	public void setAdminController(AdminHomeController controller)
	{
		this.admincontroller = controller;
	}

}
