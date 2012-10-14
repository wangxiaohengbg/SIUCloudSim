package edu.siu.cs.FileIO.JSONSim;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.VmScheduler;
import org.cloudbus.cloudsim.provisioners.BwProvisioner;
import org.cloudbus.cloudsim.provisioners.PeProvisioner;
import org.cloudbus.cloudsim.provisioners.RamProvisioner;

/**
 * A representation of the CloudSim Host class that is used to generate and parse
 * JSON files using Gson. This will be converted to/from an actual CloudSim Host
 * class up stream.
 * 
 * @author Crackers
 */
class HostClass {
	private int quantity; //How many of these hosts exist in the data center
	private int ram; // host memory (MB)
	private long storage; // host storage
	private int bw;
	private int[] processors;
	
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
	
	public <T,S,U,V> List<Host> convertToHost(int id,Class<T> ram, Class<S> bw, Class<U> vm, Class<V> pe) {
		ArrayList<Host> result = new ArrayList<Host>();
		RamProvisioner ramP;
		BwProvisioner bwP;
		VmScheduler vmP;
		List<Pe> peList = new ArrayList<Pe>();
		int peId = 0;
		
		//Convert the generics to provisioning policiesS
		try {
			Constructor<T> ramC = ram.getConstructor(int.class);
			Constructor<S> bwC = bw.getConstructor(long.class);
			Constructor<U> vmC = vm.getConstructor(List.class);
			Constructor<V> peC = pe.getConstructor(double.class);
			//Create the processors to give the VMAllocationPolicy's constructor
			for(int i = 0; i < this.processors.length; i++) {
				peList.add(new Pe(peId++,(PeProvisioner)peC.newInstance(this.processors[i])));
			}
			ramP = (RamProvisioner)ramC.newInstance(this.ram);
			bwP = (BwProvisioner)bwC.newInstance(this.bw);
			vmP = (VmScheduler)vmC.newInstance(peList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		for(int i = 0; i < quantity; i++) {
			result.add(new Host(id++, ramP, bwP, this.storage, peList, vmP));
		}
		
		return result;
	}
}
