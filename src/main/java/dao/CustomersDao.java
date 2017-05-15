package main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.obj.Customer;

public class CustomersDao {
	private static String dbURL = "jdbc:derby:MyDbTest";
	private static String tableName = "customers";
	// jdbc Connection
	private static Connection conn = null;
	private static Statement stmt = null;

	public static void createConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			// Get a connection
			conn = DriverManager.getConnection(dbURL);
		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	public static void shutdown() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				DriverManager.getConnection(dbURL + ";shutdown=true");
				conn.close();
			}
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static List<Customer> getCusts() {
		createConnection();
		List<Customer> customers = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName);

			while (results.next()) {
				int customerId = results.getInt(1);
				String firstName = results.getString(2);
				String lastName = results.getString(3);
				String email = results.getString(4);
				Customer customer = new Customer(customerId, firstName, lastName, email);
				customers.add(customer);
			}
			shutdown();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return customers;
	}
	
	public static boolean createCustomer(Customer customer){
		createConnection();
		if (checkForCustomer(customer)){
			return false;
		}
		try {
			stmt = conn.createStatement();
			stmt.execute("insert into " + tableName + " (firstname, lastname, email) values ('" + customer.getFirstName().toLowerCase() 
		                    + "', '" + customer.getLastName() 
		                    + "', '" + customer.getEmail() +"')");
			shutdown();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			return false;
		}
		return true;
    }
	
	public static boolean checkForCustomer(Customer customer){
		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName +
					" WHERE firstname = '" + customer.getFirstName() + "' AND lastname = '" +customer.getLastName() +"'");
			if (results.next()){
				results.close();
	            stmt.close();
	            System.out.println("user exists!!");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
