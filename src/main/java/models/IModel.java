package models;

import java.sql.SQLException;

public interface IModel {
	public void save ( );
	public void retrieve ( int id ) throws SQLException;
	public void destroy ( );
}
