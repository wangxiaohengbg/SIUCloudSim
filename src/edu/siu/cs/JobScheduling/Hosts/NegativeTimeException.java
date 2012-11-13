package edu.siu.cs.JobScheduling.Hosts;

public class NegativeTimeException extends Exception {

	private static final long serialVersionUID = 6765731728933495595L;
	
	public NegativeTimeException() {
		super("Attempted to make a VM Reservation with a negative start/finish time");
	}

}
