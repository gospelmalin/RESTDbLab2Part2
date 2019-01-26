package lab2task2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

	public List<User> getAllUsers(){    
	      List<User> userList = null; 
	      /*
	      User user = new User(1, "Britta", "Expert"); 
          userList = new ArrayList<User>(); 
          userList.add(user); 
          user = new User(2, "Sanna", "Specialist"); //TODO test
          userList.add(user); //TODO test
          user = new User(3, "Henny", "Specialist"); //TODO test
          userList.add(user); //TODO test
	      */
	      
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      return userList; 
	   } 
	
	/*
	private void saveUserList(List<User> userList){ 
	      try { 
	        File file = new File("Users3.dat"); 
	    	//  File file = new File("Users.txt"); 
	         FileOutputStream fos;  
	         fos = new FileOutputStream(file); 
	         ObjectOutputStream oos = new ObjectOutputStream(fos); 
	         oos.writeObject(userList); 
	         oos.close(); 
	      } catch (FileNotFoundException e) { 
	         e.printStackTrace(); 
	      } catch (IOException e) { 
	         e.printStackTrace(); 
	      } 
	   }  
	*/
}
