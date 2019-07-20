package home;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeOperatorMainController implements Initializable{

	private String code;
	
	@FXML
	private Button btn_calculate;
	
	@FXML
	private Label lbl_tollbooth;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setTollboothCode(String code)
	{
		this.code = code;
		lbl_tollbooth.setText("Tollboth #" + code);
		return;
	}
	

	public void onClick()
	{
		//TODO: Codice per prendere da file i dati, calcolare il prezzo e stamparlo
	}
}
