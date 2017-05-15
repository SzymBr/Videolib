package main.java.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieValidator {
	private String message;
	private Pattern pattern;
	private Matcher matcher;
	
	public boolean isValid(String title, String director, String year) {
		if(!isValidTitle(title)){
			message = "Invalid title";
			return false;
		}
			
		if(!isValidDirector(director)){
			message = "Invalid director name";
			return false;
		}
		if(!isValidYear(year)){
			message = "Invalid Year";
			return false;
		}
		return true;
	}

	private boolean isValidYear(String year) {
		pattern = Pattern.compile("\\d{4,}");
		matcher = pattern.matcher(year);
		return matcher.find();
	}

	private boolean isValidDirector(String director) {
		pattern = Pattern.compile("[a-zA-Z]{3,}");
		matcher = pattern.matcher(director);
		return matcher.find();
	}

	private boolean isValidTitle(String title) {
		pattern = Pattern.compile(".{3,}");
		matcher = pattern.matcher(title);
		return matcher.find();
	}

	public String getMessage() {
		return message;
	}

}
