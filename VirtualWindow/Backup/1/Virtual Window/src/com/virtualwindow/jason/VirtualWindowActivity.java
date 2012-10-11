package com.virtualwindow.jason;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import com.virtualwindow.media.StreamingMediaPlayer;
import com.virtualwindow.wifi.WifiHandler;
/*
 import javax.jmdns.JmDNS;
 import javax.jmdns.ServiceEvent;
 import javax.jmdns.ServiceInfo;
 import javax.jmdns.ServiceListener;

 */

import android.app.Activity;
import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.MulticastLock;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VirtualWindowActivity extends Activity {

	private Button streamButton;

	private ImageButton playButton;

	private TextView textStreamed;

	private boolean isPlaying;

	private StreamingMediaPlayer audioStreamer;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// UDPTest();
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				Log.e("bc", "thread started!");

				// UTPDirectTest();
				// UDPTest();
				UDPBroadcastTest();

			}

		});
		t1.start();
		// zero();
	}

	private void UDPBroadcastTest() {
		// TODO Auto-generated method stub
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		if (wifi != null) {
			WifiManager.MulticastLock lock = wifi
					.createMulticastLock("Log_Tag");
			lock.acquire();

		}
		int DISCOVERY_PORT = 1989;
		int TIMEOUT_MS = 5000;

		try {

			DatagramSocket socket = new DatagramSocket(DISCOVERY_PORT);
			socket.setBroadcast(true);
			socket.setSoTimeout(TIMEOUT_MS);
			byte[] buf = new byte[1024];
			while (true) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length,
						InetAddress.getByName("192.168.1.255"), DISCOVERY_PORT);
				socket.receive(packet);
				Log.e("nbc", packet.getAddress().getHostAddress());
				String s = new String(packet.getData(), 0, packet.getLength());
				Log.e("nbc", "Received response " + s);

			}

		} catch (IOException e) {
			Log.e("nbc", "Could not send discovery request", e);
		}

	}

	public void zero() {
		String type = "_http._tcp.local.";
		try {

			WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

			if (wifi != null) {
				WifiManager.MulticastLock lock = wifi
						.createMulticastLock("Log_Tag");
				lock.acquire();

			}

			JmDNS jmdns = JmDNS.create();

			jmdns.addServiceListener(type, new JMDNSListener());

			ServiceInfo[] serviceInfos = jmdns.list(type);
			for (int i = 0; i < serviceInfos.length; i++) {
				Log.e("z", "## resolve service " + serviceInfos[i]);
			}
			// System.out.println("Name: " + serviceInfos[0].getName() +
			// " url: " + serviceInfos[0].getType());
			/*
			 * for (ServiceInfo info : serviceInfos) { //
			 * System.out.println("## resolve service " + info.getName() + " : "
			 * + info.getURL()); Log.e("z", "## resolve service " +
			 * info.getName() + " : " + info.getURL()); }
			 */
			Log.e("z", "worked7");
			jmdns.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("z", "IOException while creating jmdns: " + e);
		}
	}

	class JMDNSListener implements ServiceListener {

		public void serviceAdded(ServiceEvent event) {
			// TODO Auto-generated method stub
			Log.e("z", "added serivce: " + event);
		}

		public void serviceRemoved(ServiceEvent event) {
			// TODO Auto-generated method stub
			Log.e("z", "removed serivce: " + event);
		}

		public void serviceResolved(ServiceEvent event) {
			// TODO Auto-generated method stub
			Log.e("z", "resolved serivce: " + event);
		}

	}

	public void UTPDirectTest() {
		try {
			DatagramSocket socket = new DatagramSocket(444);

			byte[] buffer = new byte[256];
			InetAddress IP = InetAddress.getByName("138.25.110.244");
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
					IP, 444);
			socket.send(packet);

			packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);
			String message = new String(packet.getData(), 0, packet.getLength());
			Log.e("bc", "message: " + message);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void UDPTest() {
		try {
			/*
			 * WifiManager wifi = (WifiManager)getSystemService(
			 * Context.WIFI_SERVICE ); if(wifi != null){
			 * WifiManager.MulticastLock lock =
			 * wifi.createMulticastLock("Log_Tag"); lock.acquire();
			 * 
			 * }
			 */

			Log.e("bc", "worked 0");
			MulticastSocket socket = new MulticastSocket(5001);
			Log.e("bc", "worked 1");
			InetAddress IP = InetAddress.getByName("239.255.255.223");
			Log.e("bc", "worked 2");
			socket.joinGroup(IP);
			Log.e("bc", "worked 3");

			DatagramPacket packet;

			for (int i = 0; i < 10; i++) {
				Log.e("bc", "about to receive a message");
				byte[] buffer = new byte[256];
				packet = new DatagramPacket(buffer, buffer.length);

				socket.receive(packet);
				String message = new String(packet.getData(), 0,
						packet.getLength());
				Log.e("bc", "recieved message: " + message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("bc", "IO: " + e);
		}
	}

	private void initControls() {
		textStreamed = (TextView) findViewById(R.id.tv_test);
		streamButton = (Button) findViewById(R.id.button_stream);
		streamButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				startStreamingAudio();
			}
		});

		playButton = (ImageButton) findViewById(R.id.button_play);
		playButton.setEnabled(false);
		playButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (audioStreamer.getMediaPlayer().isPlaying()) {
					audioStreamer.getMediaPlayer().pause();
					playButton.setImageResource(R.drawable.ic_launcher);
				} else {
					audioStreamer.getMediaPlayer().start();
					audioStreamer.startPlayProgressUpdater();
					playButton.setImageResource(R.drawable.ic_launcher);
				}
				isPlaying = !isPlaying;
			}
		});
	}

	private void startStreamingAudio() {

		final ProgressBar progressBar = (ProgressBar) findViewById(R.id.pb_progress);
		if (audioStreamer != null) {
			audioStreamer.interrupt();
		}
		audioStreamer = new StreamingMediaPlayer(this, textStreamed,
				playButton, streamButton, progressBar);
		WifiHandler wifi = new WifiHandler("138.25.110.169", 80);
		wifi.connectToServer();
		// wifi.waitForConnection();
		// audioStreamer.startStreaming(wifi.getConnection(), 8417, 199);

		// streamButton.setEnabled(false);

	}

}