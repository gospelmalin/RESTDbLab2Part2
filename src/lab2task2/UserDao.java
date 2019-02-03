package lab2task2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class UserDao handles the queries for the database, isolating the database from the business layer.
 */
public class UserDao {

	/**
    * Show all users
    *
    */
	public List<User> getAllUsers(){    
	      List<User> userList = null; 
	      
	      userList = new ArrayList<User>();
	      Database db = new Database();
	      try {
	      String query = "SELECT * from user;";
	      ResultSet rs = db.executeQuery(query);
	      	     
			while(rs.next()) {
				  int id = rs.getInt(1);
				  String name = rs.getString(2);
				  String profession = rs.getString(3);
				  userList.add(new User(id, name, profession));
			  }
		} catch (SQLException e) {
			System.err.println("An SQL exception occured when trying to get all users from database " + e);
		}	      
	      return userList; 
	   } 
	
	/**
    * Show information about selected user
    *
    */
	public User getUser(int id){
		
		  List<User> users = getAllUsers();

	      for(User user: users){
	         if(user.getId() == id){
	            return user;
	         }
	      }
	      return null;
	   }
	
	/**
	 * Add a new user
	 *
	 */
	public int addUser(User pUser){
	      List<User> userList = getAllUsers();
	      boolean userExists = false;
	      for(User user: userList){
	         if(user.getId() == pUser.getId()){
	            userExists = true;
	            break;
	         }
	      }		
	      if(!userExists){
	         userList.add(pUser);
	         // Setup query
	         String query = "INSERT INTO USER(id, name, profession) VALUES(?,?,?);";
	         Connection conn = Database.connectMariaDb();
	         try {
				// Setup statement
				 PreparedStatement stmt = conn.prepareStatement(query);
     
				 // Set values
				stmt.setInt(1, pUser.getId());
				stmt.setString(2, pUser.getName());
				stmt.setString(3, pUser.getProfession());
				
				// Execute statement
				stmt.executeUpdate();
				
				// Closing statement and connection
				stmt.close();
				Database.mariaDbClose();
			} catch (SQLException e) {
				System.err.println("An SQL exception occured when trying to add user " + e);
			}		      
	         return 1;
	      }
	      return 0;
	   }
	
	/**
	 * Update user
	 *
	 */
	public int updateUser(User pUser){
	      List<User> userList = getAllUsers();

	      for(User user: userList){
	         if(user.getId() == pUser.getId()){
	            int index = userList.indexOf(user);			
	            userList.set(index, pUser);
	            String query = "UPDATE USER SET name = ?,  profession = ? WHERE id = ?;";
		         Connection conn = Database.connectMariaDb();
		         try {
					// Setup statement
					 PreparedStatement stmt = conn.prepareStatement(query);
	     
					// Set values	
					stmt.setString(1, pUser.getName());
					stmt.setString(2, pUser.getProfession());
					stmt.setInt(3, pUser.getId());
					
					// Execute statment
					stmt.executeUpdate();
					
					// Closing statement and connection
					stmt.close();
					Database.mariaDbClose();
				} catch (SQLException e) {
					System.err.println("An SQL exception occured when trying to update user " + e);
				}        
	            return 1;
	         }
	      }		
	      return 0;
	   }
	
	/**
	 * Delete user
	 *
	 */
	public int deleteUser(int id){
	      List<User> userList = getAllUsers();

	      for(User user: userList){
	         if(user.getId() == id){
	            int index = userList.indexOf(user);			
	            userList.remove(index);
	            String query = "DELETE FROM USER WHERE id=?;";
		         Connection conn = Database.connectMariaDb();
		         try {
					// Setup statement
					 PreparedStatement stmt = conn.prepareStatement(query);
	     
					// Set values					
					stmt.setInt(1, id);
					
					// Execute statment
					stmt.executeUpdate();
					
					// Closing statement and connection
					stmt.close();
					Database.mariaDbClose();
				} catch (SQLException e) {
					System.err.println("An SQL exception occured when trying to delete user " + e);
				}
	            return 1;   
	         }
	      }		
	      return 0;
	   }
	

}
