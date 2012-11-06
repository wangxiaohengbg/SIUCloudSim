package edu.siu.cs.FileIO.dam;

class LocalTest {

	/**
	 * Local main function for running tests on DAM files
	 * @param args
	 */
	public static void main(String[] args) {

//		boolean[][] matrix = {
//				{false,false,false,false,false,false,false},
//				{ true,false,false,false,false,false,false},
//				{ true,false,false,false, true,false,false},
//				{false, true, true,false,false,false,false},
//				{false,false,false, true,false,false,false},
//				{false,false,false,false, true,false,false},
//				{false,false,false, true,false, true,false}
//		};
//		
//		matrix = DAMFunctions.removeSelfDependencies(matrix);
//		try {
//			DAMFunctions.integrityCheck(matrix);
//		} catch (DAMSelfDependent e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		DAMFunctions.printMatrix(matrix);
		
		
		DAMSmith d = new DAMSmith();
		d.generateRandomFile(2000, 2, "files/generated.dam");
		DAMParse p = new DAMParse();
//		try {
//			boolean[][] matrix = p.parseDependancies("files/generated.dam");
//			DAMFunctions.integrityCheck(matrix);
//			DAMFunctions.printMatrix(matrix);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

}
