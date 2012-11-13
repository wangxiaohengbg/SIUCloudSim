package edu.siu.cs.FileIO.JSONSim;

/**
 * DELETE UPON FINISHING LIBRARY
 * @author Crackers
 *
 */
class LocalTest {

	public static void main(String args[]) {
		JsonSmith json = new JsonSmith();
		System.out.println("Generating Hosts...");
		json.generateHosts(5);
		System.out.println("Generating Cloudlets...");
		json.generateCloudlets(200);
		System.out.println("Generating VM Instances...");
		json.useAmazonInstances();
		System.out.println("Generating Datacenter Characteristics...");
		json.generateDatacenterCharacteristics();
		System.out.println("Writing Data to File");
		json.GenerateJson("files/small.json");
	}
}
