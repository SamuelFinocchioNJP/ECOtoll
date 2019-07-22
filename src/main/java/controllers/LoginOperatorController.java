package controllers;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import casello.Casello;
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
import utility.Constants;

/**
 * This class manages the operator toll selection panel
 *
 */
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
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}
	
	/**
	 * This button submits the tollcode to the next view
	 * Checks if the code exists and doesn't allow
	 * you to continue if it does not exists
	 */
	public void onSubmitClick()
	{			
		try {
			/// True is to allow constructor to forward exceptions
			new Casello(Integer.valueOf(textbox_toll_code.getText()),true);
			
			// Creo il loader che contiene il nuovo layout dell'interfaccia
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Home_Operator_Main.fxml"));
			root = loader.load();
			
			// Prendo il controller del loader e setto il tollcode con il relativo metodo
			HomeOperatorMainController controller = loader.getController();
			controller.setTollboothCode(textbox_toll_code.getText());
			
			// Passo alla nuova view
			stage = (Stage) submit.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Operator Panel - Tollbooth #" + textbox_toll_code.getText());
			
		} catch (Exception e) {
			
			textbox_toll_code.setText("");
			textbox_toll_code.setPromptText(Constants.CASELLO_NOT_FOUND_ERROR);
			return;
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
