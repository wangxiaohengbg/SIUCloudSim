package edu.siu.cs.driver;

import java.util.Iterator;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
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
		//Initialize CloudSim
		CloudSim.init(1, null, false);
		
		//Parse in the JSON file
		JsonParse j = new JsonParse();
		j.parseJson("files/example.json");
		
		//Get hosts from parser
		List<Host> hosts = j.getHosts(RamProvisionerSimple.class, BwProvisionerSimple.class, VmSchedulerTimeShared.class, PeProvisionerSimple.class);
		
		//Get DatacenterCharacteristics from parser
		DatacenterCharacteristics dcc = j.getDatacenterCharacteristics(hosts);
		
		//Create the broker and datacenter
		@SuppressWarnings("unused")
		Datacenter dc;
		DatacenterBroker broker;
		try {
			dc = new Datacenter("test",dcc,new VmAllocationPolicySimple(hosts),null,0);
			broker = new DatacenterBroker("broker");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		//Import Cloudlets
		List<Cloudlet> cloudlets = j.getCloudlets(UtilizationModelFull.class, UtilizationModelFull.class, UtilizationModelFull.class);
		Iterator<Cloudlet> it = cloudlets.iterator();
		while(it.hasNext()) {
			((Cloudlet)it.next()).setUserId(broker.getId());
		}
		
		List<Vm> vmInstanceTypes = j.getVmInstanceTypes(broker.getId(), CloudletSchedulerTimeShared.class);
		broker.submitVmList(vmInstanceTypes);
		
		CloudSim.startSimulation();
	}
}
