package org.jason.server;

import java.util.Arrays;
import java.util.Collections;

import org.jason.network.model.ServerSocket;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	
	
	/*		SCENARIO
	 *  1. App starts up, views initializes, event listeners are set up
	 *  2. User decides to use wifi or bluetooth
	 *  3.1. If user chose WIFI, the app creates a new UDPBroadCaster
	 *  and the class starts to advertise. (It advertise "ServerProject[EncryptedIP,PORT]")
	 *  4. Views file hirarchy
	 *  5. parse that hierarchy into JSON
	 */


	
	public static void main(String[] args){
		
		//Making object from factory by referencing the configuration
		//BeanFactory factory = new XmlBeanFactory(new FileSystemResource("configuration.xml"));
		
		//ApplicationContext is similar to BeanFactory
		AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("configuration.xml");
		appContext.registerShutdownHook();	//init and destructor
		//This makes a singleton object, objects inside the Spring container are all same
		WifiServer wifiServer = (WifiServer) appContext.getBean("wifiServer");
		wifiServer.test();
		wifiServer.testIncrement();
		
	}
	
}
