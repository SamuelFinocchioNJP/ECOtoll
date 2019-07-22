package view;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import Casello.Casello;
import autostrada.Autostrada;
import autostrada.ControllerAutostrada;
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
import veicolo.VeicoloController;

public class HomeOperatorMainController implements Initializable{

	private String destination_tollbooth_code;
	private String start_tollbooth_code;
	private String car_license_plate;
	private int toll_code;
	private IPedaggio toll;
	
	@FXML
	private Button btn_calculate;
	
	@FXML
	private Label lbl_tollbooth;
	
	@FXML
	private Label lbl_tollbooth2;
	
	@FXML
	private Button btn_km_toll;
	
	@FXML
	private Button btn_eco_toll;
	
	@FXML
	private Label lbl_tollprice;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void setTollboothCode(String code)
	{
		this.destination_tollbooth_code = code;
		lbl_tollbooth2.setText("you are at toolbooth number: "+ code);
		return;
	}
	
	public void handleTollClick(ActionEvent actionEvent) {
		
		
		if(actionEvent.getSource() == btn_km_toll) {
			toll_code = Constants.KM_TOLL;
			lbl_tollprice.setVisible(false);
		}
		
		if(actionEvent.getSource() == btn_eco_toll) {
			toll_code = Constants.ECO_TOLL;
			lbl_tollprice.setVisible(false);
			lbl_tollprice.setText("The ECOtoll will be avaible from 2021!");
			lbl_tollprice.setVisible(true);
		}
		System.out.println("Toll Selected: "+toll_code);
		
	}

	public void onClick() throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("tollData.txt"));
		this.start_tollbooth_code = reader.readLine();
		this.car_license_plate = reader.readLine();
		System.out.println(start_tollbooth_code);
		System.out.println(car_license_plate);

		switch(toll_code) {	
			case Constants.KM_TOLL:							
				Veicolo vehicle = VeicoloController.getVeicolo(car_license_plate);				
					Casello destination_toll = new Casello(Integer.parseInt(this.destination_tollbooth_code));
					Casello start_toll = new Casello(Integer.parseInt(this.start_tollbooth_code));
					ControllerAutostrada highway_controller = new ControllerAutostrada();
					Map<String,Float> rate = highway_controller.getAutostradeTariffe(destination_toll.getAutostradaId());											
					toll = new PedaggioKm();
					
					Autostrada highway = new Autostrada(destination_toll.getAutostradaId());
					int highway_iva = highway.getIva();
					
					
					lbl_tollprice.setText("The toll price is: "+ toll.calcoloPedaggio(vehicle, start_toll, destination_toll, rate, highway_iva)+"0�");
					lbl_tollprice.setVisible(true);
					break;
				
		
			case Constants.ECO_TOLL:		
				toll = new PedaggioEco();
				System.out.println(toll_code);
				break;
		
			default:
				lbl_tollprice.setText("You have to select a toll type!");
				lbl_tollprice.setVisible(true);			
		}
		reader.close();
	}
	
	
}
