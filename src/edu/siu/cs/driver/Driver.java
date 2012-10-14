package edu.siu.cs.driver;

import java.util.List;

import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

import edu.cs.siu.FileIO.JSONSim.JsonParse;

public class Driver {
	
	public static void main(String args[]) {
		/**
		 * Put all simulation and driver data here
		 */
		CloudSim.init(1, null, false);
		JsonParse j = new JsonParse();
		j.parseJson("files/example.json");
		List<Host> hosts = j.getHosts(RamProvisionerSimple.class, BwProvisionerSimple.class, VmSchedulerTimeShared.class, PeProvisionerSimple.class);
		DatacenterCharacteristics dcc = j.getDatacenterCharacteristics(hosts);
		try {
			Datacenter dc = new Datacenter("test",dcc,new VmAllocationPolicySimple(hosts),null,0);
			DatacenterBroker broker = new DatacenterBroker("broker");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
