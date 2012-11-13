package edu.siu.cs.JobScheduling.WorkflowDatacenter;

import java.util.List;

import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.VmAllocationPolicy;
import org.cloudbus.cloudsim.core.SimEvent;

import edu.siu.cs.JobScheduling.SIUTags;
import edu.siu.cs.JobScheduling.Workflow.Workflow;

public class WorkflowDatacenter extends Datacenter {

	/**
	 * Allocates a new PowerDatacenter object that supports Workflow style
	 * cloudlet execution.
	 * 
	 * @param name the name to be associated with this entity (as required by Sim_entity class from
	 *            simjava package)
	 * @param characteristics an object of DatacenterCharacteristics
	 * @param storageList a LinkedList of storage elements, for data simulation
	 * @param vmAllocationPolicy the vmAllocationPolicy
	 * @throws Exception This happens when one of the following scenarios occur:
	 *             <ul>
	 *             <li>creating this entity before initializing CloudSim package
	 *             <li>this entity name is <tt>null</tt> or empty
	 *             <li>this entity has <tt>zero</tt> number of PEs (Processing Elements). <br>
	 *             No PEs mean the Cloudlets can't be processed. A CloudResource must contain one or
	 *             more Machines. A Machine must contain one or more PEs.
	 *             </ul>
	 * @pre name != null
	 * @pre resource != null
	 * @post $none
	 */
	public WorkflowDatacenter(String arg0, DatacenterCharacteristics arg1,
			VmAllocationPolicy arg2, List<Storage> arg3, double arg4)
			throws Exception {
		super(arg0, arg1, arg2, arg3, arg4);
	}
	
	protected void processOtherEvent(SimEvent ev) {
		switch(ev.getTag()) {
		case SIUTags.SUBMIT_WORKFLOW:
			Workflow wf = (Workflow) ev.getData();
			Log.printLine(getName()+" received Workflow "+wf.getId()+" with "+wf.getSize()+" jobs...");
			break;
		default:
			Log.printLine(getName() + ".processOtherEvent(): Error - unrecognized Command.");
		}
	}

	
}
