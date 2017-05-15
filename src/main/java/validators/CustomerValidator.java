package main.java.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.obj.Customer;

public class CustomerValidator {
	private String message;
	private Pattern pattern;
	private Matcher matcher;
	
	
	public boolean isValid(Customer customer) {
		
		if(!isValidFirstName(customer.getFirstName())){
			message = "Invalid First Name";
			return false;
		}
		
		if(!isValidLastName(customer.getLastName())){
			message = "Invalid Last Name";
			return false;
		}
		
		if(!isValidEmailAddress(customer.getEmail())){
			message = "Invalid Email";
			return false;
		}

		return true;
	}
	
	public String getMessage() {
		return message;
	}
	
	private boolean isValidFirstName(String firstName) {
		pattern = Pattern.compile("[a-zA-Z]{2,}");
		matcher = pattern.matcher(firstName);
		return matcher.find();	
	}

	private boolean isValidLastName(String lastName) {
		pattern = Pattern.compile("[a-zA-Z]{2,}");
		matcher = pattern.matcher(lastName);
		return matcher.find();
	}
	
	private boolean isValidEmailAddress(String email) {
        pattern = Pattern.compile("^(.+)@(.+)$");
        matcher = pattern.matcher(email);
        return matcher.find();
	}



	
}
