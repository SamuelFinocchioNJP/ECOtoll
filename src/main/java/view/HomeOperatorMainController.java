package view;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pedaggio.IPedaggio;
import pedaggio.PedaggioEco;
import pedaggio.PedaggioKm;
import utility.Constants;
import veicolo.Veicolo;

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

	public void onClick() throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("tollData.txt"));
		String tollbooth_code = reader.readLine();
		String car_license_plate = reader.readLine();
		System.out.println(tollbooth_code);
		System.out.println(car_license_plate);

		switch(toll_code) {
		
		case Constants.KM_TOLL:
				
			//Veicolo vehicle = new Veicolo();
			
			toll = new PedaggioKm();
			//toll.calcoloPedaggio(veicolo, puntoPagamentoIn, puntoPagamentoOut, listCasello, tariffaUnitaria, iva);
			
			break;
		
		case Constants.ECO_TOLL:
			
			toll = new PedaggioEco();
			System.out.println(toll_code);
			
			break;
		
		default:
			
		}
	}
	
	
}
