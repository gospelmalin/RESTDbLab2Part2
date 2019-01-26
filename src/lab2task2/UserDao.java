package lab2task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

	public List<User> getAllUsers(){    
	      List<User> userList = null; 
	      
	      User user = new User(1, "Britta", "Expert"); 
          userList = new ArrayList<User>(); 
          userList.add(user); 
          user = new User(2, "Sanna", "Specialist"); //TODO test
          userList.add(user); //TODO test
          user = new User(3, "Henny", "Specialist"); //TODO test
          userList.add(user); //TODO test
	      
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
