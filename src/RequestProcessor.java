import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;

public class RequestProcessor implements Callable<Void>{
private Socket connection;
OutputStream out = null;
InputStream in = null;
public RequestProcessor(Socket connection) {
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
public RequestProcessor() {
	super();
	
}

public Void call() {
	
	// TODO Auto-generated method stub
	
	
	
	
	Date date = new Date();
	try {
		System.out.println("connection accepted!");
		 BufferedReader br = new BufferedReader(new InputStreamReader(in));
		 String header = br.readLine();
		 System.out.println(header);
         String[] headerParts = header.split(" ");
         String method = headerParts[0];
         String path = headerParts[1];
         System.out.println("method " + method);
         System.out.println("method " + path);
         String[] pathParts = path.split("/");
         String name = "";
         if(pathParts.length > 1) {
       	 name = pathParts[1];
         }
         
         
         
         String s = "Hello, " + name + "! <br>" + date.toString()+"\r\n";
         String response = "HTTP/1.1 200 OK\r\n" +
                 
                 "Content-Type: text/html\r\n" +
                 "Content-Length: " + s.length() + "\r\n" +
                 "Connection: close\r\n\r\n";
         
         
        
        
         
		Writer writer = new OutputStreamWriter(out, "ASCII");
		writer.write(response+s);
		
		writer.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
	try {
		if(connection != null) {
	connection.close();
	}
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	return null;
	
}



}
