package edu.siu.cs.Hosts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * An ArrayList of VmReservations sorted by their individual start times.
 * This class supports the ReservationHost class.
 * @author Crackers
 */
class ReservationList extends ArrayList<VmReservation> {

	private static final long serialVersionUID = -3737258521374900063L;

	@Override
	public boolean add(VmReservation e) {
		int index = 0;
		Iterator<VmReservation> it = this.iterator();
		@SuppressWarnings("unused")
		VmReservation v;
		while(it.hasNext()&&(v = it.next()).getStartTime()<e.getStartTime()) {
			index++;
		}
		super.add(index,e);
		return true;
	}
	
	@Override
	public void add(int index, VmReservation e) {
		this.add(e);
	}
	
	@Override
	public boolean addAll(Collection<? extends VmReservation> c) {
		Iterator<? extends VmReservation> it = c.iterator();
		boolean result = true;
		while(it.hasNext()) {
			if(!this.add(it.next()))
				result = false;
		}
		return result;
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends VmReservation> c) {
		return this.addAll(c);
	}
	
	@Override
	public VmReservation set(int index, VmReservation e) {
		VmReservation result = this.remove(index);
		this.add(e);
		return result;
	}
}
