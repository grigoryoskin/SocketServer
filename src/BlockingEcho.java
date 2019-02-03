import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;

public class BlockingEcho implements Callable<Void>{
	private Socket connection;
	OutputStream out = null;
	InputStream in = null;
	public BlockingEcho(Socket connection) {
		super();
		this.connection = connection;
		try {
			this.out = connection.getOutputStream();
			this.in = connection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public BlockingEcho() {
		super();
		
	}

	public  Void call() {
		
		// TODO Auto-generated method stub
		
		
		//while(true) {
		
		try {
			System.out.println("connection accepted");
			 BufferedReader br = new BufferedReader(new InputStreamReader(in));
		        PrintWriter writer 
		          = new PrintWriter(new OutputStreamWriter(out));
				System.out.println("IO initialized");

	            while (true) {
	                String str = br.readLine(); 
	                if (str == null) {
	                	System.out.println("client closed");
	                  break; // client closed connection 
	                } else {
	                	writer.println("Echo: " + str); 
	        			System.out.println("Echo: " + str);

	                	writer.flush();
	                  if (str.trim().equals("BYE"))    
	                    break; 
	                }
	            }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
		try {
			if(connection != null) {
            	System.out.println("closing connection");

		connection.close();
		}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		//}
		
		return null;
		
	}
}
