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
import main.java.dao.CustomersDao;
import main.java.obj.Customer;
import main.java.validators.CustomerValidator;


public class CustomersController {

	@FXML // fx:id="firstName"
	private TextField firstName;

	@FXML // fx:id="lastName"
	private TextField lastName;

	@FXML // fx:id="email"
	private TextField email;

	@FXML // fx:id="addCustomer"
	private Button addCustomer;

	@FXML // fx:id="showCustomers"
	private Button showCustomers;

	@FXML // fx:id="message"
	private Label message;

	@FXML
	private TableView<Customer> viewTable;

	@FXML
	private TableColumn<Customer, Integer> customerIdCol;

	@FXML
	private TableColumn<Customer, String> firstNameCol;

	@FXML
	private TableColumn<Customer, String> lastNameCol;

	@FXML
	private TableColumn<Customer, String> emailCol;

	
	public void init(MainController mainController) {
	}

	
	
	@FXML // fx:id="addCustomer"
	private void addCustomer(ActionEvent event) {
		CustomerValidator customerValidator = new CustomerValidator();
		
		// Create a new Customer from values submitted in fields
		Customer newCustomer = new Customer(firstName.getText().toLowerCase(), lastName.getText().toLowerCase(),
				email.getText().toLowerCase());
		
		// Check values retrieved from fields by passing Customer object
		if (customerValidator.isValid(newCustomer)) {
			if (CustomersDao.createCustomer(newCustomer)) {
				message.setText("Customer Created");
				firstName.clear();
				lastName.clear();
				email.clear();
			} else {
				message.setText("Customer Already exists!");
			}
		} 
		else {
			message.setText(customerValidator.getMessage());
		}
	}

	
	
	@FXML // fx:id="showCustomers"
	private void showCustomers(ActionEvent event) {
		customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

		ObservableList<Customer> customers = FXCollections.observableArrayList(CustomersDao.getCusts());
		viewTable.setItems(customers);
	}

}
