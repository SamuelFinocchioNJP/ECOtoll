package Controllers;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import Casello.Casello;
import autostrada.Autostrada;
import autostrada.AutostradaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pedaggio.IPedaggio;
import pedaggio.PedaggioEco;
import pedaggio.PedaggioKm;
import utility.Constants;
import veicolo.Veicolo;
import veicolo.VeicoloController;

/**
 * This is the main controller for the operator main view
 *
 */
public class HomeOperatorMainController implements Initializable {

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
	
	private double x , y;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	/**
	 * <p>This method is used to pass the 
	 * tollbooth code from the parent controller
	 * </p>
	 * @param code
	 */
	public void setTollboothCode(String code)
	{
		this.destination_tollbooth_code = code;
		lbl_tollbooth2.setText ( "you are at toolbooth number: " + code );
		return;
	}
	
	/**
	 * <p> This is the handler for the buttons 
	 * used to select the toll type</p>
	 * @param actionEvent
	 */
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
		System.out.println("Toll Selected: " + toll_code);
		
	}

	/**
	 * <p>This method is used to 
	 * manage the "back icon" click event </p>
	 */
	public void onBtnBackClick()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login-operator.fxml"));
		Parent root;
		
		try {	
			root = loader.load();
			Stage stage = (Stage) btn_eco_toll.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
			
			root.setOnMousePressed(event -> {
		        x = event.getSceneX();
		        y = event.getSceneY();
		    });
		    root.setOnMouseDragged(event -> {
		        stage.setX(event.getScreenX() - x);
		        stage.setY(event.getScreenY() - y);

		    });
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * <p>This method handles the click on the calculate button
	 * Calculates a toll price and displays it on the view</p>
	 * @throws IOException
	 */
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
					AutostradaController highway_controller = new AutostradaController();
					Map<String,Float> rate = highway_controller.getAutostradeTariffe(destination_toll.getAutostradaId());											
					toll = new PedaggioKm();
					
					Autostrada highway = new Autostrada(destination_toll.getAutostradaId());
					int highway_iva = highway.getIva();
					
					
					lbl_tollprice.setText("The toll price is: "+ toll.calcoloPedaggio(vehicle, start_toll, destination_toll, rate, highway_iva)+"0 Euro");
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
