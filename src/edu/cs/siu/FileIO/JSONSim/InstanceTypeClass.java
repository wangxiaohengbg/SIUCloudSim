package edu.cs.siu.FileIO.JSONSim;

class InstanceTypeClass {
	public int mips;
	public long size;
	public int ram;
	public long bw;
	public int pesNumber;

	public InstanceTypeClass(int mips, long size, int ram, long bw, int pesCount) {
		this.mips = mips;
		this.size = size;
		this.ram = ram;
		this.bw = bw;
		this.pesNumber = pesCount;
	}
}
