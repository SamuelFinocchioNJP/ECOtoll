package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import autostrada.Autostrada;
import autostrada.ControllerAutostrada;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	private VBox scroll_Vehicle = null;
	
	
	@FXML
	private Button btn_Signout;
	
	
	
	private ArrayList<Autostrada> query_results;
	
	
	
	public AdminHomeController()
	{
		ControllerAutostrada controller = new ControllerAutostrada();
		this.query_results = controller.getAutostrade();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Query e popolamento delle rows
		getAllHighways();
		
						
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
		//Pulisco la lista
		scroll_Highways.getChildren().clear();
		
		//Query e popolamento delle rows
		getAllHighways();
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
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login-administrator.fxml"));
			Parent root = loader.load();
			
			Stage stage = (Stage) btn_Signout.getScene().getWindow();
			stage.setScene(new Scene(root));
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void getAllHighways()
	{
		//Eseguo la query
		ControllerAutostrada controllera = new ControllerAutostrada();
		this.query_results = controllera.getAutostrade();
				
			
				
		//Ripopolo la lista
		Node[] nodes = new Node[query_results.size()];
		int i = 0;
		for(Autostrada x : query_results)
		{
					
			try
			{
				//Prendo il layout della singola riga
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RowHighways.fxml"));
				//la assegno all'i-esmima entry
				nodes[i] = (Node) loader.load();
						
				//prendo il controller della riga e utilizzo il metodo setLabels per inserire i dati del record corrente
				RowHighwaysController controller = loader.getController();
				controller.setLabels(String.valueOf(x.getId()),x.getNome());
				//Ogni row ha il riferimento al controller dello scroller in cui si trova
				controller.setAdminController(this);
						
				//aggiungo la riga allo scroller
				scroll_Highways.getChildren().add(nodes[i]);
						
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
