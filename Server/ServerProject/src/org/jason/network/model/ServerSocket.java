package org.jason.network.model;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public interface ServerSocket {

	public void setupSocket() throws SocketException, UnknownHostException;
	public void sendData(byte[] buffer) throws IOException;
	public void killThread();
	
}
