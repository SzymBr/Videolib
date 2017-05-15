package main.java.validators;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class LoansValidator {
	private String message;
	private Pattern pattern;
	private Matcher matcher;

	
	public boolean isValid(String customerId, String movieId, LocalDate date) {
		if(!isValidCustomerId(customerId)){
			message = "Invalid Customer ID";
			return false;
		}
			
		if(!isValidMovieId(movieId)){
			message = "Invalid Movie ID";
			return false;
		}
		if(date == null){
			message = "Pick date";
			return false;
		}
		
		return true;
	}

	
	private boolean isValidCustomerId(String customerId) {
		pattern = Pattern.compile("\\d+");
		matcher = pattern.matcher(customerId);
		return matcher.find();
	}

	private boolean isValidMovieId(String movieId) {
		pattern = Pattern.compile("\\d+");
		matcher = pattern.matcher(movieId);
		return matcher.find();
	}


	public String getMessage() {
		return message;
	}
	

	

}
