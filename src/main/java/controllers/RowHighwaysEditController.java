package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.HBox;

/**
 * This is the controller for the rows in the edit panel
 *
 */
public class RowHighwaysEditController implements Initializable {

	@FXML
	private Label lbl_Name;
	
	@FXML
	private TextField txt_Tariff;
	
	@FXML
	private HBox box_Row;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		UnaryOperator<Change> filter = change -> {
		    String text = change.getText();

		    if (text.matches("^\\d*\\.?\\d*$")) {
		        return change;
		    }
		    return null;
		};
		
		TextFormatter<String> formatter = new TextFormatter<>(filter);
		txt_Tariff.setTextFormatter(formatter);
	}

	/**
	 * Sets the row lables
	 * @param type
	 * @param tariff
	 */
	public void setLabels(String type, String tariff)
	{
		lbl_Name.setText(type);
		txt_Tariff.setText(tariff);
	}
	
	/**
	 * Returns the labels of this row
	 * @return String[]
	 */
	public String[] getLabels()
	{
		String[] res = new String[2];
		res[0] = lbl_Name.getText();
		res[1] = txt_Tariff.getText();
		return res;
	}
			
	// Metodi per animazione bellina :)
	
	/**
	 * Decides the mouse entering event animation
	 */
	public void onMouseEntered()
	{
		box_Row.setStyle("-fx-background-color : #0A0E3F");
	}
	
	/**
	 * Decides the mouse exiting event animation
	 */
	public void onMouseExited()
	{
		box_Row.setStyle("-fx-background-color : #02030A");
	}
}
