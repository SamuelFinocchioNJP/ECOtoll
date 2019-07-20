package view;

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

public class LoginAdministratorController implements Initializable {

	@FXML
	private Button btn_login;
	
	@FXML
	private TextField txt_Username;
	
	@FXML
	private TextField txt_Password;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void onClick()
	{
		try {
			//TODO: query per controllo login
			
			//Suppongo successo nel login
			
			//Creo il loader che contiene il nuovo layout dell'interfaccia
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
			Parent root = loader.load();
			
			
			//Passo alla nuova view
			Stage stage = (Stage) btn_login.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Operator Panel - Tollbooth #" + txt_Username.getText());
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println(e);
		}
		
		
	}
	
}
