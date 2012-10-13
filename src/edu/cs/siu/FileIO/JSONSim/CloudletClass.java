package edu.cs.siu.FileIO.JSONSim;

class CloudletClass {
	public long MillionsOfInstructions;
	public int Processors;
	public long inputFileSize;
	public long outputFileSize;
	
	public CloudletClass(long length, int pes, long fileSize, long outputSize) {
		this.MillionsOfInstructions = length;
		this.Processors = pes;
		this.inputFileSize = fileSize;
		this.outputFileSize = outputSize;
	}
}
