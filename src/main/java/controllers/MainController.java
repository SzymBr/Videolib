package main.java.controllers;


import javafx.fxml.FXML;
import main.java.controllers.tab.CustomersController;

public class MainController  {
	
	@FXML CustomersController customersController;
	
	@FXML public void initialize(){
		customersController.init(this);
	}
}
