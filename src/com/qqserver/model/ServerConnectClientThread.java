package com.qqserver.model;

import java.net.*;

import com.qq.common.*;

import java.io.*;

/**
 * This class can be used to communicate with the client
 * @author Zhang
 *
 */
public class ServerConnectClientThread extends Thread{
	
	private Socket sk;
	private String userID;
	
	public ServerConnectClientThread(String userID,Socket sk) {
		//give the socket of server and client to this thread
		this.userID=userID;
		this.sk=sk;
		
	}
	
	public void transferInfo(Socket socketOfGetter, Message mes) {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socketOfGetter.getOutputStream());
			oos.writeObject(mes);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		
		while(true) {
			
			try {
				ObjectInputStream ois = new ObjectInputStream(sk.getInputStream());
				Message me = (Message) ois.readObject();
				System.out.println("Sender is "+me.getSender());
				System.out.println("Content is "+me.getContentOfMessage());
				System.out.println("Type of message is "+me.getType());
				
				//transfer the message
				//obtain the socket of getter
				ServerConnectClientThread scct_getter = ConnectorThreadManager.getClientThread(me.getGetter());
				Socket socketOfGetter = scct_getter.getSk();
				this.transferInfo(socketOfGetter, me);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the sk
	 */
	public Socket getSk() {
		return sk;
	}

	/**
	 * @param sk the sk to set
	 */
	public void setSk(Socket sk) {
		this.sk = sk;
	}
	
	
}
