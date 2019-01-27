package lab2task2;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	public Database() {}
	
	// JDBC driver name and database URL
 //  private static final String JDBC_DRIVER = "mariadb-java-client.Driver";  
 // (( private static final String DB_URL = "jdbc:mariadb://localhost/";

	// Database name - database should exist
   private static final String DATABASE = "USERDBLAB2";
   // Connection string
   private static final String CONN_STR = "jdbc:mariadb://localhost:3306/" + DATABASE;
   //  Database credentials
   private static final String USER = "lab2";
   private static final String PWD = "lab2";
	
	//Connection
    private static Connection conn = null;
    
    //Connect to DB
    protected static Connection connectMariaDb() {
    	try {
    		// Register driver
    		Driver driver = (Driver) Class.forName("org.mariadb.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			 //Open a connection
	        System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(CONN_STR,USER,PWD);
			return conn;
		} catch (SQLException e) {
			System.err.println("An SQL exception occured when opening connection to MariaBD" + e);
			} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("Connected!");
    	return conn;
    }
    
    
    
  /**
   * Maria db close.
   */
  //Close Connection
   protected static void mariaDbClose() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e){
        	System.err.println("An SQL exception occured when closing connection " + e);
        }
        
        System.out.println("Connection closed!");
    }
   
	/**
	 * Execute query.
	 *
	 * @param query the query
	 * @return the result set
	 */
	//DB Execute Select Query Operation
    public ResultSet executeQuery(String query) {
    	
    	// connect to db
    	connectMariaDb();

    	// statement for query
    	Statement stmt;
    	
    	// resultset for the result
    	ResultSet rs = null;
    	
    	// do querying
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			stmt.close();
			
		} catch (SQLException e) {
			System.err.println("An SQL exception occured when while executing query " + e);
		}
		
		// close db
		mariaDbClose();
		
    	return rs;
    	
    } 
    
    /*
    Statement stmt = null;
   
    try{
    	//Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PWD);
        
        //Execute create database query
        System.out.println("Creating database...");
        stmt = conn.createStatement();
        
        String sql = "CREATE DATABASE USERDBLAB2 IF NOT EXISTS";
        stmt.executeUpdate(sql);
        System.out.println("Database created successfully...");
        
    } catch (SQLException e) { // Handle JDBC exceptions
		System.err.println("An SQL exception occured when creating database " + e);
    } catch(Exception e1){ //Handle Class.forName exceptions 
        e.printStackTrace();
     } finally { // used to close resources
    	 
        try{
           if(stmt!=null)
              stmt.close();
        }catch(SQLException e2){
        }// nothing we can do
        try{
           if(conn!=null)
              conn.close();
        }catch(SQLException se){
           se.printStackTrace();
        }//end finally try
     }//end try
     System.out.println("Goodbye!");
    */

}
