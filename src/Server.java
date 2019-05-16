import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {
	public static void main(String[] args) {
		String address = "";
		int port = 0;		
		
		Properties prop = new Properties();
	    InputStream input = null;
	    
	    ServerSocket ss = null;

	    try {
	        input = new FileInputStream("config.properties");
	        prop.load(input);
	        address = prop.getProperty("ip");
	        port = Integer.parseInt(prop.getProperty("port"));
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    } finally {
	        if (input != null) {
	            try {
	                input.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
				
		try {
			ss = new ServerSocket(port, 1, InetAddress.getByName(address));  
			System.out.println("Created new SS");
			Socket s = ss.accept();
			System.out.println("Connected");
			
			String data = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));        
	        while ((data = in.readLine()) != null) {
	            System.out.println("\r\nMessage: " + data);
	        }
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
	}

}
