package auth;

import java.sql.ResultSet;
import java.sql.SQLException;

import utility.Database;

public class LoginController {
	
	public boolean login ( String username, String password ) {
		ResultSet rs = null;
		try {			
			rs = Database.getConnectionStatement()
						.executeQuery( "SELECT * FROM administrator WHERE username LIKE '" + username + "' AND password = '" + password + "'" );
		} catch (Exception e) {
			e.printStackTrace ( );
		}
		
		try {
			return rs.next();
		} catch ( SQLException e ) {
			return false;
		}
	}
	
}
