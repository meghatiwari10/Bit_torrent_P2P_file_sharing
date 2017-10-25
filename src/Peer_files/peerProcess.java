package Peer_files;

import java.io.IOException;
import java.net.UnknownHostException;

public class peerProcess {
	public static void main(String args[]) throws NumberFormatException, UnknownHostException, IOException{
		//takes peer ID as arguments
		Connections conn = new Connections(Integer.parseInt(args[0]));
		Thread th1 = new Thread(conn);
		th1.run();
	}
}
