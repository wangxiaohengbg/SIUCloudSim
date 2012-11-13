package edu.siu.cs.JobScheduling.Hosts;

import org.cloudbus.cloudsim.Vm;

/**
 * This class is used to represent a VmReservation on a host machine
 * 
 * @author Crackers
 *
 */
class VmReservation {
	
	private double timeStart;
	private double timeStop;
	private Vm vm;
	private boolean expires; //If this is set to false, the VM reservation never expires
	
	/**
	 * Reserver space on a node from startTime to finishTime for a Vm vm
	 * @param startTime
	 * The time the reservation should start
	 * @param finishTime
	 * The time the reservation expires
	 * @param vm
	 * The vm that space/time is being reserved for
	 * @throws NegativeTimeException 
	 * If startTime or finishTime are negative
	 */
	public VmReservation(double startTime, double finishTime, Vm vm) throws NegativeTimeException {
		if(startTime < 0||finishTime < 0) {
			throw new NegativeTimeException();
		}
		if(startTime > finishTime) {
			double temp = startTime;
			finishTime = startTime;
			startTime = temp;
		}
		this.timeStart = startTime;
		this.timeStop = finishTime;
		this.vm = vm;
		this.expires = true;
	}
	
	/**
	 * Reserver space on a node at startTime for the virtual machine vm, this
	 * reservation never expires
	 * @param startTime
	 * The time the reservation should start
	 * @param vm
	 * The vm that space/time is being reserved for
	 * @throws NegativeTimeException 
	 * If startTime is negative
	 */
	public VmReservation(double startTime, Vm vm) throws NegativeTimeException {
		if(startTime < 0) {
			throw new NegativeTimeException();
		}
		this.timeStart = startTime;
		this.timeStop = -1;
		this.vm = vm;
		this.expires = false;
	}
	
	/**
	 * Get the starting time of the reservation
	 */
	public double getStartTime() {
		return this.timeStart;
	}
	
	/**
	 * Get the time the reservation expires
	 * @return
	 * returns the time this reservation expires or -1 if the reservation does not expire
	 */
	public double getStopTime() {
		if(this.expires)
			return this.timeStop;
		else
			return -1;
	}
	
	/**
	 * Check to see if this vm reservation expires
	 */
	public boolean doesExpire() {
		return this.expires;
	}
	
	/**
	 * Get the Vm that the reservation represents
	 */
	public Vm getVm() {
		return this.vm;
	}
}
