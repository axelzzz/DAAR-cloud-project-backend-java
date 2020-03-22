package library;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import py4j.GatewayServer;
import py4j.GatewayServer.GatewayServerBuilder;

public class LibraryEntryPoint {

	private Library library;

    public LibraryEntryPoint() {
      library = Library.getInstance();
    }

    public Library getLibrary() {
        return library;
    }

    public static void main(String[] args) {
    	
    	long startTime1 = System.nanoTime();
    	
    	try {
			InetAddress javaAddr = InetAddress.getByName("aqueous-scrubland-31409.herokuapp.com");
			InetAddress pythonAddr = InetAddress.getByName("cloud-project-backend-django.herokuapp.com");
			//GatewayServer gatewayServer = new GatewayServer(new LibraryEntryPoint());
	        GatewayServer gatewayServer = new GatewayServer(new LibraryEntryPoint(), 25333, 25334, javaAddr, pythonAddr, 0, 0, null);
	       
	        long endTime1 = System.nanoTime();
		    long duration = endTime1-startTime1;
		    duration = duration / 1000000;
		    System.out.println("duree : "+duration+"ms");
		    
	        gatewayServer.start();
	        System.out.println("Gateway Server Started");
		} catch (UnknownHostException e) {			
			e.printStackTrace();
		}         
    }
}
