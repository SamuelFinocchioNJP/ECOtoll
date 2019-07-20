package home;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RowTollboothsController implements Initializable, RowControllerInterface {

	@FXML
	private Label lbl_Code;
	
	@FXML
	private Label lbl_Name;
	
	@FXML
	private Label lbl_KM;
	
	@FXML
	private Button btn_Edit;
	
	@FXML
	private Button btn_Delete;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void onEditClick()
	{
		//TODO: Da decidere, apriamo un pannello per editare?
	}
	
	@Override
	public void onDeleteClick()
	{
		//TODO: Chiediamo una conferma?
	}
	
	@Override
	public void setLabels(String... strings) {
		// TODO Auto-generated method stub
		lbl_Code.setText(strings[0]);
		lbl_Name.setText(strings[1]);
		lbl_KM.setText(strings[2]);
		return;
	}
}
