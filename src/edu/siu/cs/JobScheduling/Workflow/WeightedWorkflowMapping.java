package edu.siu.cs.JobScheduling.Workflow;

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
 * @author Crackers
 *
 */
class WeightedWorkflowMapping {

	private long[][] dependencyMap;

	public WeightedWorkflowMapping(boolean[][] adjacencyMatrix, ArrayList<Cloudlet> cloudlets) {
		if(cloudlets.size()>0) {
			this.dependencyMap = new long[cloudlets.size()][cloudlets.size()];
			//Make sure the adjacency matrix is populated
			if(!(adjacencyMatrix.length>0)||!(adjacencyMatrix[0].length>0))
				return;
			//Copy values from cloudlets into matrix
			Iterator<Cloudlet> it = cloudlets.iterator();
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
		} else {
			this.dependencyMap = new long[0][0];
		}
	}

	/**
	 * I = child
	 * J = parent
	 * @param i
	 * @param j
	 * @return
	 * @throws Exception 
	 */
	public long getValue(int i, int j) throws IllegalArgumentException {
		if(i < 0 || j < 0) {
			throw new IllegalArgumentException();
		} else if(i>dependencyMap.length||(dependencyMap[i].length>0&&j>dependencyMap[0].length)) {
			return 0;
		}
		
		return this.dependencyMap[i][j];
	}
	
	public int getSize() {
		return this.dependencyMap.length;
	}
}
