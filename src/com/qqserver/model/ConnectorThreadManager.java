package com.qqserver.model;
/**
 * This is a class for handling all the threads 
 * that are create when one client connect to the server
 * @author Zhang
 *
 */

import java.util.*;

public class ConnectorThreadManager {
	
	private static HashMap<String, ServerConnectClientThread> threadCollection;
	
	public ConnectorThreadManager() {
		threadCollection = new HashMap<String, ServerConnectClientThread>();
	}
	
	public static void addClientThread(String userID, ServerConnectClientThread scct) {
		
		threadCollection.put(userID, scct);
	}
	
	public static ServerConnectClientThread getClientThread(String userID) {
		
		return threadCollection.get(userID);
	}
}
