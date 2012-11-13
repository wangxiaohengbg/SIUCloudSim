package edu.siu.cs.JobScheduling;

/**
 * An extension to the Tags defined by CloudSimTags. These tags will be used in SIU's
 * implementation of job scheduling algorithms
 * 
 * @author Crackers
 *
 */
public class SIUTags {
	
	/**
	 * If a conflict with another package arises (such as CloudSim's default Tags)
	 * we can change the base to shift the entire range of Tags defined here to a
	 * new range.
	 */
	public static final int BASE = 1000;
	
	/**
	 * Used to signal the submission of a Workflow from a WorkflowBroker to a Datacenter.
	 */
	public static final int SUBMIT_WORKFLOW = BASE+1;
	
	private SIUTags() {
		throw new UnsupportedOperationException("SIUTags cannot/should not be instantiated");
	}
}
