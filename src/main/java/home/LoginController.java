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
	
	private double x,y;
	
	@FXML
	private Button submit;

	public LoginController() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void handleClicks(ActionEvent actionEvent) {
       
		if(actionEvent.getSource() == submit) {
	        Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("Home.fxml"));
			    Stage stageTheButtonBelongs = (Stage) submit.getScene().getWindow();
			    stageTheButtonBelongs.setScene(new Scene(root));
		        root.setOnMousePressed(event -> {
		            x = event.getSceneX();
		            y = event.getSceneY();
		        });
		        root.setOnMouseDragged(event -> {

		            stageTheButtonBelongs.setX(event.getScreenX() - x);
		            stageTheButtonBelongs.setY(event.getScreenY() - y);

		        });
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
					
	}

}
