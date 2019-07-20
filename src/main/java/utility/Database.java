package utility;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @return Statement ( JDBC )
	 */
	public static java.sql.Statement getConnectionStatement ( ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		/** Definition of mysql instance for JDBC **/
		// TODO: Remove Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		  
		/** Database connection String **/
		Connection conn = DriverManager.getConnection ( 
				   "jdbc:mysql://" 
						   + Settings.Config.DB_CONNECTION_SERVER + "/" 
						   + Settings.Config.DB_CONNECTION_DBNAME 
						   + "?user=" + Settings.Config.DB_CONNECTION_DBUSERNAME 
						   + "&password=" + Settings.Config.DB_CONNECTION_DBPASSWORD );
		
		return conn.createStatement ( );
	}
	
	public static Connection getConnectionObject ( ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		/** Definition of mysql instance for JDBC **/
		// TODO: Remove Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		  
		/** Database connection String **/
		Connection conn = DriverManager.getConnection ( 
				   "jdbc:mysql://" 
						   + Settings.Config.DB_CONNECTION_SERVER + "/" 
						   + Settings.Config.DB_CONNECTION_DBNAME 
						   + "?user=" + Settings.Config.DB_CONNECTION_DBUSERNAME 
						   + "&password=" + Settings.Config.DB_CONNECTION_DBPASSWORD );
		
		return conn;
	}
}
