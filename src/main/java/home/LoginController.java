package home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	
	@FXML
	private Button administrationArea;
	@FXML
	private Button operatorArea;

	public LoginController() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void handleClicks(ActionEvent actionEvent) {
		       
		if(actionEvent.getSource() == administrationArea) {
	        Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("Login-administrator.fxml"));
			    Stage stageTheButtonBelongs = (Stage) administrationArea.getScene().getWindow();
			    stageTheButtonBelongs.setScene(new Scene(root));
		       
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(actionEvent.getSource() == operatorArea) {
	        Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("Login-operator.fxml"));
			    Stage stageTheButtonBelongs = (Stage) operatorArea.getScene().getWindow();
			    stageTheButtonBelongs.setScene(new Scene(root));
		      
			    
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
					
	}

}
