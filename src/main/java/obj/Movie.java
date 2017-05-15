package main.java.obj;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Movie {
	
	private SimpleIntegerProperty movieId;
	private SimpleStringProperty title;
	private SimpleStringProperty director;
	private SimpleIntegerProperty year;
	
	
	public Movie(){
	}
	
	public Movie(String firstName, String lastName, int email) {
		this.title = new SimpleStringProperty(firstName);
		this.director = new SimpleStringProperty(lastName);
		this.year = new SimpleIntegerProperty(email);
	}
	
	public Movie(int customerId, String firstName, String lastName, int year) {
		this.movieId = new SimpleIntegerProperty(customerId);
		this.title = new SimpleStringProperty(firstName);
		this.director = new SimpleStringProperty(lastName);
		this.year = new SimpleIntegerProperty(year);
	}

	public int getMovieId() {
		return movieId.get();
	}
	public void setMovieId(int movieId) {
		this.movieId.set(movieId);
	}
	public String getTitle() {
		return title.get();
	}
	public void setTitle(String title) {
		this.title.set(title);
	}
	public String getDirector() {
		return director.get();
	}
	public void setDirector(String director) {
		this.director.set(director);
	}
	public int getYear() {
		return year.get();
	}
	public void setYear(int year) {
		this.year.set(year);
	}
	
	@Override
	public String toString() {
		return "Customer [cid=" + movieId + ", firstName=" + title + ", lastName=" + director + ", email=" + year + "]";
	}
	
	
	
	
}
