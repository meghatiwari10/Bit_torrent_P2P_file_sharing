package Config_files;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// class to read the properties of common.cfg

public class CommonConfig {
	private final int noOfPreferredNeighbors;
	private final int unchokingInterval;
	private final int optimisticUnchokingInterval;
	private final String fileName;
	private final int filesize;
	private final int piecesize;
	private final int noOfPieces;
	
	//getter and setter method.
	public int getNoOfPieces() {
		return noOfPieces;
	}
	public int getNoOfPreferredNeighbors() {
		return noOfPreferredNeighbors;
	}
	public int getUnchokingInterval() {
		return unchokingInterval;
	}
	public int getOptimisticUnchokingInterval() {
		return optimisticUnchokingInterval;
	}
	public String getFileName() {
		return fileName;
	}
	public int getFilesize() {
		return filesize;
	}
	public int getPiecesize() {
		return piecesize;
	}
	
	
	//setting variable values from cfg file
	//passing fileName as parameter.
	public CommonConfig(String commonCfg) throws FileNotFoundException{
		Scanner scan = new Scanner(new FileReader(commonCfg));
		String[] prefNeighbors=scan.nextLine().split(" ");
		this.noOfPreferredNeighbors = Integer.parseInt(prefNeighbors[1]);
		String[] unchokingInt=scan.nextLine().split(" ");
		this.unchokingInterval = Integer.parseInt(unchokingInt[1]);
		String[] opUnchokingInt=scan.nextLine().split(" ");
		this.optimisticUnchokingInterval = Integer.parseInt(opUnchokingInt[1]);
		String[] name=scan.nextLine().split(" ");
		this.fileName = name[1];
		String[] size=scan.nextLine().split(" ");
		this.filesize = Integer.parseInt(size[1]);
		String[] piece=scan.nextLine().split(" ");
		this.piecesize = Integer.parseInt(piece[1]);
		
		if(this.filesize % this.piecesize == 0){
			this.noOfPieces = (this.filesize/this.piecesize);
		} else {
			this.noOfPieces = (this.filesize/this.piecesize)+1;
		}
		scan.close();
	}
	
}
