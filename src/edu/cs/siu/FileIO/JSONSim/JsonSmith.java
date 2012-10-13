package edu.cs.siu.FileIO.JSONSim;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class is an object oriented approach to generating JSON definitions
 * of the following CloudSim simulation entities: <br>
 * Host <br>
 * VM <br>
 * Cloudlet <br>
 * 
 * @author Crackers
 *
 */
public class JsonSmith {

	//Default values for generating hosts
	private int Hostquantity = 20;
	private int HOSTminRam = 512;
	private int HOSTmaxRam = 8192;
	private int HOSTramInterval = 512;
	private long HOSTminStorage = 10000;
	private long HOSTmaxStorage = 100000;
	private long HOSTstorageInterval = 5000;
	private int HOSTminBW = 1000;
	private int HOSTmaxBW = 20000;
	private int HOSTbwInterval = 1000;
	private int HOSTminPe = 1;
	private int HOSTmaxPe = 4;
	private int HOSTpeInterval = 1;
	private int HOSTminMips = 1000;
	private int HOSTmaxMips = 2000;
	private int HOSTmipsInterval = 500;
	
	//Default values for generating Cloudlets
	private long CLOUDLETminLength = 10000;
	private long CLOUDLETmaxLength = 100000;
	private long CLOUDLETlengthInterval = 1;
	private int CLOUDLETminPES = 1;
	private int CLOUDLETmaxPES = 2;
	private int CLOUDLETpesInterval = 1;
	private long CLOUDLETminFileSize = 10000;
	private long CLOUDLETmaxFileSize = 30000;
	private long CLOUDLETfileSizeInterval = 1;
	private long CLOUDLETminOutputSize = 10000;
	private long CLOUDLETmaxOutputSize = 30000;
	private long CLOUDLEToutputSizeInterval = 1;
	
	//Global sim value, will be written to/from the JSON file
	Sim s = new Sim();
	
	/**
	 * Generate a JSON file with the current objects configuration
	 * 
	 * @param filename
	 * The file to store the generate JSON in
	 * 
	 * @return
	 * The Sim data structure that was used to generate the JSON file
	 */
	public Sim GenerateJson(String filename) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		String json = g.toJson(s);
		try {
			writeToFile(json,filename);
			return s;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @return
	 * An array of 4 VMs that are modeled after Amazon's Cloud Service's
	 * std VMs.
	 */
	private InstanceTypeClass[] getAmazonStdInstances() {
		InstanceTypeClass result[] = new InstanceTypeClass[4];
		result[0] = new InstanceTypeClass(1200, 1000, (int)(1.7*1024), (160*1024), 1);
		result[1] = new InstanceTypeClass(2400, 1000, (int)(3.75*1024), (410*1024), 2);
		result[2] = new InstanceTypeClass(3000, 1000, (int)(7.5*1024), (850*1024), 4);
		result[3] = new InstanceTypeClass(3000, 1000, (int)(15*1024), (1690*1024), 8);
		return result;
	}
	
	/**
	 * Include default instance types in the JSON file
	 */
	public void useAmazonInstances() {
		this.s.vmInstances = this.getAmazonStdInstances();
	}
	
	/**
	 * An internal function that writes a JSON string to a file
	 * @param JSON
	 * JSON text
	 * @param FileName
	 * The file to output too
	 * @throws IOException
	 */
	private void writeToFile(String JSON, String FileName) throws IOException {
		try {
			File output = new File(FileName);
			if(!output.exists()) {
				output.createNewFile();
			} else {
				output.delete();
				output.createNewFile();
			}
			FileWriter file = new FileWriter(output);
			file.write(JSON);
			file.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generates a Random Host specified by the global variables
	 * at the top of the class.
	 * @return
	 * A randomly generated HostClass representing a CloudSim Host
	 * data structure
	 */
	private HostClass RandomHost() {
		int ram = randomNumber(this.HOSTminRam, this.HOSTmaxRam, this.HOSTramInterval);
		long storage = randomNumber(this.HOSTminStorage, this.HOSTmaxStorage, this.HOSTstorageInterval);
		int bw = randomNumber(this.HOSTminBW, this.HOSTmaxBW, this.HOSTbwInterval);
		int peCount = randomNumber(this.HOSTminPe,this.HOSTmaxPe,this.HOSTpeInterval);
		int[] processors = new int[peCount];
		for(int i = 0; i < peCount; i++) {
			processors[i] = randomNumber(this.HOSTminMips,this.HOSTmaxMips,this.HOSTmipsInterval);
		}
		return new HostClass(this.Hostquantity, ram, storage, bw, processors);
	}
	
	/**
	 * Set all of the values used to randomly generate a HostClass
	 * 
	 * @param quantity
	 * The number of exact duplicates of this host type to create
	 * 
	 * @param minRam
	 * The minimum amount of RAM a randomly generated host can have
	 * @param maxRam
	 * The maximum amount of RAM a randomly generated host can have.
	 * @param ramInterval
	 * The interval between RAM sizes randomly assigned to a host
	 * (i.e. randomly generate RAM size between 512 and 2048 will
	 * generate a RAM size from the set [512,1024,1536,2048]).
	 * @param minStorage
	 * The minimum amount of hard drive space a randomly generated host
	 * can have.
	 * @param maxStorage
	 * The maximum amount of hard drive space a randomly generated host
	 * can have.
	 * @param storageInterval
	 * The interval between storage sizes randomly assigned to a host
	 * @param minBW
	 * The minimum amount of bandwidth a randomly generated host can have.
	 * @param maxBW
	 * The maximum amount of bandwidth a randomly generated host can have.
	 * @param bwInterval
	 * The interval between bandwidth sizes randomly assigned to a host
	 * @param minPe
	 * The minimum amount of Processors a randomly generated host can have.
	 * @param maxPe
	 * The maximum amount of Processors a randomly generated host can have.
	 * @param peInterval
	 * The interval between Processors randomly assigned to a host
	 * @param minMips
	 * The minimum Million Instruction Per Second a processor in a randomly
	 * randomly generated host can have.
	 * @param maxMips
	 * The maximum Million Instruction Per Second a processor in a randomly
	 * randomly generated host can have.
	 * @param mipsInterval
	 * The interval between MIPS randomly assigned to a processor in a host
	 */
	public void setHostValues(int quantity, int minRam, int maxRam, int ramInterval,
			long minStorage, long maxStorage, long storageInterval,
			int minBW, int maxBW, int bwInterval,
			int minPe, int maxPe, int peInterval,
			int minMips, int maxMips, int mipsInterval) {
		this.Hostquantity = 10;
		this.HOSTminRam = minRam;
		this.HOSTmaxRam = maxRam;
		this.HOSTramInterval = ramInterval;
		this.HOSTminStorage = minStorage;
		this.HOSTmaxStorage = maxStorage;
		this.HOSTstorageInterval = storageInterval;
		this.HOSTminBW = minBW;
		this.HOSTmaxBW = maxBW;
		this.HOSTbwInterval = bwInterval;
		this.HOSTminPe = minPe;
		this.HOSTmaxPe = maxPe;
		this.HOSTpeInterval = peInterval;
		this.HOSTminMips = minMips;
		this.HOSTmaxMips = maxMips;
		this.HOSTmipsInterval = mipsInterval;
	}
	
	/**
	 * The number of exact duplicates of this host type to create
	 * @param quantity
	 * The number of exact duplicates of this host type to create
	 */
	public void setHostQuantity(int quantity) {
		this.Hostquantity = quantity;
	}
	
	/**
	 * Set the parameters for randomly generating RAM values for a host
	 * 
	 * @param minRam
	 * The minimum amount of RAM a randomly generated host can have
	 * @param maxRam
	 * The maximum amount of RAM a randomly generated host can have.
	 * @param ramInterval
	 * The interval between RAM sizes randomly assigned to a host
	 * (i.e. randomly generate RAM size between 512 and 2048 will
	 * generate a RAM size from the set [512,1024,1536,2048]).
	 */
	public void setHostRam(int min, int max, int interval) {
		if(min>max) {
			int temp = min;
			min = max;
			max = temp;
		}
		this.HOSTminRam = min;
		this.HOSTmaxRam = max;
		this.HOSTramInterval = interval;
	}
	
	/**
	 * Set the exact amount of RAM to give to a generated host
	 * @param RAM
	 * The amount of RAM that will be given to a generated host
	 */
	public void setHostRam(int RAM) {
		this.HOSTminRam = RAM;
		this.HOSTmaxRam = RAM;
		this.HOSTramInterval = 1;
	}
	
	/**
	 * Set the parameters for randomly generating hard drive space for
	 * hosts
	 * 
	 * @param minStorage
	 * The minimum amount of hard drive space a randomly generated host
	 * can have.
	 * @param maxStorage
	 * The maximum amount of hard drive space a randomly generated host
	 * can have.
	 * @param storageInterval
	 * The interval between storage sizes randomly assigned to a host
	 */
	public void setHostStorage(long min, long max, long interval) {
		if(min>max) {
			long temp = min;
			min = max;
			max = temp;
		}
		this.HOSTminStorage = min;
		this.HOSTmaxStorage = max;
		this.HOSTstorageInterval = interval;
	}
	
	/**
	 * Set the exact amount of hard drive space to be allocated to a
	 * randomly generated host.
	 * @param HDD
	 * The amount of hard drive space to be allocated to a randomly
	 * generated host.
	 */
	public void setHostStorage(long HDD) {
		this.HOSTminStorage = HDD;
		this.HOSTmaxStorage = HDD;
		this.HOSTstorageInterval = 1;
	}
	
	/**
	 * Set the parameters for randomly allocating bandwidth to generated
	 * hosts.
	 * 
	 * @param minBW
	 * The minimum amount of bandwidth a randomly generated host can have.
	 * @param maxBW
	 * The maximum amount of bandwidth a randomly generated host can have.
	 * @param bwInterval
	 * The interval between bandwidth sizes randomly assigned to a host
	 */
	public void setHostBW(int min, int max, int interval) {
		if(min>max) {
			int temp = min;
			min = max;
			max = temp;
		}
		this.HOSTminBW = min;
		this.HOSTmaxBW = max;
		this.HOSTbwInterval = interval;
	}
	
	/**
	 * Set the exact amount of bandwidth to allocate to a randomly generated
	 * host.
	 * @param BW
	 * An integer value representing bandwidth.
	 */
	public void setHostBw(int BW) {
		this.HOSTminBW = BW;
		this.HOSTmaxBW = BW;
		this.HOSTbwInterval = 1;
	}
	
	/**
	 * Set the parameters for generating processors that will be assigned
	 * to a randomly generated host.
	 * 
	 * @param minPe
	 * The minimum amount of Processors a randomly generated host can have.
	 * @param maxPe
	 * The maximum amount of Processors a randomly generated host can have.
	 * @param peInterval
	 * The interval between Processors randomly assigned to a host
	 */
	public void setHostProcessors(int min, int max, int interval) {
		if(min>max) {
			int temp = min;
			min = max;
			max = temp;
		}
		this.HOSTminPe = min;
		this.HOSTmaxPe = max;
		this.HOSTpeInterval = interval;
	}
	
	/**
	 * Set the exact number of processors a randomly generated host will
	 * have.
	 * @param PE
	 * An integer value representing the quantity of processors given to
	 * hosts.
	 */
	public void setHostProcessors(int PE) {
		this.HOSTminPe = PE;
		this.HOSTmaxPe = PE;
		this.HOSTpeInterval = 1;
	}
	
	/**
	 * Set the parameters for randomly generating the speed of the generated
	 * processors assigned to a randomly generated host.
	 * 
	 * @param minMips
	 * The minimum Million Instruction Per Second a processor in a randomly
	 * randomly generated host can have.
	 * @param maxMips
	 * The maximum Million Instruction Per Second a processor in a randomly
	 * randomly generated host can have.
	 * @param mipsInterval
	 * The interval between MIPS randomly assigned to a processor in a host
	 */
	public void setHostPeMIPS(int min, int max, int interval) {
		if(min>max) {
			int temp = min;
			min = max;
			max = temp;
		}
		this.HOSTminMips = min;
		this.HOSTmaxMips = max;
		this.HOSTmipsInterval = interval;
	}
	
	/**
	 * Set the exact speed of a randomly generated processor assigned to a host.
	 * @param MIPS
	 */
	public void setHostPeMIPS(int MIPS) {
		this.HOSTminMips = MIPS;
		this.HOSTmaxMips = MIPS;
		this.HOSTmipsInterval = 1;
	}
	
	/**
	 * Generate a set of hosts and store them internally in this class.
	 * When GenerateJson() is called, it will use the data stored internally
	 * to this class created by these functions to generate the JSON file.
	 * The hosts generated by this function will be generated using the
	 * parameters set prior to calling this function.
	 * @param quantity
	 */
	public void generateHosts(int quantity) {
		HostClass hosts[] = new HostClass[quantity];
		for(int i = 0; i < quantity; i++) {
			hosts[i] = this.RandomHost();
		}
		this.s.addHosts(hosts);
	}
	
	/**
	 * Generate a random cloudlet based off of the current configuration of 
	 * this object.
	 * @return
	 * A randomly generated cloudlet.
	 */
	private CloudletClass RandomCloudlet() {
		long length = randomNumber(CLOUDLETminLength,CLOUDLETmaxLength,CLOUDLETlengthInterval);
		int pes = randomNumber(CLOUDLETminPES,CLOUDLETmaxPES,CLOUDLETpesInterval);
		long fileSize = randomNumber(CLOUDLETminFileSize,CLOUDLETmaxFileSize,CLOUDLETfileSizeInterval);
		long outputSize = randomNumber(CLOUDLETminOutputSize,CLOUDLETmaxOutputSize,CLOUDLEToutputSizeInterval);
		return new CloudletClass(length,pes,fileSize,outputSize);
	}
	

	public void setCloudletValues(long minLength, long maxLength, long lengthInterval,
			int minPES, int maxPES, int pesInterval,
			long minFileSize, long maxFileSize, long fileSizeInterval,
			long minOutputSize, long maxOutputSize,	long outputSizeInterval) {
				this.CLOUDLETminLength = minLength;
				this.CLOUDLETmaxLength = maxLength;
				this.CLOUDLETlengthInterval = lengthInterval;
				this.CLOUDLETminPES = minPES;
				this.CLOUDLETmaxPES = maxPES;
				this.CLOUDLETpesInterval = pesInterval;
				this.CLOUDLETminFileSize = minFileSize;
				this.CLOUDLETmaxFileSize = maxFileSize;
				this.CLOUDLETfileSizeInterval = fileSizeInterval;
				this.CLOUDLETminOutputSize = minOutputSize;
				this.CLOUDLETmaxOutputSize = maxOutputSize;
				this.CLOUDLEToutputSizeInterval = outputSizeInterval;
	}
	
	public void generateCloudlets(int quantity) {
		CloudletClass cloudlets[] = new CloudletClass[quantity];
		for(int i = 0; i < quantity; i++) {
			cloudlets[i] = this.RandomCloudlet();
		}
		this.s.addCloudlets(cloudlets);
	}
	
	private int randomNumber(int minValue, int maxValue, int interval) {
		int values = (maxValue-minValue+1)/interval;
		if(interval>1)
			values++;
		return minValue + ((int)(Math.random()*2147483647)%values)*interval;
	}
	
	private long randomNumber(long minValue, long maxValue, long interval) {
		long values = (maxValue-minValue+1)/interval;
		if(interval>1)
			values++;
		return minValue + ((long)(Math.random()*9223372036854775807l)%values)*interval;
	}
}
