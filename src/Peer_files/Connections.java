package Peer_files;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Config_files.CommonConfig;
import Config_files.PeerInfoConfig;



//class file to manage the connections among the peers
public class Connections implements Runnable{
	
	private CommonConfig commonConfig;
	private PeerInfoConfig peerInfoConfig;
	private int peerId;
	private int noOfNeighbors;

	//read configuration files
	public Connections(int peerId) throws UnknownHostException, IOException {
		// TODO Auto-generated constructor stub
		try {
			this.commonConfig = new CommonConfig("Common.cfg");
			this.peerInfoConfig = new PeerInfoConfig("PeerInfo.cfg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.peerId = peerId;
		this.noOfNeighbors = this.peerInfoConfig.getNoOfPeers() - 1; //subtracting itself		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 1. 
	}

}
