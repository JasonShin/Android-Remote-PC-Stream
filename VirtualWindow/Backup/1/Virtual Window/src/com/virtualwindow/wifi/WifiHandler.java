package com.virtualwindow.wifi;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import android.text.format.Formatter;
import android.util.Log;

public class WifiHandler {

	private Socket socket;
	private String ip;
	private int port;

	public WifiHandler(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void waitForConnection(){
		try {
			ServerSocket server = new ServerSocket(9999);
			Log.e("ser", "waiting for PC: " + getLocalIpAddress());
			socket = server.accept();
			Log.e("ser", "Connected");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("ser", "IO: " + e);
		}
		
	}

	public String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						String ip = Formatter.formatIpAddress(inetAddress
								.hashCode());
						Log.i("ser", "***** IP=" + ip);
						return ip;
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("ser", ex.toString());
		}
		return null;
	}

	public void connectToServer() {
		try {
			Log.e("c", "worked1: " + ip + " port: " + port);
			socket = new Socket(ip, 9999);
			Log.e("c", "worked2");
			setUpStreams();
			Log.e("c", "worked3");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setUpStreams() {
		try {
			InputStream input = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Socket getConnection() {
		return socket;
	}

}
