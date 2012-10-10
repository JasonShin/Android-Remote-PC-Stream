package org.jason.network.model;

import java.io.File;

public interface TCPServerSocket {
	
	void startToAccept();
	void setUpStreams();
	void sendDataToClient(File f);
	void sendDataToClient(String s);
	
}
