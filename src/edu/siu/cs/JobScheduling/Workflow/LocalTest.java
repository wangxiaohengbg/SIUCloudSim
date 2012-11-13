package edu.siu.cs.JobScheduling.Workflow;

import java.util.ArrayList;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModelFull;

import edu.siu.cs.FileIO.JSONSim.JsonParse;
import edu.siu.cs.FileIO.dam.DAMParse;

public class LocalTest {

	public static void main(String[] args) {
		DAMParse damparse = new DAMParse();
		JsonParse jparse = new JsonParse();
		try {
			boolean[][] matrix = damparse.parseDependancies("files/generated.dam");
			jparse.parseJson("files/example.json");
			ArrayList<Cloudlet> cloudlets = (ArrayList<Cloudlet>)jparse.getCloudlets(UtilizationModelFull.class,UtilizationModelFull.class,UtilizationModelFull.class);
			WeightedWorkflowMapping test = new WeightedWorkflowMapping(matrix,cloudlets);
			LocalTest.printMatrix(test);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printMatrix(WeightedWorkflowMapping matrix) {
		String result = "";
		for(int i = 0; i < matrix.getSize(); i++) {
			result+="|";
			for(int j = 0; j<matrix.getSize();j++) {
				String c = ""+matrix.getValue(i, j);
				if(matrix.getValue(i,j)==0)
					c = "0000"+c;
				result+=(c+"|");
			}
			result+="\n";
		}
		
		System.out.println(result);
	}
}