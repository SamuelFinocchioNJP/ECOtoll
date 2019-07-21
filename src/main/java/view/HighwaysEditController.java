package view;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import autostrada.Autostrada;
import autostrada.ControllerAutostrada;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HighwaysEditController implements Initializable {

	@FXML
	private Button btn_Done;
	
	@FXML
	private Label lbl_Highway;
	
	@FXML
	private TextField txt_Name;
	
	@FXML
	private VBox scroll_vehicle_classes;
	
	private int code;
	
	private AdminHomeController admincontroller;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void onClick()
	{
		//query di inserimento
	}
	
	public void setHomeController(AdminHomeController controller)
	{
		admincontroller = controller;
	}
	
	public void setLabels(String id, String name)
	{
		lbl_Highway.setText("Highway " + id);
		txt_Name.setText(name);
	}
	
	public void setCode(int code)
	{
		this.code = code;
		populate();
	}
	
	private void populate()
	{
		
		ControllerAutostrada controllerA= new ControllerAutostrada();
		Map<Integer,Float> result = controllerA.getAutostradeTariffe(code);
		
		
		Node[] nodes = new Node[result.size()];
		int i = 0;
		for(Map.Entry<Integer,Float> x : result.entrySet())
		{
					
			try
			{
				//Prendo il layout della singola riga
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RowHighwaysEdit.fxml"));
				//la assegno all'i-esmima entry
				nodes[i] = (Node) loader.load();
						
				//prendo il controller della riga e utilizzo il metodo setLabels per inserire i dati del record corrente
				RowHighwaysEditController controller = loader.getController();
				controller.setLables("test", String.valueOf(x.getValue()));
				//Ogni row ha il riferimento al controller dello scroller in cui si trova
				controller.setHighwaysController(this);
				scroll_vehicle_classes.getChildren().add(nodes[i]);
				i++;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
