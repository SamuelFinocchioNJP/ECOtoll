package view;

/*
 * @author: Pietro
 */



import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginOperatorController implements Initializable{
	
	@FXML
	private Button submit;
	
	@FXML
	private TextField textbox_toll_code;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void onSubmitClick()
	{		
		try {
			//Creo il loader che contiene il nuovo layout dell'interfaccia
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Home_Operator_Main.fxml"));
			Parent root = loader.load();
			
			//Prendo il controller del loader e setto il tollcode con il relativo metodo
			HomeOperatorMainController controller = loader.getController();
			controller.setTollboothCode(textbox_toll_code.getText());
			
			//Passo alla nuova view
			Stage stage = (Stage) submit.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Operator Panel - Tollbooth #" + textbox_toll_code.getText());
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
		
	}
}
