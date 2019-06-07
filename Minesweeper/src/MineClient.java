import java.io.*;
import java.net.*;

public class MineClient {
	private boolean connected;
	private String response;
	
	public MineClient(int difficulty, String name, int time) {
		String str = new String(Integer.toString(difficulty)+ "\n" + name + "\n" + Integer.toString(time) + "\n");
		response = null;
		try {
			String serverIp = "localhost";
			Socket soc = new Socket(serverIp, 1234);
			OutputStream out = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			dos.writeUTF(str);
			
			InputStream in = soc.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			while (response == null) {
				response = dis.readUTF();
			}
			
			dos.close();
			dis.close();
			soc.close();
			
			connected = true;
		}
		catch (UnknownHostException e) {
			connected = false;
		}
		catch (IOException e) {
			connected = false;
		}
	}
	
	public String getData() {
		return response;
	}
	
	public boolean isConnected() {
		return connected;
	}
}
