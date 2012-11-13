package edu.siu.cs.JobScheduling.Workflow;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Log;

public class Workflow {

	private static int IDGEN = 0;
	private int ID;
	private WeightedWorkflowMapping dependencyMap;
	private ArrayList<Cloudlet> cloudlets;
	
	@SuppressWarnings("unchecked")
	public Workflow(boolean[][] dependencies, List<Cloudlet> cloudlets) {
		if(dependencies == null||cloudlets == null) {
			throw new IllegalArgumentException("Can not have null argument for constructor!");
		}
		this.ID = IDGEN++;
		Log.printLine("Creating Workflow "+ID+"...");
		this.cloudlets = (ArrayList<Cloudlet>)((ArrayList<Cloudlet>) cloudlets).clone();
		this.dependencyMap = new WeightedWorkflowMapping(dependencies,this.cloudlets);
	}
	
	public long getWeight(int i, int j) throws IllegalArgumentException {
		return this.dependencyMap.getValue(i, j);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Cloudlet> getCloudlets() {
		return (ArrayList<Cloudlet>)this.cloudlets.clone();
	}
	
	public Cloudlet getCloudlet(int i) {
		return this.cloudlets.get(i);
	}
	
	/**
	 * @return
	 * Returns the number of Cloudlets this Workflow represents.
	 */
	public int getSize() {
		return this.cloudlets.size();
	}
	
	public int getId() {
		return this.ID;
	}
}
