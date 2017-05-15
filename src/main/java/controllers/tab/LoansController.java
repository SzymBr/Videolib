package main.java.controllers.tab;

import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.controllers.MainController;
import main.java.dao.LoansDao;
import main.java.obj.Loan;
import main.java.validators.LoansValidator;

public class LoansController {

	@FXML // fx:id="customerId"
	private TextField customerId;

	@FXML // fx:id="movieId"
	private TextField movieId;

	@FXML // fx:id="dateOfLoan"
	private DatePicker dateOfLoan;

	@FXML // fx:id="registerLoan"
	private Button registerLoan;

	@FXML // fx:id="showLoans"
	private Button showLoans;

	@FXML // fx:id="message"
	private Label message;

	@FXML
	private TableView<Loan> viewTable;

	@FXML
	private TableColumn<Loan, Integer> loanIdCol;

	@FXML
	private TableColumn<Loan, Integer> customerIdCol;

	@FXML
	private TableColumn<Loan, Integer> movieIdCol;

	@FXML
	private TableColumn<Loan, String> dateOfLoanCol;

	
	public void init(MainController mainController) {
	}

	
	
	@FXML //fx:id="registerLoan"
	private void registerLoan(ActionEvent event) {
		LoansValidator loansValidator = new LoansValidator();

		// Check values retrieved from fields
		if (loansValidator.isValid(customerId.getText(), movieId.getText(), dateOfLoan.getValue())) {
			
			// Convert picked date to String value
			String date = dateOfLoan.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			// Create a new Loan object from values submitted in fields
			Loan newLoan = new Loan(Integer.parseInt(customerId.getText()), Integer.parseInt(movieId.getText()), date);
			
			if (LoansDao.registerLoan(newLoan)) {
				message.setText("Loan Registered");
				customerId.clear();
				movieId.clear();
			} else {
				message.setText("Check Customer ID and Movie ID");
			}
		} 
		else {
			message.setText(loansValidator.getMessage());
		}
	}

	
	
	@FXML //fx:id="showLoans"
	private void showLoans(ActionEvent event) {
		loanIdCol.setCellValueFactory(new PropertyValueFactory<>("loanId"));
		customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
		movieIdCol.setCellValueFactory(new PropertyValueFactory<>("movieId"));
		dateOfLoanCol.setCellValueFactory(new PropertyValueFactory<>("dateOfLoan"));

		ObservableList<Loan> loans = FXCollections.observableArrayList(LoansDao.getLoans());
		viewTable.setItems(loans);
	}

}
