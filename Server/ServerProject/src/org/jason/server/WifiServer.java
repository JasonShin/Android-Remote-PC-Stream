package org.jason.server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class WifiServer{
	
	private int port;
	
	public WifiServer(String target, String author){
		System.out.println("Targetting system: " + target + " | author of this system: " + author);
	} 
	
	public void test(){
		System.out.println("worked and port is: " + getPort());
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	//@PostConstruct
	public void testIncrement(){
		port++;
	}
	
	//@PreDestroy
	public void destructor(){
		System.out.println("Object is destroyed!");
	}
	
}
