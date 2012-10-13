package edu.siu.cs.FileIO.dam;

import static org.junit.Assert.*;

import org.junit.Test;

public class SelfDependent {

	@Test
	public void test() {
		try {
			DependencyFactory.parseDependancies("test4.dam");
			fail("Did not catch exception");
		} catch (DAMSelfDependent e) {
			
		} catch (Exception e) {
			fail("Caught Wrong Exception" + e.getMessage());
		}
	}

}
