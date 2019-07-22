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
	
	private Parent root;
	private double x,y;
	private Stage stage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void onClick()
	{
		try {
			
			Auth.LoginController controller = new Auth.LoginController();
			
			Boolean login = controller.login(txt_Username.getText(), txt_Password.getText());
			
			if(!login)
			{
				txt_Username.setText("Wrong Credentials");
				txt_Password.setText("");
				return;
			}
			
			//Creo il loader che contiene il nuovo layout dell'interfaccia
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
			root = loader.load();
			
			
			//Passo alla nuova view
			stage = (Stage) btn_login.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Operator Panel - Tollbooth #" + txt_Username.getText());
		}
		catch(Exception e){
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
