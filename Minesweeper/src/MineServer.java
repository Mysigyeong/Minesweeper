import java.net.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class MineServer {
	static String[][] nameTable = new String[3][10];
	static int[][] timeTable = new int[3][10];
	
	public static void main(String[] args) {
		String str = null;
		ServerSocket server = null;
		
		try {
			File file = new File("serverdata/table.txt");    //저장되어있는 기록들 불러오기
			Scanner scn = new Scanner(file);
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 10; j++) {
					nameTable[i][j] = new String(scn.nextLine());
					timeTable[i][j] = Integer.parseInt(scn.nextLine());
				}
			}
			
			scn.close();
		}
		catch (FileNotFoundException e) {
			System.exit(1);
		}
		
		
		try {
			server = new ServerSocket(1234);
			System.out.println(getTime() + "Starting Server.\n");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			try {
				str = null;
				
				System.out.println(getTime() + "Waiting Connect.....");
				
				Socket soc = server.accept();
				System.out.println(getTime() + soc.getInetAddress() +" is connected.\n");
				
				InputStream in = soc.getInputStream();
				DataInputStream dis = new DataInputStream(in);
				
				System.out.println(getTime() + "Reading data...");
				while (str == null) {
					str = dis.readUTF();
				}
				System.out.println(getTime() + "Reading data complete.");
				
				
				System.out.println(getTime() + "Updating data...");
				str = updateData(str);
				System.out.println(getTime() + "Updating data complete.");
				
				System.out.println(getTime() + "Writting data...");
				OutputStream out = soc.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF(str);
				System.out.println(getTime() + "Writting data complete.");
				
				try {
					BufferedWriter bout = new BufferedWriter(new FileWriter("serverdata/table.txt"));
					
					bout.write(str);
					
					bout.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
				
				dis.close();
				dos.close();
				
				System.out.println(getTime() + soc.getInetAddress() +" is disconnected.\n");
				soc.close();
				
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private static String updateData(String str) {
		String data[] = str.split("\n");
		String returnData[] = new String[30];
		String ret;
		int diff = Integer.parseInt(data[0]) - 1;
		int time = Integer.parseInt(data[2]);
		
		if (time != -1) {
			for (int i = 0; i < 10; i++) {
				if (timeTable[diff][i] == -1 || time <= timeTable[diff][i]) {
					insert(diff, data[1], time, i);
					break;
				}
			}
		}
		
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 10; j++) {
				returnData[k] = new String(nameTable[i][j] + "\n" +Integer.toString(timeTable[i][j]) + "\n");
				k++;
			}
		}
		
		ret = returnData[0];
		for (int i = 1; i < 30; i++) {
			ret = ret.concat(returnData[i]);
		}
		
		return ret;
	}
	private static void insert(int diff, String name, int time, int idx) {
		for (int i = 8; i >= idx; i--) {
			timeTable[diff][i + 1] = timeTable[diff][i];
			nameTable[diff][i + 1] = nameTable[diff][i];
		}
		
		timeTable[diff][idx] = time;
		nameTable[diff][idx] = name;
	}
	
	static private String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(new Date());
    }
	
}
