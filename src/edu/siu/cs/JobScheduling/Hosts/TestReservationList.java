package edu.siu.cs.JobScheduling.Hosts;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.cloudbus.cloudsim.Vm;
import org.junit.Test;

public class TestReservationList {

	@Test
	public void expires() {
		ReservationList test = new ReservationList();
		Vm vm = new Vm(0, 0, 0, 0, 0, 0, 0, null, null);
		for(int i = 20000; i > 0; i--) {
			try {
				test.add(new VmReservation(i*Math.random(),i*Math.random(),vm));
			} catch (NegativeTimeException e) {
				fail();
			}
		}
		
		Iterator<VmReservation> it = test.iterator();
		VmReservation previous = it.next();
		assertTrue(previous.doesExpire()&&previous.getStopTime()!=-1);
		while(it.hasNext()) {
			VmReservation temp = it.next();
			assertTrue(previous.getStartTime()<temp.getStartTime());
			assertTrue(temp.doesExpire()&&temp.getStopTime()!=-1);
			previous = temp;
		}
	}
	
	@Test
	public void noExpiration() {
		ReservationList test = new ReservationList();
		Vm vm = new Vm(0, 0, 0, 0, 0, 0, 0, null, null);
		for(int i = 20000; i > 0; i--) {
			try {
				test.add(new VmReservation(i*Math.random(),vm));
			} catch (NegativeTimeException e) {
				fail();
			}
		}
		
		Iterator<VmReservation> it = test.iterator();
		VmReservation previous = it.next();
		assertTrue(!previous.doesExpire()&&previous.getStopTime()==-1);
		while(it.hasNext()) {
			VmReservation temp = it.next();
			assertTrue(previous.getStartTime()<temp.getStartTime());
			assertTrue(!temp.doesExpire()&&temp.getStopTime()==-1);
			previous = temp;
		}
	}
	
	@Test
	public void negativeTimeException() {
		boolean exceptionCaught=false;
		ReservationList test = new ReservationList();
		Vm vm = new Vm(0, 0, 0, 0, 0, 0, 0, null, null);
		
		try {
			test.add(new VmReservation(-1,0,vm));
		} catch (NegativeTimeException e) {
			exceptionCaught = true;
		}
		
		assertTrue("Expires - TS",exceptionCaught);
		exceptionCaught = false;
		
		try {
			test.add(new VmReservation(0,-1,vm));
		} catch (NegativeTimeException e) {
			exceptionCaught = true;
		}
		
		assertTrue("Expires - TF",exceptionCaught);
		exceptionCaught = false;
		
		try {
			test.add(new VmReservation(-1,vm));
		} catch (NegativeTimeException e) {
			exceptionCaught = true;
		}
		
		assertTrue("No Expiration - TF",exceptionCaught);
		exceptionCaught = false;
	}

}
