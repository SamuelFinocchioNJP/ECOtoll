package view;

/*
 * @author: Pietro
 */



import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import Casello.Casello;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;

public class LoginOperatorController implements Initializable{
	
	@FXML
	private Button submit;
	
	@FXML
	private TextField textbox_toll_code;
	
	private Parent root;
	private double x,y;
	private Stage stage;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	UnaryOperator<Change> filter = change -> {
		    String text = change.getText();

		    if (text.matches("[0-9]*")) {
		        return change;
		    }
		    return null;
		};
		
		TextFormatter<String> formatter = new TextFormatter<>(filter);
		textbox_toll_code.setTextFormatter(formatter);
		
	}
	
	public void onBtnBackClick()
	{
		
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login-choice.fxml"));
		Parent root;
		
		try {	
						
			root = loader.load();
			Stage stage = (Stage) submit.getScene().getWindow();
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
	
	
	public void onSubmitClick()
	{		
				
		try {
			
			Casello casello = new Casello(Integer.valueOf(textbox_toll_code.getText()));
			
			//Creo il loader che contiene il nuovo layout dell'interfaccia
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Home_Operator_Main.fxml"));
			root = loader.load();
			
			//Prendo il controller del loader e setto il tollcode con il relativo metodo
			HomeOperatorMainController controller = loader.getController();
			controller.setTollboothCode(textbox_toll_code.getText());
			
			//Passo alla nuova view
			stage = (Stage) submit.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Operator Panel - Tollbooth #" + textbox_toll_code.getText());
			
		} catch (Exception e) {
			
			textbox_toll_code.setPromptText("Error");
			e.printStackTrace();
			return;
			
			/*e.printStackTrace();
			System.err.println(e);*/
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
