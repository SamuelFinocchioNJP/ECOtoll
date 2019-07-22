package autostrada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import models.IModel;
import utility.Database;

/**
 *	Class Autostrada
 *	Model for autostrada relation
 */
public class Autostrada implements IModel {
	private String nome;
	private int id;
	private int iva;
	
	/**
	 * Constructor method that creates a new Autostrada in database
	 * @param nome
	 * @param iva
	 */
	public Autostrada ( String nome, int iva ) {
		this.nome = nome;
		this.iva = iva;
		
		this.save();
	}
	
	/**
	 * Constructor method
	 * @param id
	 */
	public Autostrada ( int id ) {
		this.id = id;
		this.retrieve( id );
	}

	/**
	 * Getter for nome
	 * @return nome
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Getter for id
	 * @return id
	 */
	public int getId ( ) {
		return this.id;
	}
	
	/**
	 * Getter for iva
	 * @return iva
	 */
	public int getIva( ) {
		return this.iva;
	}

	/**
	 * Setter for nome
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @Override
	 * Saves current object to the database
	 */
	public void save ( ) {
		/***
		 * DB Schema for Autostrada:
		 *  id BIGINT AUTO_INCREMENT,
      	 *	nome VARCHAR ( 255 ) NOT NULL,
      	 *  iva NOT NULL,
      	 *  PRIMARY KEY ( id )
		 */
		
		/** Acknowledgement of previous existence of the same instance of autostrada **/
		if ( this.id == 0 ) {
			try {
				ResultSet rs = Database.getConnectionStatement ( ).executeQuery ( "SELECT id FROM autostrada WHERE nome='" + this.getNome ( ) + "' LIMIT 1" );
				
				/** If result set is empty, go for insert query **/
				if ( rs.next() == false ) {
					PreparedStatement preparedStatement = Database.getConnectionObject().prepareStatement ( "INSERT INTO autostrada ( nome, iva )"
							+ " VALUES ('" + this.getNome() + "','" + this.getIva() + "')", Statement.RETURN_GENERATED_KEYS );
					
					if ( preparedStatement.executeUpdate() != 0 ) {
						this.save();
					} else {
						throw new Exception ( "Cant save Autostada exception!" );
					}
				} else {
					/// Result found in query
					this.id = rs.getInt ( "id" );
					System.out.println( "UPDATE autostrada SET iva = '" + this.getIva ( ) + "' WHERE id=" + this.id  );
					Database.getConnectionStatement().executeUpdate ( "UPDATE autostrada SET iva = '" + this.getIva ( ) + "' WHERE id=" + this.id  );
				}
			    
				this.retrieve ( id );
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				Database.getConnectionStatement().executeUpdate ( "UPDATE autostrada SET nome = '" + this.getNome ( ) + "', iva = '" + this.getIva ( ) + "' WHERE id=" + this.id  );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Populates current object with database informations
	 * @Override
	 */
	public void retrieve ( int id ) {
		
		try {
			ResultSet rs = Database.getConnectionStatement().executeQuery ( "SELECT id, nome, iva FROM autostrada WHERE id='" + id + "' LIMIT 1" );
			
			/** If result set is empty, go for insert query **/
			if ( rs.next() == false ) {
				throw new Exception ( "Autostrada not found Exception" );
			} else {
				/// Result found in query
				this.nome  = rs.getString ( "nome" );
				this.iva  = rs.getInt ( "iva" );
				this.id = rs.getInt ( "id" );
			}
		    
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 
	 * Setter for iva
	 * @param iva
	 */
	public void setIva ( int iva ) {
		if ( iva > 0 )
			this.iva = iva;
	}

	/**
	 * Deletes current object from database
	 */
	public void destroy ( ) {
		try {
			Database.getConnectionStatement().executeUpdate ( "DELETE FROM autostrada WHERE nome='" + this.getNome() + "'" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
