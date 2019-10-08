package com.qqserver.model;

import java.io.*;
import java.net.*;

import com.qq.common.*;

public class ServerModel {
	
	ServerSocket ss;
	ConnectorThreadManager ctm;
	
	public ServerModel() {
		
		try {
			ss = new ServerSocket(9999);
			ctm = new ConnectorThreadManager();
			
			while(true) {
				System.out.println("Server is activated and listening ... ");
				Socket sk = ss.accept();
				System.out.println("Get connected");
				ObjectInputStream ois = new ObjectInputStream(sk.getInputStream());
				User user = (User) ois.readObject();
				System.out.println(user.getUserID()+" logs in");
				ObjectOutputStream oos = new ObjectOutputStream(sk.getOutputStream());
				Message me = new Message();
				if(user.getPassword().equals("12345")) {
					me.setType(1);
					oos.writeObject(me);
					
					ServerConnectClientThread scct = new ServerConnectClientThread(user.getUserID(),sk);
					//store the information of socket and sender into a hashmap
					ctm.addClientThread(scct.getUserID(), scct);
					scct.start();

				}else {
					me.setType(2);
					oos.writeObject(me);
					sk.close();
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @return the ss
	 */
	public ServerSocket getSs() {
		return ss;
	}

	/**
	 * @param ss the ss to set
	 */
	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}

	/**
	 * @return the ctm
	 */
	public ConnectorThreadManager getCtm() {
		return ctm;
	}

	/**
	 * @param ctm the ctm to set
	 */
	public void setCtm(ConnectorThreadManager ctm) {
		this.ctm = ctm;
	}
	
}
