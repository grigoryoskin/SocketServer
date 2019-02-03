import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
public static void main(String[] args) {
	ExecutorService pool = Executors.newFixedThreadPool(50);
	try(ServerSocket server = new ServerSocket(8080)){
		while(true) {
		Socket connection = server.accept();
	    pool.submit(new BlockingEcho(connection));
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
