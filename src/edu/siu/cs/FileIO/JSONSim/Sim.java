package edu.siu.cs.FileIO.JSONSim;

import java.util.ArrayList;

/**
 * This class is a datastructure containing a simplfied representation
 * of a datacenter for a CloudSim simulation. Its main purpose is to
 * be used in creating and parsing JSON files. It contains simplified
 * versions of the following classes:<br>
 * Host<br>
 * VM<br>
 * Cloudlet<br>
 * DatacenterCharacteristics
 * @author Crackers
 */
class Sim {
	public CharacteristicsClass datacenterCharacteristics;
	private ArrayList<HostClass> hosts;
	public InstanceTypeClass[] vmInstances;
	private ArrayList<CloudletClass> cloudlets;
	
	public void addHosts(HostClass[] hosts) {
		if(this.hosts == null) {
			this.hosts = new ArrayList<HostClass>();
		}
		for(int i = 0; i < hosts.length; i++) {
			this.hosts.add(hosts[i]);
		}
	}
	
	public void addCloudlets(CloudletClass[] cloudlets) {
		if(this.cloudlets == null) {
			this.cloudlets = new ArrayList<CloudletClass>();
		}
		for(int i = 0; i < cloudlets.length; i++) {
			this.cloudlets.add(cloudlets[i]);
		}
	}
	
	public ArrayList<HostClass> getHosts() {
		return this.hosts;
	}
	
	public ArrayList<CloudletClass> getCloudlets() {
		return this.cloudlets;
	}
}
