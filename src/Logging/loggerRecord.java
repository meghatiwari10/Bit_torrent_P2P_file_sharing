package Logging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//log record file for each peer

public class loggerRecord {
	//peer id of each system
	private int peerId;
	
	//logger object used to log messages 
	private Logger peerLog;
	
	//format to write log messages
	private SimpleFormatter simpleFormatter;
	
	//file handler to write the log messages to a file
	private FileHandler fileHandler;
	
	// constructor to initialize variables
	public loggerRecord(int peerID) throws SecurityException, IOException{
		this.peerId = peerID;
		//initialized the logger for the peer
		peerLog = Logger.getLogger("Peer_id"+peerId);
		
		//set the level of the logger to informational
		peerLog.setLevel(Level.INFO);
		
		//assign the file name to the file handler
		fileHandler = new FileHandler("log_peer_"+peerId+".log");
		
		//adding formatter to the file Handler
		simpleFormatter = new SimpleFormatter();
		fileHandler.setFormatter(simpleFormatter);
		
		//adding handler to the logger to receive log messages
		peerLog.addHandler(fileHandler);
	}
	
	// log for TCP connection
	public void makeConnectionToPeer(int id){
		peerLog.info("Peer " + peerId + " makes a connection to Peer " + id + "\n");
	}
	
	public void getConnectionFromPeer(int id){
		peerLog.info("Peer " + peerId + " is connected from Peer " + id + "\n");
	}
		
	// log for change of preferred neighbors
	public void changeOfPreferredNeighbors(ArrayList<Integer> preferredNeighbors){
		StringBuffer sb = new StringBuffer();
		sb.append(":Peer " + peerId + " changed preferred neighbors to : ");
		for(int i = 0; i < preferredNeighbors.size(); i++){
			if(i < (preferredNeighbors.size() - 1)){
				sb.append(preferredNeighbors.get(i)+", ");
			}else{
				sb.append(preferredNeighbors.get(i)+"\n");
			}
		}
		peerLog.info(sb.toString());
	}
	
	// log for change of optimistically unchoked neighbor
	public void optimisticallyUnchokedNeighbor(int id){
		peerLog.info("Peer " + peerId + " has optimistically unchoked neighbor " + id + "\n");
	}
	
	// log for unchoking
	public void unchoking(int id){
		peerLog.info("Peer " + peerId + " is unchoked by " + id + "\n");
	}
		
	// log for choking
	public void choking(int id){
		peerLog.info("Peer " + peerId + " is choked by " + id + "\n");
	}
		
	// log for receiving ‘have’ message
	public void receiveHaveMessage(int id, int piece_index){
		peerLog.info("Peer " + peerId + " receives a have message from " + id + " for the piece " + piece_index + "\n");
	}
	
	// log for receiving ‘interested’ message
	public void receiveInterested(int id){
		peerLog.info("Peer " + peerId + " receives the interested message from " + id + "\n");
	}
	
	// log for receiving ‘not interested’ message
	public void receiveNotInterested(int id){
		peerLog.info("Peer " + peerId + " receives the not interested message from " + id + "\n");
	}
	
	// log for downloading a piece
	public void downloadPiece(int id, int index){
		peerLog.info("Peer " + peerId + " has downloaded the piece " + index + " from " + id + "\n"); 
	}
	
	// log for completion of download
	public void downloadCompletion(){
		peerLog.info("Peer " + peerId + " has downloaded the complete file. \n");
	}
}
