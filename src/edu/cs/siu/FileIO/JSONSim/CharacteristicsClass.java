package edu.cs.siu.FileIO.JSONSim;

public class CharacteristicsClass {

	public String architecture;
	public String os;
	public String vmm;
	public double timeZone;
	public double costPerSec;
	public double costPerMem;
	public double costPerStorage;
	public double costPerBw;
    
    public CharacteristicsClass(String arch, String os, String vmManager,
    		double timeZone, double costPerSec, double costPerMem,
    		double costPerStorage, double costPerBw) {
    	this.architecture = arch;
    	this.os = os;
    	this.vmm = vmManager;
    	this.timeZone = timeZone;
    	this.costPerSec = costPerSec;
    	this.costPerMem = costPerMem;
    	this.costPerStorage = costPerStorage;
    	this.costPerBw = costPerBw;
    }
    
}
