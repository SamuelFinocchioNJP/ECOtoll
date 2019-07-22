package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import casello.Casello;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This is the controller for toll booths panel rows
 *
 */
public class RowTollboothsController implements Initializable, IRowController {

	@FXML
	private Label lbl_Code;
	
	@FXML
	private Label lbl_Name;
	
	@FXML
	private Label lbl_KM;
	
	@FXML
	private Button btn_Edit;
	
	@FXML
	private Button btn_Delete;
	
	@FXML
	private HBox box_Row;
	
	private AdminHomeController admincontroller;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	}

	/**
	 * Manages the click on edit button
	 * Opens a new panel and populate its labels 
	 * with the correct data
	 */
	public void onEditClick()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TollboothEdit.fxml"));
			Parent root = loader.load();
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			TollboothEditController controller = loader.getController();
			controller.setLabels(lbl_Code.getText(), lbl_Name.getText(), lbl_KM.getText());
			controller.setCode(Integer.valueOf(lbl_Code.getText()));
			controller.setHomeController(admincontroller);
			
			stage.show();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Manages the click on the Delete button
	 * queries the database and drops the record with the given id
	 */
	public void onDeleteClick()
	{
		Casello casello = new Casello(Integer.valueOf(lbl_Code.getText()));
		casello.destroy();
			
		admincontroller.onRefreshClickTollbooths();
	}
	
	/**
	 * Sets the row lables
	 * @param type
	 * @param tariff
	 */
	@Override
	public void setLabels(String... strings) {
		lbl_Code.setText(strings[0]);
		lbl_Name.setText(strings[1]);
		lbl_KM.setText(strings[2]);
		return;
	}
	
	//Metodi per animazione bellina :)
	
	/**
	 * Decides the mouse entering event animation
	 */
	@Override
	public void onMouseEntered()
	{
		box_Row.setStyle("-fx-background-color : #0A0E3F");
	}
	
	/**
	 * Decides the mouse exiting event animation
	 */
	@Override
	public void onMouseExited()
	{
		box_Row.setStyle("-fx-background-color : #02030A");
	}
	
	
	/**
	 * <p>Gives this controller a reference 
	 * to the controlled that created it
	 * </p>
	 * @param controller
	 */
	public void setAdminController(AdminHomeController controller)
	{
		this.admincontroller = controller;
	}
}
