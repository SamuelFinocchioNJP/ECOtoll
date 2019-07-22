package view;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.mysql.jdbc.StringUtils;

import autostrada.ControllerAutostrada;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utility.Constants;

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
	
	private Node[] nodes ;
	
	private FXMLLoader[] loaders;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void onClick()
	{
		
		if(StringUtils.isEmptyOrWhitespaceOnly(txt_Name.getText()))
		{
			txt_Name.setPromptText(Constants.WRONG_NAME);
			return;
		}
		
		Map<String,Float> res = new HashMap<String,Float>();
		
		//Popolo la map
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
		controller.editAutostradaWithTariff(code, txt_Name.getText(), res);
		
		admincontroller.onRefreshClickHighways();
		
		Stage stage = (Stage) btn_Done.getScene().getWindow();
		stage.close();
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
		Map<String,Float> result = controllerA.getAutostradeTariffe(code);
		
		loaders = new FXMLLoader[result.size()];
		nodes = new Node[result.size()];
		int i = 0;
		for(Map.Entry<String,Float> x : result.entrySet())
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
				controller.setLables(x.getKey(), String.valueOf(x.getValue()));
				
				scroll_vehicle_classes.getChildren().add(nodes[i]);
				i++;
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
