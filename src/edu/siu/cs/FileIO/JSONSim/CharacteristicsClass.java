package edu.siu.cs.FileIO.JSONSim;

import java.util.List;

import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;

class CharacteristicsClass {

	private String architecture;
	private String os;
	private String vmm;
	private double timeZone;
	private double costPerSec;
	private double costPerMem;
	private double costPerStorage;
	private double costPerBw;
    
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
    
    public DatacenterCharacteristics convertToDatacenterCharacteristics(List<? extends Host> hosts) {
    	return new DatacenterCharacteristics(
    			this.architecture,
    			this.os,
    			this.vmm,
    			hosts,
    			this.timeZone,
    			this.costPerSec,
    			this.costPerMem,
    			this.costPerStorage,
    			this.costPerBw);
    }
}
