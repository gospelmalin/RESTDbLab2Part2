package lab2task2;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * The Class WebServiceTester is used to test the webservice.
 * For these tests to work as expected with the below test data, the database should have at least 
 * 2 users (id=1 and id =2, and no user with id=4 is allowed) in the user table before running.
 */
public class WebserviceTester {
	
	 private Client client;
	   private String REST_SERVICE_URL = "http://localhost:8081/RESTDbLab2Part2/rest/UserService/users";
	   private static final String SUCCESS_RESULT="<result>success</result>";
	   private static final String PASS = "pass";
	   private static final String FAIL = "fail";

	   private void init(){
	      this.client = ClientBuilder.newClient();
	   }

	   // For these tests to work as expected with the below test data, the database should have at least 
	   // 2 users (id=1 and id =2, and no user with id=4 is allowed) in the user table before running.
	   public static void main(String[] args){
	      WebserviceTester tester = new WebserviceTester();
	      //initialize the tester
	      tester.init();
	      //test get all users Web Service Method
	      tester.testGetAllUsers();
	      //test get user Web Service Method 
	      tester.testGetUser();
	      //test update user Web Service Method
	      tester.testUpdateUser(); 
	      //test add user Web Service Method
	      tester.testAddUser();
	      //test delete user Web Service Method
	      tester.testDeleteUser();
	   }
	   
	   //Test: Get list of all users
	   //Test: Check if list is not empty
	   private void testGetAllUsers(){
	      GenericType<List<User>> list = new GenericType<List<User>>() {};
	      List<User> users = client
	         .target(REST_SERVICE_URL)
	         .request(MediaType.APPLICATION_XML)
	         .get(list);
	      String result = PASS;
	      if(users.isEmpty()){
	         result = FAIL;
	      }
	      System.out.println("Test case name: testGetAllUsers, Result: " + result );
	   }
	   
	   //Test: Get User of id 1
	   //Test: Check if user is same as sample user
	   private void testGetUser(){
	      User sampleUser = new User();
	      sampleUser.setId(1);

	      User user = client
	         .target(REST_SERVICE_URL)
	         .path("/{userid}")
	         .resolveTemplate("userid", 1)
	         .request(MediaType.APPLICATION_XML)
	         .get(User.class);
	      String result = FAIL;
	      if(sampleUser != null && sampleUser.getId() == user.getId()){
	         result = PASS;
	      }
	      System.out.println("Test case name: testGetUser, Result: " + result );
	   }
	   //Test: Update User of id 1
	   //Test: Check if result is success XML.
	   private void testUpdateUser(){
	      Form form = new Form();
	      form.param("id", "1");
	      form.param("name", "Sanna");
	      form.param("profession", "Dancer");

	      String callResult = client
	         .target(REST_SERVICE_URL)
	         .request(MediaType.APPLICATION_XML)
	         .put(Entity.entity(form,
	            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	            String.class);
	      String result = PASS;
	      if(!SUCCESS_RESULT.equals(callResult)){
	         result = FAIL;
	      }

	      System.out.println("Test case name: testUpdateUser, Result: " + result );
	   }
	   //Test: Add User of id 4
	   //Test: Check if result is success XML.
	   private void testAddUser(){
	      Form form = new Form();
	      form.param("id", "4");
	      form.param("name", "Martinus");
	      form.param("profession", "priest");

	      String callResult = client
	         .target(REST_SERVICE_URL)
	         .request(MediaType.APPLICATION_XML)
	         .post(Entity.entity(form,
	            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	            String.class);
	   
	      String result = PASS;
	      if(!SUCCESS_RESULT.equals(callResult)){
	         result = FAIL;
	      }

	      System.out.println("Test case name: testAddUser, Result: " + result );
	   }
	   //Test: Delete User of id 2
	   //Test: Check if result is success XML.
	   private void testDeleteUser(){
	      String callResult = client
	         .target(REST_SERVICE_URL)
	         .path("/{userid}")
	         .resolveTemplate("userid", 2)
	         .request(MediaType.APPLICATION_XML)
	         .delete(String.class);

	      String result = PASS;
	      if(!SUCCESS_RESULT.equals(callResult)){
	         result = FAIL;
	      }

	      System.out.println("Test case name: testDeleteUser, Result: " + result );
	   }

}
