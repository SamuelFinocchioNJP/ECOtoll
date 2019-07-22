package controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.mysql.jdbc.StringUtils;

import autostrada.AutostradaController;
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

/**
 * This is the main controller for the panel to edit an Highway
 *
 */
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
	
	/**
	 * <p>This checks for the text boxes correctness and if they are correct 
	 * runs the query to edit the selected highway into the database
	 * Ends by closing the view and refreshing the highway rows (Hence the need
	 * for the caller controller reference)</p>
	 */
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
				String[] lables = ((RowHighwaysEditController) x.getController()).getLabels();
				res.put(lables[0], Float.valueOf(lables[1]));
			}
		}
		//query
		AutostradaController controller = new AutostradaController();
		controller.editAutostradaWithTariff(code, txt_Name.getText(), res);
		
		admincontroller.onRefreshClickHighways();
		
		Stage stage = (Stage) btn_Done.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * <p>This method is used to give this controller a reference 
	 * to the controlled that started the insertion operation
	 * </p>
	 * @param controller
	 */
	public void setHomeController(AdminHomeController controller)
	{
		admincontroller = controller;
	}
	
	/**
	 * <p>This method is used to initialize the labels of
	 * the view once an highway is selected from the parent controller</p>
	 * @param id
	 * @param name
	 */
	public void setLabels(String id, String name)
	{
		lbl_Highway.setText("Highway " + id);
		txt_Name.setText(name);
	}
	
	/**
	 * <p>
	 * This method is used to pass the 
	 * selected highway code from the parent controller
	 * </p>
	 * @param code
	 */
	public void setCode(int code)
	{
		this.code = code;
		populate();
	}
	
	private void populate()
	{
		
		AutostradaController controllerA= new AutostradaController();
		Map<String,Float> result = controllerA.getAutostradeTariffe(code);
		
		loaders = new FXMLLoader[result.size()];
		nodes = new Node[result.size()];
		int i = 0;
		for(Map.Entry<String,Float> x : result.entrySet())
		{
					
			try
			{
				//Prendo il layout della singola riga
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/RowHighwaysEdit.fxml"));
				loaders[i] = loader;
				//la assegno all'i-esmima entry
				nodes[i] = (Node) loader.load();
						
				//prendo il controller della riga e utilizzo il metodo setLabels per inserire i dati del record corrente
				RowHighwaysEditController controller = loader.getController();
				controller.setLabels(x.getKey(), String.valueOf(x.getValue()));
				
				scroll_vehicle_classes.getChildren().add(nodes[i]);
				i++;
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
