package edu.siu.cs.FileIO.dam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class DAMSmith {


	public boolean[][] generateRandomFile(int jobCount, int frequency, String FileName) {
		boolean[][] matrix = this.generateMatrix(jobCount, frequency);
		matrix = DAMFunctions.removeSelfDependencies(matrix);
		String result = smithFileContents(matrix);
		writeFile(result, FileName);
		return matrix;
	}
	
	private void writeFile(String contents, String FileName) {
		FileWriter f = openFile(FileName);
		try {
			f.write(contents);
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String smithFileContents(boolean[][] matrix) {
		System.out.println("Assembling File Contents...");
		StringBuilder result = new StringBuilder("JOBCOUNT " + ((matrix[0].length>matrix.length)?matrix[0].length:matrix.length));
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < matrix[0].length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				if(matrix[j][i]) {
					stack.push(new Integer(j));
				}
			}
			if(!stack.isEmpty()) {
				result.append("\n");
				result.append("PARENT " + i + " CHILD");
				while(stack.size()>0) {
					result.append(" " + stack.pop().toString());
				}
			}
			stack.clear();
		}
		return result.toString();
	}
	
	private FileWriter openFile(String FileName) {
		File f = new File(FileName);
		if(f.exists()) {
			f.delete();
		}
		try {
			f.createNewFile();
			FileWriter result = new FileWriter(f);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	private boolean[][] generateMatrix(int jobCount, int frequency) {
		System.out.println("Generating Matrix...");
		boolean[][] result = new boolean[jobCount][jobCount];
		for(int i = 0; i < jobCount; i++)
			for(int j = 0; j < jobCount; j++)
				result[i][j] = random(frequency);
		return result;
	}
	
	private boolean random(int frequency) {
		return  ((int) (Math.random() * 2147483647)%frequency==0)? true : false;
	}
}
