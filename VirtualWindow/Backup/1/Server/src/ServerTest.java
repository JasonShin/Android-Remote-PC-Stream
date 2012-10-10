import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
	private static ServerSocket server;
	private static Socket connection;
	private static OutputStream outputStream;

	public static void main(String[] args) {
		try {
			server = new ServerSocket(80, 100);
			System.out.println("Waiting for the connection");
			connection = server.accept();
			System.out.println("connected with the client");
			File f = new File("test.m4a");
			InputStream inputStream = new FileInputStream(f);
			//byte buf[] = new byte[16384];
			int totalBytesRead = 0, incrementalBytesRead = 0;
			int totalKbRead = 0;
			outputStream = connection.getOutputStream();
			while(true){
				int numread = inputStream.read();
				if(numread < 0){
					break;
				}
				outputStream.write(numread);
				totalBytesRead += numread;
				incrementalBytesRead += numread;
				totalKbRead = totalBytesRead / 1000;
				System.out.println("increment: " + incrementalBytesRead + "  total bytes read: " + totalBytesRead);
			}
			System.out.println("total kb: " + totalKbRead);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ServerTest() {

	}
}
