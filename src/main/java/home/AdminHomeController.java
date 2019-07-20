package home;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AdminHomeController implements Initializable {

	//Variabili Pannelli di Sinistra
	
	@FXML
	private Pane pnl_Highways;
	
	@FXML
	private Pane pnl_Tollbooths;
	
	@FXML 
	private Pane pnl_Vehicles;
	
	//Variabili oggetti pannello Highways
	
	@FXML
	private Label lbl_Number_Highways;
	
	@FXML
	private Label lbl_Number_Title_Highways;
	
	@FXML 
	private Button btn_Insert_Highways;
	
	@FXML
	private Button btn_Refresh_Highways;
	
	@FXML
	private VBox scroll_Highways = null;
	
	
	//Variabili oggetti pannello Tollbooths
	
	@FXML
	private Label lbl_Number_Tollbooths;
		
	@FXML
	private Label lbl_Number_Title_Tollbooths;
		
	@FXML 
	private Button btn_Insert_Tollbooths;
		
	@FXML
	private Button btn_Refresh_Tollbooths;
	
	@FXML
	private TextField txt_HighwayCode;
	
	@FXML
	private VBox scoll_Tollbooths = null;
	
	//Variabili oggetti pannello Vehicles
	
	@FXML
	private Label lbl_Number_Vehicles;
			
	@FXML
	private Label lbl_Number_Title_Vehicles;
			
	@FXML 
	private Button btn_Insert_Vehicles;
			
	@FXML
	private Button btn_Refresh_Vehicles;	
	
	@FXML
	private VBox scroll_Vehicles = null;
	
	
	
	public AdminHomeController()
	{
		//TODO: costruttore, qua si fa la query per fare il get delle highways una volta che ho i dati parte initialize automaticamente
		//		qua non ho accesso agli oggetti @FXML
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO: prendere i dati che mi fornisce la query fatta nel costruttore e displayarli
		//		 qui ho accesso agli oggetti FXML
		
		
		//Parte visibile il pannello highways
		pnl_Highways.setVisible(true);
		pnl_Tollbooths.setVisible(false);
		pnl_Vehicles.setVisible(false);	
	}
	
	//Metodi bottoni Highways
	
	public void onInsertClickHighwyas()
	{
		//TODO: query di inserimento
	}
	
	public void onRefreshClickHighways()
	{
		//TODO: query per refreshare la vista delle autostrade
	}

	//Metodi bottoni Tollbooths
	
	public void onInsertClickTollbooths()
	{
		//TODO: query di inserimento
	}
		
	public void onRefreshClickTollbooths()
	{
		//TODO: query per refreshare la vista delle tollbooths
	}
	
	//Metodi bottoni Vehicles
	
	public void onInsertClickVehicles()
	{
		//TODO: query di inserimento
	}
		
	public void onRefreshClickVehicles()
	{
		//TODO: query per refreshare la vista dei veicoli
	}
	
	//Metodi click sui bottoni a sinistra per cambio panel
	
	public void onHighwaysClick()
	{
		if(!pnl_Highways.isVisible())
		{
			pnl_Highways.setVisible(true);
			pnl_Tollbooths.setVisible(false);
			pnl_Vehicles.setVisible(false);	
		}
	}
	
	public void onTollboothsClick()
	{
		if(!pnl_Tollbooths.isVisible())
		{
			pnl_Highways.setVisible(false);
			pnl_Tollbooths.setVisible(true);
			pnl_Vehicles.setVisible(false);	
		}
	}
	
	public void onVehiclesClick()
	{
		if(!pnl_Vehicles.isVisible())
		{
			pnl_Highways.setVisible(false);
			pnl_Tollbooths.setVisible(false);
			pnl_Vehicles.setVisible(true);	
		}
	}
	
	public void onSignoutClick()
	{
		//TODO: sloggo e torno alla schermata iniziale
	}
	
	

}
