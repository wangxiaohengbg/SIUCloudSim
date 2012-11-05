package edu.siu.cs.FileIO.WeightedWorkflowMapping;

import java.util.ArrayList;
import java.util.Iterator;

import org.cloudbus.cloudsim.Cloudlet;

/**
 * This data structure is the mapping of an ArrayList of cloudlets
 * mapped onto a dependency adjacency matrix. The result of this is
 * a weighted dependency adjacency matrix that will be used in
 * CloudSim. The weight from j to i represents the amount of data
 * that will be transfered from j to i when running the simulation.
 * The value of w[i][j] is pulled from the amount of data output by
 * cloudlet j. Therefore it is assumed 100% of the data generated
 * by j will be transfered to i. All cloudlets will be stored in
 * this mapping data structure to prevent re-organization outside
 * of the data structure. From this point forward, you should
 * refer to this class (or any class that extends it) when
 * interacting with the ArrayList of cloudlets.
 * 
 * @author tlchack5
 *
 */
public class WeightedWorkflowMapping {

	private long[][] dependencyMap;
	private ArrayList<Cloudlet> cloudlets;
	
	
	@SuppressWarnings("unchecked")
	public WeightedWorkflowMapping(boolean[][] adjacencyMatrix, ArrayList<Cloudlet> cloudlets) {
		this.cloudlets = (ArrayList<Cloudlet>)cloudlets.clone();
		if(this.cloudlets.size()>0) {
			this.dependencyMap = new long[this.cloudlets.size()][this.cloudlets.size()];
			//Make sure the adjacency matrix is populated
			if(!(adjacencyMatrix.length>0)||!(adjacencyMatrix[0].length>0))
				return;
			//Copy values from cloudlets into matrix
			Iterator<Cloudlet> it = this.cloudlets.iterator();
			for(int j = 0; j < this.dependencyMap[0].length;j++) {
				if(j<adjacencyMatrix[0].length) {
					long weight = it.next().getCloudletOutputSize();
					for(int i = 0; i < adjacencyMatrix.length;i++) {
						if(adjacencyMatrix[i][j]) {
							this.dependencyMap[i][j] = weight;
						}
					}
				} else {
					break;
				}
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Cloudlet> getCloudletList() {
		return (ArrayList<Cloudlet>)this.cloudlets.clone();
	}

	public long[][] getMap() {
		return this.dependencyMap.clone();
	}
}
