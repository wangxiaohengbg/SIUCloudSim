package edu.cs.siu.FileIO.JSONSim;

/**
 * A representation of the CloudSim Host class that is used to generate and parse
 * JSON files using Gson. This will be converted to/from an actual CloudSim Host
 * class up stream.
 * 
 * @author Crackers
 */
class HostClass {
	public int quantity; //How many of these hosts exist in the data center
	public int ram; // host memory (MB)
	public long storage; // host storage
	public int bw;
	public int[] processors;
	
	/**
	 * Constructor
	 * 
	 * @param quantity
	 * The number of hosts to create in the simulation that have these hardware
	 * specifications.
	 * @param ram
	 * The amount of ram this host type will have
	 * @param storage
	 * The amount of hard drive space this host will have
	 * @param bw
	 * The amount of bandwidth available to this host
	 * @param processors
	 * An array of integers, each index corresponds to a processor and each index's value
	 * maps to how many MIPS the processor can handle. For example, if you want 2 processors
	 * one with 1000 million instructions per second and the other with 2000, you would pass
	 * the array '[1000,2000]'
	 */
	public HostClass(int quantity, int ram, long storage, int bw, int[] processors) {
		this.quantity = quantity;
		this.ram = ram;
		this.storage = storage;
		this.bw = bw;
		this.processors = processors;
	}

}
