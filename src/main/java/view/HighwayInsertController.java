package view;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import autostrada.ControllerAutostrada;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utility.Constants;

public class HighwayInsertController implements Initializable {

	FXMLLoader[] loaders ;
	
	Node[] nodes;
	
	@FXML
	private TextField txt_Name;
	
	@FXML
	private Button btn_Done;
	
	@FXML
	private VBox scroll_vehicle_classes;
	
	private AdminHomeController admincontroller;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		populate();
	}

	public void setHomeController(AdminHomeController controller)
	{
		admincontroller = controller;
	}
	
	public void onClick()
	{
		Map<String,Float> res = new HashMap<String,Float>();
		
		if(loaders != null)
		{
			for(FXMLLoader x : loaders)
			{
				String[] lables = ((RowHighwaysEditController) x.getController()).getLables();
				res.put(lables[0], Float.valueOf(lables[1]));
			}
		}
		//query
		ControllerAutostrada controller = new ControllerAutostrada();
		controller.addAutostradaWithTariff(txt_Name.getText(), res);
		
		admincontroller.onRefreshClickHighways();
		
		Stage stage = (Stage) btn_Done.getScene().getWindow();
		stage.close();
	}
	
	private void populate()
	{
		
		String[] classi =  {"A","B","3","4","5"};
		
		
		loaders = new FXMLLoader[Constants.NUMERO_CLASSI];
		nodes = new Node[Constants.NUMERO_CLASSI];
		int i = 0;
		for(i = 0; i < Constants.NUMERO_CLASSI ; i++)
		{
			try
			{
				//Prendo il layout della singola riga
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RowHighwaysEdit.fxml"));
				loaders[i] = loader;
				//la assegno all'i-esmima entry
				nodes[i] = (Node) loader.load();
						
				//prendo il controller della riga e utilizzo il metodo setLabels per inserire i dati del record corrente
				RowHighwaysEditController controller = loader.getController();
				controller.setLables(classi[i], "");
				//Ogni row ha il riferimento al controller dello scroller in cui si trova
				controller.setHighwaysController(this);
				scroll_vehicle_classes.getChildren().add(nodes[i]);
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
