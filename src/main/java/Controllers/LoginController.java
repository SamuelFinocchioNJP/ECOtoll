package Controllers;

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

/**
 * This is the main controller for the main page, used to chose between admin or user access
 *
 */
public class LoginController implements Initializable{

	
	@FXML
	private Button administrationArea;
	@FXML
	private Button operatorArea;
	
	private Parent root;
	private double x,y;
	private Stage stageTheButtonBelongs;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	
	/**
	 * <p>This method handles the user decision to chose 
	 * between admin and operator view.
	 * Intercepts the button clicks.</p>
	 * @param actionEvent
	 */
	public void handleClicks(ActionEvent actionEvent) {
		       
		if ( actionEvent.getSource() == administrationArea ) {
	        
			try {
				root = FXMLLoader.load(getClass().getResource("../view/Login-administrator.fxml"));
			    stageTheButtonBelongs = (Stage) administrationArea.getScene().getWindow();
			    stageTheButtonBelongs.setScene(new Scene(root));
		       
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(actionEvent.getSource() == operatorArea) {
	        
			try {
				root = FXMLLoader.load(getClass().getResource("../view/Login-operator.fxml"));
			    stageTheButtonBelongs = (Stage) operatorArea.getScene().getWindow();
			    stageTheButtonBelongs.setScene(new Scene(root));
		      
			    
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stageTheButtonBelongs.setX(event.getScreenX() - x);
            stageTheButtonBelongs.setY(event.getScreenY() - y);

        });
	}

}
