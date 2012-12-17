package edu.siu.cs.JobScheduling.Workflow;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Log;

/**
 * This is a class that represents the weighted dependency adjacency matrix of
 * all the cloudlets along with the cloudlets themselves.
 * @author Crackers
 *
 */
public class Workflow {

	private static int IDGEN = 0; //Used for assigning IDs to Workflows
	private int ID; //The ID of this Workflow
	/**
	 * It is important to note that the order that Cloudlets appear in the ArrayList
	 * is their ID in the dependencyMap. (i.e. cloudlets.get(i) == dependencyMap.get(i,x))
	 */
	private WeightedWorkflowMapping dependencyMap; //The weighted dependency adjacency matrix
	private ArrayList<Cloudlet> cloudlets; //The processes/jobs/cloudlets represented in this workflow
	
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
