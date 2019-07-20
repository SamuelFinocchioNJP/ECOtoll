package home;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RowVehiclesController implements Initializable, RowControllerInterface {

	@FXML
	private Label lbl_Name;
	
	@FXML
	private Label lbl_Examples;
	
	@FXML
	private Button btn_Edit;
	
	@FXML
	private Button btn_Delete;
		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	


	@Override
	public void onEditClick() {
		//TODO: Da decidere, apriamo un pannello per editare?

	}

	@Override
	public void onDeleteClick() {
		//TODO: Chiediamo una conferma?

	}

	@Override
	public void setLabels(String... strings) {

		lbl_Name.setText(strings[0]);
		lbl_Examples.setText(strings[1]);
	}

}
