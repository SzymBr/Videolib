package main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.obj.Movie;

public class MoviesDao {
	private static String dbURL = "jdbc:derby:MyDbTest";
	private static String tableName = "movies";
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

	public static List<Movie> getMovies() {
		createConnection();
		List<Movie> movies = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName);
			while (results.next()) {
				int movieId = results.getInt(1);
				String title = results.getString(2);
				String director = results.getString(3);
				int year = results.getInt(4);
				// create new student object
				Movie movie = new Movie(movieId, title, director, year);
				// add it to the list of students
				movies.add(movie);
			}
			shutdown();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return movies;
	}

	public static boolean createMovie(Movie movie){
		createConnection();
		if (checkForMovie(movie)){
			return false;
		}
		try {
			stmt = conn.createStatement();	
			stmt.execute("insert into " + tableName + " (title, director, yr) values ('" + movie.getTitle() 
		                    + "', '" + movie.getDirector() 
		                    + "', " + movie.getYear() +")");
			shutdown();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			return false;
		}
		return true;
    }
	
	public static boolean checkForMovie(Movie movie){
		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName +
					" WHERE title = '" + movie.getTitle() + "' AND director = '" +movie.getDirector() +"'");
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
