package message;

import java.net.DatagramPacket;
import java.util.Arrays;

public class MessageHandler implements Runnable
{
	private DatagramPacket packet;
	private String[] headerTokens;
	private byte[] body;
	
	public MessageHandler(DatagramPacket packet)
	{
		System.out.print("Handling the packet");
		
		this.packet = packet;
		byte[] packetData = packet.getData();
		
		int delimiterIndex = indexOf(packetData, MessageGenerator.CRLF.getBytes());
		
		byte[] headerBytes = Arrays.copyOfRange(packetData, 0, delimiterIndex - 1);
		body = Arrays.copyOfRange(packetData, delimiterIndex + 2, packetData.length);
		
		String headerString = new String(headerBytes, 0, headerBytes.length);
		headerTokens = headerString.split(" ");
	}

	@Override
	public void run()
	{
		String received = new String(packet.getData(), 0, packet.getLength());
		String[] parts = received.split(MessageGenerator.CRLF);

	}
	
	public static int indexOf(byte[] list, byte[] element)
	{
	    for(int i = 0; i < list.length - element.length + 1; ++i)
	    {
	        boolean found = true;
	        
	        for(int j = 0; j < element.length; ++j)
	        {
	           if (list[i + j] != element[j])
	           {
	               found = false;
	               break;
	           }
	        }
	        
	        if(found)
	        	return i;
	     }
	   return -1;  
	} 
	
	/*
	private void parseType(String[] arr)
	{
		this.version = arr[1];
		this.senderID = Integer.valueOf(arr[2]);
		this.fileID = Integer.valueOf(arr[3]);
		
		switch(this.messageType){
		case("PUTCHUNK"):
			this.chunkNo = Integer.valueOf(arr[4]);
			this.replicationDegree = Integer.valueOf(arr[5]);
			break;
		case("STORED"):
			this.chunkNo = Integer.valueOf(arr[4]);
			break;
		case("GETCHUNK"):
			this.chunkNo = Integer.valueOf(arr[4]);
			break;
		case("CHUNK"):
			this.chunkNo = Integer.valueOf(arr[4]);
			break;		
		case("REMOVED"):
			this.chunkNo = Integer.valueOf(arr[4]);
			break;
		}
	}
	*/
}