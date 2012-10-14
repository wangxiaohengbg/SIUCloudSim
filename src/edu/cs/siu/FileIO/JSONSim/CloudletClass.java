package edu.cs.siu.FileIO.JSONSim;

import java.lang.reflect.Constructor;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;

class CloudletClass {
	public long MillionsOfInstructions;
	public int Processors;
	public long inputFileSize;
	public long outputFileSize;
	
	public CloudletClass(long length, int pes, long fileSize, long outputSize) {
		this.MillionsOfInstructions = length;
		this.Processors = pes;
		this.inputFileSize = fileSize;
		this.outputFileSize = outputSize;
	}
	
	/*
	 * Cloudlet(
	 * int cloudletId,
	 * long cloudletLength,
	 * int pesNumber,
	 * long cloudletFileSize,
	 * long cloudletOutputSize,
	 * UtilizationModel utilizationModelCpu,
	 * UtilizationModel utilizationModelRam,
	 * UtilizationModel utilizationModelBw)
	 */
	public <T,S,U> Cloudlet convertToCloudlet(int id, Class<T> cpuUtilization, Class<S> ramUtilization, Class<U> bwUtilization) {
		UtilizationModel cpuU;
		UtilizationModel ramU;
		UtilizationModel bwU;
		
		try{
			Constructor<T> cpuC = cpuUtilization.getConstructor();
			Constructor<S> ramC = ramUtilization.getConstructor();
			Constructor<U> bwC = bwUtilization.getConstructor();
			cpuU = (UtilizationModel) cpuC.newInstance();
			ramU = (UtilizationModel) ramC.newInstance();
			bwU = (UtilizationModel) bwC.newInstance();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return new Cloudlet(id, this.MillionsOfInstructions,this.Processors,this.inputFileSize,this.outputFileSize,cpuU,ramU,bwU);
	}
}
