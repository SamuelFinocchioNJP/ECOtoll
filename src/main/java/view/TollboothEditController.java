package view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import Casello.Casello;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;

/**
 * 
 * This is the controller for the panel to edit tollbooths
 *
 */
public class TollboothEditController implements Initializable {

	
	@FXML
	private Button btn_Done;
	
	@FXML
	private TextField txt_Name;
	
	@FXML
	private TextField txt_KM;
	
	@FXML
	private Label lbl_Tollbooth;
	
	private AdminHomeController admincontroller;
	
	private int code;
	
	
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
		txt_KM.setTextFormatter(formatter);

	}

	/**
	 * This methods handles the clock on the done button to complete the edit.
	 * This queries it to the database and then closes the edit windows
	 * and refreshes the rows in the parent view.
	 */
	public void onClick()
	{
		Casello casello;
		casello = new Casello (code);
		casello.setKm(Integer.valueOf(txt_KM.getText()));
		casello.setLocalita(txt_Name.getText());
		
		casello.save();
		
		
		admincontroller.onRefreshClickTollbooths();
		
		Stage stage = (Stage) btn_Done.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * <p>This method is used to give this controller a reference 
	 * to the controlled that created it
	 * </p>
	 * @param controller
	 */
	public void setHomeController(AdminHomeController controller)
	{
		admincontroller = controller;
	}
	
	/**
	 * Sets the labels for the edit panel
	 * @param code
	 * @param nome
	 * @param KM
	 */
	public void setLabels(String code, String nome, String KM)
	{
		txt_Name.setText(nome);
		txt_KM.setText(KM);
		lbl_Tollbooth.setText("Editing Tollbooth " + code);
	}
	
	/**
	 * Sets the highway code to use in the query
	 * @param code
	 */
	public void setCode(int code)
	{
		this.code = code;
	}
}
