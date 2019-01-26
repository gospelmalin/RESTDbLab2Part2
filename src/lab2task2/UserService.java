package lab2task2;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Själva webservicen
@Path("/UserService")
public class UserService {
	UserDao userDao = new UserDao();
	 //  private static final String SUCCESS_RESULT="<result>success</result>";
	 //  private static final String FAILURE_RESULT="<result>failure</result>";

	   
	   @GET
	   @Path("/users")
	   @Produces(MediaType.APPLICATION_XML)
	   public List<User> getUsers(){
	      return userDao.getAllUsers();
	   }
}
