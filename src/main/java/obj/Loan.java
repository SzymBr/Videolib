package main.java.obj;



import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Loan {
	private SimpleIntegerProperty loanId;
	private SimpleIntegerProperty customerId;
	private SimpleIntegerProperty movieId;
	private SimpleStringProperty dateOfLoan;
	

	public Loan(){	
	}
	
	public Loan(int customerId, int movieId, String dateOfLoan) {
		this.customerId = new SimpleIntegerProperty(customerId);
		this.movieId = new SimpleIntegerProperty(movieId);
		this.dateOfLoan = new SimpleStringProperty(dateOfLoan);
	}
	
	public Loan(int loanId, int customerId, int movieId, String dateOfLoan) {
		this.loanId = new SimpleIntegerProperty(loanId);
		this.customerId = new SimpleIntegerProperty(customerId);
		this.movieId = new SimpleIntegerProperty(movieId);
		this.dateOfLoan = new SimpleStringProperty(dateOfLoan);
	}

	public int getLoanId() {
		return loanId.get();
	}
	public void setLoanId(int loanId) {
		this.loanId.set(loanId);
	}
	public int getCustomerId() {
		return customerId.get();
	}
	public void setCustomerId(int customerId) {
		this.customerId.set(customerId);
	}
	public int getMovieId() {
		return movieId.get();
	}
	public void setMovieId(int loanId) {
		this.movieId.set(loanId);
	}
	public String getDateOfLoan(){
		return dateOfLoan.get();
	}
	public void setDateOfLoan(String dateOfLoan){
		this.dateOfLoan.set(dateOfLoan);
	}
}