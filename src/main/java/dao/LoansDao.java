package main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.obj.Loan;

public class LoansDao {
	private static String dbURL = "jdbc:derby:MyDbTest";
	private static String tableName = "loans";
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

	public static List<Loan> getLoans() {
		createConnection();
		List<Loan> loans = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName);

			while (results.next()) {
				int loanId = results.getInt(1);
				int customerId = results.getInt(2);
				int movieId = results.getInt(3);
				String dateOfLoan = results.getDate(4).toString();
				// create new customer object
				Loan loan = new Loan(loanId, customerId, movieId, dateOfLoan);
				// add it to the list of customers
				loans.add(loan);
			}
			shutdown();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return loans;
	}

	public static boolean registerLoan(Loan loan){
		createConnection();
		if (checkForLoan(loan)){
			return false;
		}
		try {
			stmt = conn.createStatement();
			stmt.execute("insert into " + tableName + " (customerid, movieid, dateofloan) values (" + loan.getCustomerId() 
		                    + ", " + loan.getMovieId() 
		                    + ", '" + loan.getDateOfLoan() +"')");
			shutdown();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			return false;
		}
		return true;
    }
	
	public static boolean checkForLoan(Loan loan){
		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName +
					" WHERE customerid = " + loan.getCustomerId() + " AND movieid = " +loan.getMovieId() +"");
			if (results.next()){
				results.close();
	            stmt.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
