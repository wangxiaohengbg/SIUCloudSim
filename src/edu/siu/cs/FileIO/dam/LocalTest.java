package edu.siu.cs.FileIO.dam;

class LocalTest {

	/**
	 * Local main function for running tests on DAM files
	 * @param args
	 */
	public static void main(String[] args) {

		DAMSmith d = new DAMSmith();
		d.generateRandomFile(100, 2, "files/generated.dam");
		DAMParse p = new DAMParse();
		try {
			boolean[][] matrix = p.parseDependancies("files/generated.dam");
			DAMFunctions.integrityCheck(matrix);
			DAMFunctions.printMatrix(matrix);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
