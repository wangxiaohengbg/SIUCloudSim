package edu.siu.cs.Hosts;

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
	
	/**
	 * Reserver space on a node from startTime to finishTime for a Vm vm
	 * @param startTime
	 * The time the reservation should start
	 * @param finishTime
	 * The time the reservation expires
	 * @param vm
	 * The vm that space/time is being reserved for
	 */
	public VmReservation(double startTime, double finishTime, Vm vm) {
		if(startTime > finishTime) {
			double temp = startTime;
			finishTime = startTime;
			startTime = temp;
		}
		this.timeStart = startTime;
		this.timeStop = finishTime;
		this.vm = vm;
	}
	
	/**
	 * Get the starting time of the reservation
	 */
	public double getStartTime() {
		return timeStart;
	}
	
	/**
	 * Get the time the reservation expires
	 */
	public double getStopTime() {
		return timeStop;
	}
	
	/**
	 * Get the Vm that the reservation represents
	 */
	public Vm getVm() {
		return vm;
	}
}
