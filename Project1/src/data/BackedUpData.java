package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class BackedUpData implements Serializable
{
	private String filename;
	private String fileId;
	private int desiredReplicationDegree;
	private HashMap<Integer, ArrayList<Integer>> chunkPeers;
	
	public BackedUpData(String filename, String fileId, int desiredReplicationDegree)
	{
		this.filename = filename;
		this.fileId = fileId;
		this.desiredReplicationDegree = desiredReplicationDegree;
		this.chunkPeers = new HashMap<Integer, ArrayList<Integer>>();
	}
	
	public String getFilename()
	{
		return filename;
	}
	
	public String getFileId()
	{
		return fileId;
	}
	
	public int getDesiredReplicationDegree()
	{
		return desiredReplicationDegree;
	}
	
	public void updateVersion(String fileId, int desiredReplicationDegree)
	{
		this.fileId = fileId;
		this.desiredReplicationDegree = desiredReplicationDegree;
	}
	
	public void addChunkPeers(int chunkNo, ArrayList<Integer> peers)
	{
		this.chunkPeers.put(chunkNo, peers);
	}
	
	public String toString()
	{
		String ret = "Filepath: " + filename + "\n";
		ret += "File ID: " + fileId + "\n";
		ret += "Desired Replication Degree " + String.valueOf(desiredReplicationDegree) + "\n";
		
		for(Integer k : chunkPeers.keySet())
		{
			ret += "Chunk No " + String.valueOf(k) + " - " + String.valueOf(chunkPeers.get(k).size()) + " peers\n";
		}
		
		/*for(int i = 0; i < chunkPeers.size(); i++)
		{
			if(chunkPeers.get(i) != null)
				ret += "Chunk No " + String.valueOf(i) + " - " + String.valueOf(chunkPeers.get(i).size()) + " peers\n";
		}*/
		
		return ret;	
	}
}
