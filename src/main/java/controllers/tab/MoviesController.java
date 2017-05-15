package main.java.controllers.tab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.controllers.MainController;
import main.java.dao.MoviesDao;
import main.java.obj.Movie;
import main.java.validators.MovieValidator;

public class MoviesController {

	@FXML // fx:id="title"
	private TextField title;

	@FXML // fx:id="director"
	private TextField director;

	@FXML // fx:id="year"
	private TextField year;

	@FXML // fx:id="addMovie"
	private Button addMovie;

	@FXML // fx:id="showMovies"
	private Button showMovies;

	@FXML // fx:id="message"
	private Label message;

	@FXML
	private TableView<Movie> viewTable;

	@FXML
	private TableColumn<Movie, Integer> movieIdCol;

	@FXML
	private TableColumn<Movie, String> titleCol;

	@FXML
	private TableColumn<Movie, String> directorCol;

	@FXML
	private TableColumn<Movie, Integer> yearCol;

	
	public void init(MainController mainController) {
	}

	
	
	@FXML //fx:id="addMovie"
	private void addMovie(ActionEvent event) {
		MovieValidator movieValidator = new MovieValidator();
		
		// Check values retrieved from fields
		if (movieValidator.isValid(title.getText(), director.getText(), year.getText())) {
			
			// Create a new Customer from values submitted in fields
			Movie newMovie = new Movie(title.getText().toLowerCase(), director.getText().toLowerCase(),
					Integer.parseInt(year.getText()));
			
			if (MoviesDao.createMovie(newMovie)) {
				message.setText("Movie added");
				title.clear();
				director.clear();
				year.clear();
			} else {
				message.setText("Movie already exists!");
			}
		} 
		else {
			message.setText(movieValidator.getMessage());
		}

	}

	
	
	@FXML //fx:id="showMovies"
	private void showMovies(ActionEvent event) {
		movieIdCol.setCellValueFactory(new PropertyValueFactory<>("movieId"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		directorCol.setCellValueFactory(new PropertyValueFactory<>("director"));
		yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

		ObservableList<Movie> movies = FXCollections.observableArrayList(MoviesDao.getMovies());
		viewTable.setItems(movies);
	}

}
