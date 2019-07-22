package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import utility.Constants;

/**
 * This is the main controller for the admin login view
 *
 */
public class LoginAdministratorController implements Initializable {

	@FXML
	private Button btn_login;
	
	@FXML
	private TextField txt_Username;
	
	@FXML
	private PasswordField txt_Password;
	
	private Parent root;
	private double x,y;
	private Stage stage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * <p>This method is used to 
	 * manage the "back icon" click event </p>
	 */
	public void onBtnBackClick()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login-choice.fxml"));
		Parent root;
		
		try {	
			root = loader.load();
			Stage stage = (Stage) btn_login.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
			
			root.setOnMousePressed(event -> {
		        x = event.getSceneX();
		        y = event.getSceneY();
		    });
		    root.setOnMouseDragged(event -> {
		        stage.setX(event.getScreenX() - x);
		        stage.setY(event.getScreenY() - y);

		    });
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		
	}
	
	/**
	 * <p>This method queries the database for the login authentication
	 * then proceeds to block the login or admit the user to the 
	 * main admin panel</p>
	 */
	public void onClick()
	{
		try {
			
			auth.LoginController controller = new auth.LoginController();
			
			Boolean login = controller.login(txt_Username.getText(), txt_Password.getText());
			
			if(!login)
			{
				txt_Username.setText("");
				txt_Username.setPromptText(Constants.WRONG_USERNAME);
				txt_Password.setText("");
				return;
			}
			
			//Creo il loader che contiene il nuovo layout dell'interfaccia
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminHome.fxml"));
			root = loader.load();
			
			
			//Passo alla nuova view
			stage = (Stage) btn_login.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Operator Panel - Tollbooth #" + txt_Username.getText());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println(e);
			
		}
		
        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });
	}
}
