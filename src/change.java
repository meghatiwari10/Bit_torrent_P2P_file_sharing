import java.nio.ByteBuffer;
import java.awt.image.ByteLookupTable;

public class change{
	public static int Bytes_to_Int(byte[] input) {
		int res = 0;
		int pos = 0;
		
		while(pos < 4){
			res = (res << 8) | (input[pos] & 0xFF);
			pos++;
		}
		return res;
		
	}
	
	public static byte[] Int_to_Bytes(int input) {
		byte[] output = new byte[4];
		output[0] = (byte)((input & 0xff000000) >> 24);
		output[1] = (byte)((input & 0xff0000) >> 16);
		output[2] = (byte)((input & 0xff00) >> 8);
		output[3] = (byte)((input & 0xff));
		
		return output;
	}
		
}
