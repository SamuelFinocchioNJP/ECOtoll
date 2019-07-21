package view;

import java.net.URL;
import java.util.ResourceBundle;

import Casello.Casello;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TollboothEditController implements Initializable {

	
	@FXML
	private Button btn_Done;
	
	@FXML
	private TextField txt_Name;
	
	@FXML
	private TextField txt_KM;
	
	@FXML
	private Label lbl_Tollbooth;
	
	private AdminHomeController admincontroller;
	
	private int code;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	
	public void onClick()
	{
		Casello casello = new Casello (code);
		casello.setKm(Integer.valueOf(txt_KM.getText()));
		casello.setLocalita(txt_Name.getText());
		
		casello.save();
		
		admincontroller.onRefreshClickTollbooths();
		
		Stage stage = (Stage) btn_Done.getScene().getWindow();
		stage.close();
	}
	
	public void setHomeController(AdminHomeController controller)
	{
		admincontroller = controller;
	}
	
	public void setLabels(String code, String nome, String KM)
	{
		txt_Name.setText(nome);
		txt_KM.setText(KM);
		lbl_Tollbooth.setText("Editing Tollbooth " + code);
	}
	
	public void setCode(int code)
	{
		this.code = code;
	}
}
