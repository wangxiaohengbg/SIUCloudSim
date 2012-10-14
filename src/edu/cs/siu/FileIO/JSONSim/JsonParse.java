package edu.cs.siu.FileIO.JSONSim;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cloudbus.cloudsim.Host;

import com.google.gson.Gson;

public class JsonParse {
	
	Sim s;
	
	public void parseJson(String fileName) {
		Gson g = new Gson();
		String json = openFile(fileName);
		if(json!=null) {
			s = g.fromJson(json, Sim.class);	
		}
		else {
			s = null;
		}
	}

	/**
	 * A private function for opening a file from the
	 * hard drive.
	 * 
	 * @param fileName
	 * The location of the file on the hard drive
	 * @return
	 * Returns either the contents of the file in a string
	 * or null if the file can not be opened
	 */
	private String openFile(String fileName) {
		try {
			File f = new File(fileName);
			FileReader fr = new FileReader(f);
			StringBuilder str = new StringBuilder();
			int i;
			while((i=fr.read())!=-1) {
				str.append((char)i);
			}
			fr.close();
			return str.toString();
		} catch(Exception e) {
			return null;
		}
	}
	
	public <T, S, U, V> List<Host> getHosts(Class<T> ram, Class<S> bw, Class<U> vm, Class<V> pe) {
		ArrayList<Host> result = new ArrayList<Host>();
		List<HostClass> hosts = s.getHosts();
		Iterator<HostClass> it = hosts.iterator();
		while(it.hasNext())
			result.addAll(((HostClass)it.next()).convertToHost(result.size(), ram, bw, vm, pe));
		return result;
	}
}
