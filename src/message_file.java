import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;

// Message parameter has been represented as a java object with the following types and attributes
public class message_file{
	// Different types of messages have been enumerated here
	final static int terminate = 8;
	final static int piece = 7;
	final static int request = 6;
	final static int bitfield = 5;
	final static int have = 4;
	final static int not_interested = 3;
	final static int interested = 2;
	final static int unchoke = 1;
	final static int choke = 0;
	
	// Attributes of message are listed as follows
	private int msg_len;
	private int msg_type;
	private byte[] msg_payload;
	
	//The constructor for the Class file
	public message_file() {
		msg_len = 0;
		msg_type = 0;
		msg_payload = null;
		
	}
	// The above methods are defined to return and enable payloads and message types
	
	public void enable_type(int msg_type){
		this.msg_type = msg_type;
	}
	
	public int msg_type_no() {
		return msg_type;
	}
	
	public void enable_payload(byte[] msg_payload) {
		this.msg_payload = msg_payload;
	}
	
	public byte[] recv_payload() {
		return msg_payload;
	}
	
	// Method to scan the incoming message received from the neighboring peer's socket
	public void scan(Socket socket_transm)throws IOException{
		/* Message consists of the following constituents which includes a 4 -byte message length 
		header, 1- byte message type field and a variable-sized payload*/
		int recvd_bytes_full = 0;
		int recvd_bytes;
		InputStream socket_in = socket_transm.getInputStream();
		byte[] recvd_bytes_arr = new byte[4];
		byte[] recvd_bytes_type = new byte[4];
		
		while(recvd_bytes_full < 4) {
			// revd_bytes_arr comprises of the entire length of the message in bytes
			recvd_bytes = socket_in.read(recvd_bytes_arr, recvd_bytes_full, 4 - recvd_bytes_full);
			recvd_bytes_full = recvd_bytes_full + recvd_bytes;
			
		}
		msg_len = change.Bytes_to_Int(recvd_bytes_arr);// it contains the value for no. of bytes read
		
		if(msg_len <= 4) { // if msg_payload is not null then msg_len is greater than 4
			msg_payload = null;
		}else {
			msg_payload = new byte[msg_len - 4];
		}
		
		recvd_bytes_full = 0;// again resetting the full length to 0
		while(recvd_bytes_full < 4) {
			// revd_bytes_type comprises of type of the message in bytes
			recvd_bytes = socket_in.read(recvd_bytes_type, recvd_bytes_full, 4 - recvd_bytes_full);
			recvd_bytes_full = recvd_bytes_full + recvd_bytes;
			
		}
		msg_type = change.Bytes_to_Int(recvd_bytes_type);// it contains the value for no. of bytes read
		
		recvd_bytes_full = 0;// again resetting the full length to 0
		while(recvd_bytes_full < msg_len - 4) {
		
			recvd_bytes = socket_in.read(msg_payload, recvd_bytes_full, (msg_len - 4) - recvd_bytes_full);
			recvd_bytes_full = recvd_bytes_full + recvd_bytes;
			
		}

		
	}
	
	// Method to deliver message to the neighboring peer's socket
	public void deliver(Socket socket_transm)throws IOException{
		// Message consists of the following constituents which includes a 4 -byte message length 
		// header, 1- byte message type field and a variable-sized payload
		OutputStream socket_out = socket_transm.getOutputStream();
		
		if(msg_payload != null) {
			msg_len = msg_payload.length + 4;
		}else {
			msg_len = 4;
		}
		
		socket_out.write(change.Int_to_Bytes(msg_len));
		socket_out.write(change.Int_to_Bytes(msg_type));
		
		if(msg_payload != null) {
			socket_out.write(msg_payload);
		}
		
		socket_out.flush();// Output all the byte arrays from the socket 
	}
	
	
}