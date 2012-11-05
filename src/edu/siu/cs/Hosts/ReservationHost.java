package edu.siu.cs.Hosts;

import java.util.List;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.VmScheduler;
import org.cloudbus.cloudsim.provisioners.BwProvisioner;
import org.cloudbus.cloudsim.provisioners.RamProvisioner;

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
