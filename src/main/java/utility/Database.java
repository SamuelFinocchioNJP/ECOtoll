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
						   + settings.Config.DB_CONNECTION_SERVER + "/" 
						   + settings.Config.DB_CONNECTION_DBNAME 
						   + "?useSSL=false" 
						   + "&user=" + settings.Config.DB_CONNECTION_DBUSERNAME 
						   + "&password=" + settings.Config.DB_CONNECTION_DBPASSWORD 
						  );
		
		return conn.createStatement ( );
	}
	
	public static Connection getConnectionObject ( ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		/** Definition of mysql instance for JDBC **/
		// TODO: Remove Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		  
		/** Database connection String **/
		Connection conn = DriverManager.getConnection ( 
				   "jdbc:mysql://" 
						   + settings.Config.DB_CONNECTION_SERVER + "/" 
						   + settings.Config.DB_CONNECTION_DBNAME 
						   + "?user=" + settings.Config.DB_CONNECTION_DBUSERNAME 
						   + "&password=" + settings.Config.DB_CONNECTION_DBPASSWORD );
		
		return conn;
	}
}
