package edu.siu.cs.FileIO.dam;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DependencyFactory {
	
	/**
	 * This function will generate a 2D matrix representing the
	 * edges of a directional digraph were 1 means the job
	 * corresponding to the row number depends on the job
	 * corresponding to the col number and 0 means the job 
	 * corresponding to the row number does not depend on the job
	 * corresponding to the col number.
	 * 
	 * @param filename
	 * The file name relative to the program that the
	 * Dependancy Mapping File will be imported from
	 * @return
	 * The array representing job dependancies.
	 * @throws DAMFileNotFoundException 
	 */
	public static boolean[][] parseDependancies(String filename) throws DAMFileNotFoundException, DAMSelfDependent, DAMOutOfBounds, IOException {
		FileReader file = DependencyFactory.openFile(filename);
		DAMParser parse = new DAMParser(file);
		parse.startParse();
		boolean[][] result = parse.getResult();
		return result;
	}
	
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
	 * Returns a pointer to a character file stream
	 * @param filename
	 * @return
	 */
	private static FileReader openFile(String filename) throws DAMFileNotFoundException {
		try {
			return new FileReader(filename);
		} catch (FileNotFoundException e) {
			throw new DAMFileNotFoundException(filename);
		}
		
	}
	
	//Throws errors if the imported file does not meet criteria
	public static void integrityCheck(boolean[][] matrix) throws DAMSelfDependent {
		boolean[][] temp = DependencyFactory.resolveDependancies(matrix);
		if(temp.length>0) {
			for(int i = 0;i<temp.length&&i<temp[0].length;i++) {
				//if job depends on self
				if(temp[i][i]) {
					throw new DAMSelfDependent();
				}
			}
		}
	}
	
	
}
