package com.qqserver.view;

import javax.swing.*;

import com.qqserver.model.*;

import java.awt.*;
import java.awt.event.*;

/**
 * This is a GUI panel for turning on/off the server 
 * @author Zhang
 *
 */
public class ServerPanel extends JFrame implements ActionListener{
	
	JButton activateServer, deactivateServer;
	JPanel northPanel;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerPanel sp = new ServerPanel();
	}
	
	public ServerPanel() {
		
		activateServer = new JButton("ACTIVATE");
		activateServer.addActionListener(this);
		activateServer.setActionCommand("activate");
		deactivateServer = new JButton("DEACTIVATE");
		northPanel = new JPanel(new FlowLayout());
		
		northPanel.add(activateServer);
		northPanel.add(deactivateServer);
		
		this.add(northPanel,"North");
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("activate")) {
			System.out.println("Activate my server");
			new ServerModel();
		}
	}
	
}
