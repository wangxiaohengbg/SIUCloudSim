package edu.cs.siu.FileIO.JSONSim;

import org.cloudbus.cloudsim.CloudletScheduler;
import org.cloudbus.cloudsim.Vm;

class InstanceTypeClass {
	private int mips;
	private long size;
	private int ram;
	private long bw;
	private int pesNumber;

	public InstanceTypeClass(int mips, long size, int ram, long bw, int pesCount) {
		this.mips = mips;
		this.size = size;
		this.ram = ram;
		this.bw = bw;
		this.pesNumber = pesCount;
	}
	
	public <T> Vm convertToVm(int id, int brokerId, Class<T> cloudletScheduler) {
		try {
			CloudletScheduler c = (CloudletScheduler)cloudletScheduler.getConstructor().newInstance();
			return new Vm(id, brokerId,this.mips,this.pesNumber,this.ram,this.bw,this.size,null,c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
