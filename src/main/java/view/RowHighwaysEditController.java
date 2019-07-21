package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class RowHighwaysEditController implements Initializable {

	@FXML
	private TextField txt_Type;
	
	@FXML
	private TextField txt_Tariff;
	
	@FXML
	private HBox box_Row;
	
	private HighwaysEditController editcontroller;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	
	
	public void setHighwaysController(HighwaysEditController controller)
	{
		editcontroller = controller;
	}
	
	public void setLables(String type, String tariff)
	{
		txt_Type.setText(type);
		txt_Tariff.setText(tariff);
	}
	
	public String[] getLables()
	{
		String[] res = new String[2];
		res[0] = txt_Type.getText();
		res[1] = txt_Tariff.getText();
		return res;
	}
	
	
	
	//Metodi per animazione bellina :)
	
	
	public void onMouseEntered()
	{
		box_Row.setStyle("-fx-background-color : #0A0E3F");
	}
		
	public void onMouseExited()
	{
		box_Row.setStyle("-fx-background-color : #02030A");
	}
}
