package Models;

import java.sql.SQLException;

public interface ModelInterface {
	public void save ( );
	public void retrieve ( int id ) throws SQLException;
	public void destroy ( );
}
