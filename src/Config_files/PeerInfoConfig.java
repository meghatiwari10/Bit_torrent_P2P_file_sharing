package Config_files;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

// class to read the properties of PeerInfo.cfg

public class PeerInfoConfig {
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	private ArrayList<String> hostnames = new ArrayList<String>();
	private ArrayList<Integer> ports = new ArrayList<Integer>();
	//value 1 if it has file ; 0 if it doesn't have file
	private ArrayList<Integer> hasFile = new ArrayList<Integer>();
	
	private final int noOfPeers;
	private final ArrayList<Integer> uploadingPorts = new ArrayList<Integer>();
	private final ArrayList<Integer> hasPorts = new ArrayList<Integer>();
	
	public int getNoOfPeers() {
		return noOfPeers;
	}
	public ArrayList<Integer> getUploadingPorts() {
		return uploadingPorts;
	}
	public ArrayList<Integer> getHasPorts() {
		return hasPorts;
	}
	public ArrayList<Integer> getIds() {
		return ids;
	}
	public void setIds(ArrayList<Integer> ids) {
		this.ids = ids;
	}
	public ArrayList<String> getHostnames() {
		return hostnames;
	}
	public void setHostnames(ArrayList<String> hostnames) {
		this.hostnames = hostnames;
	}
	public ArrayList<Integer> getPorts() {
		return ports;
	}
	public void setPorts(ArrayList<Integer> ports) {
		this.ports = ports;
	}
	public ArrayList<Integer> getHasFile() {
		return hasFile;
	}
	public void setHasFile(ArrayList<Integer> hasFile) {
		this.hasFile = hasFile;
	}
	
	//initializing variables
	public PeerInfoConfig(String peerInfo) throws FileNotFoundException{
		
		Scanner scan = new Scanner(new FileReader(peerInfo));
		
		int count = 0; //for counting number of peers
		while(scan.hasNextLine()){
			String str = scan.nextLine();
			String[] peerinfo = str.split(" ");
			this.ids.add(Integer.parseInt(peerinfo[0].trim()));
			this.hostnames.add(peerinfo[1].trim());
			this.ports.add(Integer.parseInt(peerinfo[2].trim()));
			this.uploadingPorts.add(Integer.parseInt(peerinfo[2].trim()) + 1);//
			this.hasPorts.add(Integer.parseInt(peerinfo[2].trim()) + 2);  //
			//set hasFile value based on whether it has file or not
			if(peerinfo[3].trim().equals("1")){
				this.hasFile.add(1);
			}else{
				this.hasFile.add(0);
			}
			count++;
		}
		this.noOfPeers = count;
		scan.close();
	}
	
}
