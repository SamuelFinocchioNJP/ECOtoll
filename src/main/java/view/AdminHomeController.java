package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import com.mysql.jdbc.StringUtils;

import autostrada.Autostrada;
import Casello.Casello;
import Casello.CaselloController;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the main controller for the Admin panel containing features to manage Highways and Tollbooths
 * @author: Pietro Ciammaricone
 * 
 * */
public class AdminHomeController implements Initializable {

	//Variabili Pannello di Sinistra
	
	@FXML
	private Pane pnl_Highways;
	
	@FXML
	private Pane pnl_Tollbooths;
	
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
	
	
	@FXML
	private Button btn_Signout;
	
	private double x , y;
	
	private ArrayList<Autostrada> query_results;
		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Query e popolamento delle rows
		getAllHighways();
		
		//Il bottone di insert parte disabilitato
		btn_Insert_Tollbooths.setDisable(true);
		
		UnaryOperator<Change> filter = change -> {
		    String text = change.getText();

		    if (text.matches("[0-9]*")) {
		        return change;
		    }
		    return null;
		};
		
		TextFormatter<String> formatter = new TextFormatter<>(filter);
		txt_HighwayCode.setTextFormatter(formatter);
		
		//Parte visibile il pannello highways
		pnl_Highways.setVisible(true);
		pnl_Tollbooths.setVisible(false);
	}
	
	//Metodi bottoni Highways
	
	/**
	 * <p> Opens a new window with an UI to create and submit a new Highway to the Database </p>
	 */
	public void onInsertClickHighwyas()
	{
		//query di inserimento
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("HighwaysInsert.fxml"));
			Parent root = loader.load();
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			HighwayInsertController controller = loader.getController();
			
			controller.setHomeController(this);
			
			stage.show();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * <p> Refreshes the current rows in the highways panel </p>
	 */
	public void onRefreshClickHighways()
	{	
		//Pulisco la lista
		scroll_Highways.getChildren().clear();
		
		//Query e popolamento delle rows
		getAllHighways();
	}

	//Metodi bottoni Tollbooths
	
	/**
	 * <p> Opens a new window with an UI to create and submit a new tollbooth to the Database </p>
	 */
	public void onInsertClickTollbooths()
	{
		//query di inserimento
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TollboothInsert.fxml"));
			Parent root = loader.load();
					
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			TollboothInsertController controller = loader.getController();
			controller.setHighwayCode(Integer.valueOf(txt_HighwayCode.getText()));
					
			controller.setHomeController(this);
					
			stage.show();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * <p> Refreshes the current rows in the tollbooths panel </p>
	 */
	public void onRefreshClickTollbooths()
	{
		
		if(StringUtils.isEmptyOrWhitespaceOnly(txt_HighwayCode.getText()))
			return;
		
		//pulisco la lista
		scoll_Tollbooths.getChildren().clear();
		
		btn_Insert_Tollbooths.setDisable(false);
		//Query e ripopolamento
		getAllTollbooths();
	}
	
	//Metodi click sui bottoni a sinistra per cambio panel
	
	
	/**
	 * Switches the UI to the highways section
	 */
	public void onHighwaysClick()
	{
		if(!pnl_Highways.isVisible())
		{
			pnl_Highways.setVisible(true);
			pnl_Tollbooths.setVisible(false);
		}
	}
	
	/**
	 * Switches the UI to the tollbooths section
	 */
	public void onTollboothsClick()
	{
		if(!pnl_Tollbooths.isVisible())
		{
			pnl_Highways.setVisible(false);
			pnl_Tollbooths.setVisible(true);
		}
	}
	
	/**
	 * Signs out from the admin panel and returns to the main choice panel
	 */
	public void onSignoutClick()
	{
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login-choice.fxml"));
			Parent root = loader.load();
			
			Stage stage = (Stage) btn_Signout.getScene().getWindow();
			stage.setScene(new Scene(root));
			
			//drag it here
		    root.setOnMousePressed(event -> {
		        x = event.getSceneX();
		        y = event.getSceneY();
		    });
		    root.setOnMouseDragged(event -> {
		        stage.setX(event.getScreenX() - x);
		        stage.setY(event.getScreenY() - y);

		    });
			
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
			
		lbl_Number_Highways.setText(String.valueOf(query_results.size()));
			
				
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
				i++;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	
	private void getAllTollbooths( )
	{
		ArrayList<Casello> query_results;
		
		//Eseguo la query
		CaselloController caselloController = new CaselloController();
		query_results = caselloController.getCaselliFromAutostrada(Integer.valueOf( txt_HighwayCode.getText() ));
					
		lbl_Number_Tollbooths.setText(String.valueOf(query_results.size()));
					
						
		//Ripopolo la lista
		Node[] nodes = new Node[query_results.size()];
		int i = 0;
		for(Casello x : query_results)
		{
							
			try
			{
				//Prendo il layout della singola riga
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RowTollbooths.fxml"));
				//la assegno all'i-esmima entry
				nodes[i] = (Node) loader.load();
								
				//prendo il controller della riga e utilizzo il metodo setLabels per inserire i dati del record corrente
				RowTollboothsController controller = loader.getController();
				controller.setLabels(String.valueOf( x.getId() ), x.getLocalita(), String.valueOf(x.getKm()));
				//Ogni row ha il riferimento al controller dello scroller in cui si trova
				controller.setAdminController(this);
								
				//aggiungo la riga allo scroller
				scoll_Tollbooths.getChildren().add(nodes[i]);
				i++;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
