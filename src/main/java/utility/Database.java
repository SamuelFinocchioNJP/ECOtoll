package utility;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Settings.Config;

public class Database {
	/**
	 * Singleton class to deploy JDBC Statement object
	 * 
	 * Usage:
	 * ResultSet resultSet = Database.getConnection( ).executeQuery ( "SELECT ...." );
	 *
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException
	 * @return Statement ( JDBC )
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public java.sql.Statement getConnection ( ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		/// Definition of mysql instance for JDBC
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		  
		/// Database connection String
		Connection conn =
			       DriverManager.getConnection ( 
			    		   "jdbc:mysql://" + Config.DB_CONNECTION_DBNAME + "/"
			    		   + Config.DB_CONNECTION_DBNAME + "?" 
			    		   + "user=" + Config.DB_CONNECTION_DBUSERNAME 
			    		   + "&password=" + Config.DB_CONNECTION_DBPASSWORD );
		
		return conn.createStatement ( );
	}
}
