package edu.siu.cs.JobScheduling.Workflow;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.junit.Test;

public class Test1 {

	@Test
	public void test_getRootNodes() {
		long matrix[][] = 
				//0	 1  2  3  4	 5  6  7  8	 9 10 11 12 13
			{	{00,00,00,10,10,00,00,00,00,00,00,00,00,00},//0
				{00,00,00,00,10,10,00,00,00,00,00,00,00,00},//1
				{00,00,00,00,00,00,10,00,00,00,00,00,00,00},//2
				{00,00,00,00,00,00,00,10,00,00,00,00,00,00},//3
				{00,00,00,00,00,00,00,10,10,00,00,00,00,00},//4
				{00,00,00,00,00,00,00,00,10,10,00,00,00,00},//5
				{00,00,00,00,00,00,00,00,00,10,00,00,00,00},//6
				{00,00,00,00,00,00,00,00,00,00,10,00,00,00},//7
				{00,00,00,00,00,00,00,00,00,00,10,00,00,00},//8
				{00,00,00,00,00,00,00,00,00,00,10,00,00,00},//9
				{00,00,00,00,00,00,00,00,00,00,00,10,10,00},//10
				{00,00,00,00,00,00,00,00,00,00,00,00,00,00},//11
				{00,00,00,00,00,00,00,00,00,00,00,00,00,00},//12
				{00,10,00,00,00,00,00,00,00,00,00,00,00,00} //13
			};
		int[] expecteds = {0,2,13};
		WeightedWorkflowMapping test = new WeightedWorkflowMapping(matrix);
		int[] actuals = test.getRootNodes();
		System.out.print("Returned Values: {");
		for(int i = 0; i < actuals.length; i++) {
			if(i!=0) {System.out.print(",");}
			System.out.print(actuals[i]);
		}
		System.out.println("}");
		assertArrayEquals(expecteds, actuals);
	}
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
		for(int i = 0; i < expectedResult.length; i++) {
			for(int j = 0; j < expectedResult[0].length; j++) {
				try {
					assertEquals(expectedResult[i][j], test.getValue(i,j));
				} catch (IllegalArgumentException e) {
					fail("IllegalArgumentException caught");
				}				
			}
		}
	}

}
