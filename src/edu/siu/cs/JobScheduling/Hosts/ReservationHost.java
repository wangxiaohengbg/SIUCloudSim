package edu.siu.cs.JobScheduling.Hosts;

import java.util.List;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.VmScheduler;
import org.cloudbus.cloudsim.provisioners.BwProvisioner;
import org.cloudbus.cloudsim.provisioners.RamProvisioner;


/**
 * @TODO implement boolean makeReservation(ts,tf,vm)
 * @TODO implement vm maxReservation(ts,tf)
 * @TODO implement vm maxReservation(ts,tf,ArrayList&lt;Vm&gt; possibleVms)
 * @TODO implement boolean canReserve(ts,tf,vm)
 * @TODO override public boolean isSuitableForVm(Vm vm)
 * @TODO override public boolean vmCreate(Vm vm)
 * @TODO override public void vmDestroyAll()
 * @TODO override public Vm getVm(int vmId,int userId)
 * @TODO override public int getNumberOfFreePes()
 * @TODO implement public int getNumberOfFreePes(time)
 * @TODO implement public int getNumberOfFreePes(ts,tf)
 * @TODO override public int getTotalMips()
 * @TODO override public boolean allocatePesForVm(Vm vm,List<Double> mipsShare)
 * @TODO override public double getAvailableMips()
 * @TODO override public double getAvailableMips(time)
 * @TODO override public double getAvailableMips(ts,tf)
 * @TODO override 
 * @TODO override 
 * @TODO override 
 * @TODO override 
 * @TODO override 
 * @TODO override 
 * @TODO override 
 * @author Crackers
 *
 */
public class ReservationHost extends Host {
	
	ReservationList reservations;

	public ReservationHost(int id, RamProvisioner ramProvisioner,
			BwProvisioner bwProvisioner, long storage,
			List<? extends Pe> peList, VmScheduler vmScheduler) {
		super(id, ramProvisioner, bwProvisioner, storage, peList, vmScheduler);
		reservations = new ReservationList();
	}
	
	/**
	 * Build a host using another host as a template
	 * @param host
	 */
	public ReservationHost(Host host) {
		super(host.getId(),host.getRamProvisioner(),host.getBwProvisioner(),host.getStorage(),host.getPeList(),host.getVmScheduler());
	}
	
}
