package edu.siu.cs.Hosts;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.cloudbus.cloudsim.Vm;
import org.junit.Test;

public class TestReservationList {

	@Test
	public void test() {
		ReservationList test = new ReservationList();
		Vm vm = new Vm(0, 0, 0, 0, 0, 0, 0, null, null);
		for(int i = 20000; i > 0; i--) {
			test.add(new VmReservation(i*Math.random(),i*Math.random(),vm));
		}
		
		Iterator<VmReservation> it = test.iterator();
		VmReservation previous = it.next();
		while(it.hasNext()) {
			VmReservation temp = it.next();
			assertTrue(previous.getStartTime()<temp.getStartTime());
			previous = temp;
		}
	}

}
