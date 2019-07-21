package view;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import Casello.Casello;
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
import veicolo.Classe3;
import veicolo.Classe4;
import veicolo.Classe5;
import veicolo.ClasseA;
import veicolo.ClasseB;
import veicolo.Veicolo;

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
	private Button btn_km_toll;
	
	@FXML
	private Button btn_eco_toll;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setTollboothCode(String code)
	{
		this.destination_tollbooth_code = code;
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
		this.start_tollbooth_code = reader.readLine();
		this.car_license_plate = reader.readLine();
		System.out.println(start_tollbooth_code);
		System.out.println(car_license_plate);

		switch(toll_code) {
		
			case Constants.KM_TOLL:
								
				Veicolo vehicle = new ClasseA("dnsj","dbsaj", "33", 2, 2);
				Veicolo vehicle2 = vehicle;
				System.out.println(vehicle2 instanceof ClasseA);
		
				
				
				
				Casello destination_toll = new Casello(Integer.parseInt(this.destination_tollbooth_code));
				Casello start_toll = new Casello(Integer.parseInt(this.start_tollbooth_code));
				ControllerAutostrada highway_controller = new ControllerAutostrada();
				Map<String,Float> rate = highway_controller.getAutostradeTariffe(destination_toll.getAutostradaId());
				if(vehicle2 instanceof ClasseA)
					rate.get(Constants.COD_CLASSE_A);
				else
					if(vehicle2 instanceof ClasseB)
						rate.get(Constants.COD_CLASSE_B);
					else
						if(vehicle2 instanceof Classe3)
							rate.get(Constants.COD_CLASSE_3);
						else
							if(vehicle2 instanceof Classe4)
								rate.get(Constants.COD_CLASSE_4);
							else
								if(vehicle2 instanceof Classe5)
									rate.get(Constants.COD_CLASSE_5);
								else
									throw new IllegalArgumentException("Tipo di veicolo non riconosciuto");
								
				
				toll = new PedaggioKm();
				toll.calcoloPedaggio(veicolo, puntoPagamentoIn, puntoPagamentoOut, listCasello, tariffaUnitaria, iva);
				break;
		
			case Constants.ECO_TOLL:
			
				toll = new PedaggioEco();
				System.out.println(toll_code);
				break;
		
		default:
			
		}
	}
	
	
}
