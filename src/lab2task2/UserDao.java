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
	      try { 
	         File file = new File("Users3.dat"); 
	    	 // File file = new File("Users.txt"); 
	         if (!file.exists()) { 
	            User user = new User(1, "Tobias", "Teacher"); 
	            userList = new ArrayList<User>(); 
	            userList.add(user); 
	            user = new User(2, "Malin", "Student"); //TODO test
	            userList.add(user); //TODO test
	            user = new User(3, "Holger", "Carpenter"); //TODO test
	            userList.add(user); //TODO test
	            user = new User(4, "Olof", "Plummer"); //TODO test
	            userList.add(user); //TODO test
	       
	            saveUserList(userList); 
	         } 
	         else{ 
	            FileInputStream fis = new FileInputStream(file); 
	            ObjectInputStream ois = new ObjectInputStream(fis); 
	            userList = (List<User>) ois.readObject(); 
	            ois.close(); 
	         } 
	      } catch (IOException e) { 
	         e.printStackTrace(); 
	      } catch (ClassNotFoundException e) { 
	         e.printStackTrace(); 
	      }   
	      return userList; 
	   } 
	
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
	
}
