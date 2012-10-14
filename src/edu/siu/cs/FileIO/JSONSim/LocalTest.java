package edu.siu.cs.FileIO.JSONSim;

/**
 * DELETE UPON FINISHING LIBRARY
 * @author Crackers
 *
 */
class LocalTest {

	public static void main(String args[]) {
		JsonSmith json = new JsonSmith();
		json.generateHosts(5);
		json.generateCloudlets(100);
		json.useAmazonInstances();
		json.generateDatacenterCharacteristics();
		json.GenerateJson("files/example.json");
	}
}
