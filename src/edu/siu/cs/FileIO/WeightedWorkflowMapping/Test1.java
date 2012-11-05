package edu.siu.cs.FileIO.WeightedWorkflowMapping;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.junit.Test;

public class Test1 {

	@Test
	public void test() {
		boolean matrix[][] = 
			{	{false,false,false,false,false,false},
				{ true,false,false,false,false,false},
				{ true,false,false,false,false,false},
				{false, true, true,false,false,false},
				{false,false, true,false,false,false},
				{false, true, true, true,false,false}};
		ArrayList<Cloudlet> cloudlets = new ArrayList<Cloudlet>();
		UtilizationModel um = new UtilizationModelFull();
		cloudlets.add(new Cloudlet(0, 0, 0, 0, 15, um, um, um));
		cloudlets.add(new Cloudlet(1, 0, 0, 0, 10, um, um, um));
		cloudlets.add(new Cloudlet(2, 0, 0, 0, 06, um, um, um));
		cloudlets.add(new Cloudlet(3, 0, 0, 0, 12, um, um, um));
		cloudlets.add(new Cloudlet(4, 0, 0, 0, 07, um, um, um));
		cloudlets.add(new Cloudlet(5, 0, 0, 0, 14, um, um, um));
		long expectedResult[][] =
			{	{00,00,00,00,00,00},
				{15,00,00,00,00,00},
				{15,00,00,00,00,00},
				{00,10,06,00,00,00},
				{00,00,06,00,00,00},
				{00,10,06,12,00,00}};
		WeightedWorkflowMapping test = new WeightedWorkflowMapping(matrix,cloudlets);
		assertArrayEquals(expectedResult, test.getMap());
	}

}
