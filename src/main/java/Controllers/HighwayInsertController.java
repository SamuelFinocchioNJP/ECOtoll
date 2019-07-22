package Controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.mysql.jdbc.StringUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utility.Constants;

/**
 * This is the main controller for the panel to add a new Highway
 * 
 *
 */
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
		populate();
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
	 * <p>This checks for the text boxes correctness and if they are correct 
	 * runs the query to add a new highway into the database
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
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/RowHighwaysEdit.fxml"));
				loaders[i] = loader;
				//la assegno all'i-esmima entry
				nodes[i] = (Node) loader.load();
						
				//prendo il controller della riga e utilizzo il metodo setLabels per inserire i dati del record corrente
				RowHighwaysEditController controller = loader.getController();
				controller.setLables(classi[i], "");
				
				scroll_vehicle_classes.getChildren().add(nodes[i]);
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
