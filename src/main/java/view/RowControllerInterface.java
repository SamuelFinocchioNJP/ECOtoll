package view;

public interface RowControllerInterface {

	public void setLabels(String...strings);
	
	public void onEditClick();
	
	public void onDeleteClick();
	
	public void onMouseEntered();
	
	public void onMouseExited();
	
	public void setAdminController(AdminHomeController controller);
}
