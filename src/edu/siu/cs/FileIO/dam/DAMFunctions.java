package edu.siu.cs.FileIO.dam;

import java.util.Iterator;
import java.util.Stack;

public class DAMFunctions {
	
	public static boolean[][] resolveDependancies(boolean[][] matrix) {
		boolean cleanPass = false;
		//Iterate through each job
		while(!cleanPass) {
			cleanPass = true;
			for(int i = 0; i < matrix.length; i++) {
				//iterate through each dependency
				for(int j = 0; j < matrix[0].length; j++) {
					if(matrix[i][j]) {
						//resolve the dependencies of the dependency
						for(int k = 0; k < matrix[0].length; k++) {
							if(!matrix[i][k]&&matrix[j][k]) {
								cleanPass = false;
								matrix[i][k] = matrix[j][k];
							}
						}
					}
				}
			}
		}
		return matrix;
	}
	
	/**
	 * Throws an error if the file does not meet the criteria for being a workflow
	 * digraph.
	 * 
	 * @param matrix
	 * Workflow Dependency Matrix
	 * @throws DAMSelfDependent
	 * If a cloudlet depends on itself
	 */
	public static void integrityCheck(boolean[][] matrix) throws DAMSelfDependent {
		boolean[][] temp = DAMFunctions.resolveDependancies(matrix);
		if(temp.length>0) {
			for(int i = 0;i<temp.length&&i<temp[0].length;i++) {
				//if job depends on self
				if(temp[i][i]) {
					throw new DAMSelfDependent();
				}
			}
		}
	}
	
	//Used for the recursive part of removeSelfDependencies()
	private static IntegerStack depends = new IntegerStack();
	
	/**
	 * Navigates the matrix and removes any self dependency.
	 */
	public static boolean[][] removeSelfDependencies(boolean[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
				depends.push(i);
				buildStack(matrix, i);
				depends.clear();
		}
		return matrix;
	}
	
	private static void buildStack(boolean[][] matrix, int row) {
		for(int j = 0; j < matrix[row].length; j++) {
			if(matrix[row][j]) {
				if(depends.contains(new Integer(j))) {
					matrix[row][j] = false;
				} else {
					depends.push(new Integer(j));
					buildStack(matrix, j);
				}
			}
		}
		depends.pop();
	}
	
	public static void printMatrix(boolean[][] matrix) {
		String result = "";
		for(int i = 0; i < matrix.length; i++) {
			result+="|";
			for(int j = 0; j<matrix[0].length;j++) {
				char c;
				if(matrix[i][j])
					c = '1';
				else
					c = '0';
				result+=(c+"|");
			}
			result+="\n";
		}
		
		System.out.println(result);
	}
	
	private static class IntegerStack extends Stack<Integer> {

		private static final long serialVersionUID = 8650955703278560778L;
		
		@Override
		public boolean contains(Object o) {
			Integer value = (Integer)o;
			Iterator<Integer> it = this.iterator();
			while(it.hasNext()) {
				if(it.next().intValue()==value.intValue())
					return true;
			}
			return false;
		}
	}
}
