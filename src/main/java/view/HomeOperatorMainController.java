package view;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pedaggio.IPedaggio;
import pedaggio.PedaggioKm;
import utility.Constants;

public class HomeOperatorMainController implements Initializable{

	private String code;
	private int toll_code;
	private IPedaggio toll;
	
	@FXML
	private Button btn_calculate;
	
	@FXML
	private Label lbl_tollbooth;
	
	@FXML
	private Button btn_km_toll;
	
	@FXML
	private Button btn_eco_toll;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setTollboothCode(String code)
	{
		this.code = code;
		lbl_tollbooth.setText("Welcome, \n you are in "+ code +" toolbooth");
		return;
	}
	
	public void handleTollClick(ActionEvent actionEvent) {
		
		if(actionEvent.getSource() == btn_km_toll) {
			toll_code = Constants.KM_TOLL;
		}
		
		if(actionEvent.getSource() == btn_eco_toll) {
			toll_code = Constants.ECO_TOLL;
		}
		System.out.println("Toll Selected: "+toll_code);
		
	}

	public void onClick() {
		
		switch(toll_code) {
		
		case Constants.KM_TOLL:
			//pedaggio per km
			toll = new PedaggioKm();
			toll.calcoloPedaggio(veicolo, puntoPagamentoIn, puntoPagamentoOut, listCasello, tariffaUnitaria, iva)
			System.out.println(toll_code);
			break;
		
		case Constants.ECO_TOLL:
			// pedaggio eco
			System.out.println(toll_code);
			
			break;
		
		default:
			
		}
	}
	
	
}
