package edu.cs.siu.FileIO.JSONSim;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;

import com.google.gson.Gson;

public class JsonParse {
	
	Sim s;
	
	public void parseJson(String fileName) {
		Gson g = new Gson();
		String json = openFile(fileName);
		if(json!=null) {
			this.s = g.fromJson(json, Sim.class);	
		}
		else {
			this.s = null;
		}
	}

	/**
	 * A private function for opening a file from the
	 * hard drive.
	 * 
	 * @param fileName
	 * The location of the file on the hard drive
	 * @return
	 * Returns either the contents of the file in a string
	 * or null if the file can not be opened
	 */
	private String openFile(String fileName) {
		try {
			File f = new File(fileName);
			FileReader fr = new FileReader(f);
			StringBuilder str = new StringBuilder();
			int i;
			while((i=fr.read())!=-1) {
				str.append((char)i);
			}
			fr.close();
			return str.toString();
		} catch(Exception e) {
			return null;
		}
	}
	
	public <T, S, U, V> List<Host> getHosts(Class<T> ram, Class<S> bw, Class<U> vm, Class<V> pe) {
		if(this.s==null) {
			return null;
		}
		ArrayList<Host> result = new ArrayList<Host>();
		List<HostClass> hosts = this.s.getHosts();
		Iterator<HostClass> it = hosts.iterator();
		while(it.hasNext())
			result.addAll(((HostClass)it.next()).convertToHost(result.size(), ram, bw, vm, pe));
		return result;
	}
	
	public DatacenterCharacteristics getDatacenterCharacteristics(List<? extends Host> hosts) {
		return this.s.datacenterCharacteristics.convertToDatacenterCharacteristics(hosts);
	}
	
	public <T,S,U> List<Cloudlet> getCloudlets(Class<T> cpuUtilization, Class<S> ramUtilization, Class<U> bwUtilization) {
		if(this.s == null) {
			return null;
		}
		ArrayList<CloudletClass> cloudlets = this.s.getCloudlets();
		List<Cloudlet> result = new ArrayList<Cloudlet>();
		Iterator<CloudletClass> it = cloudlets.iterator();
		int id = 0;
		while(it.hasNext()) {
			result.add(((CloudletClass)it.next()).convertToCloudlet(id++, cpuUtilization, ramUtilization, bwUtilization));
		}
		return result;
	}
	
	public <T> List<Vm> getVmInstanceTypes(int brokerID,Class<T> cloudletScheduler) {
		List<Vm> result = new ArrayList<Vm>();
		for(int i = 0; i < this.s.vmInstances.length; i++) {
			result.add(this.s.vmInstances[i].convertToVm(i, brokerID, cloudletScheduler));
		}
		return result;
	}
}
