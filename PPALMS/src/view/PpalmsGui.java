package view;

import javax.swing.JFrame;

import controller.PplamsInputHandler;

public class PpalmsGui extends JFrame {
	private ViewStrategy viewStrategy;
	private PplamsInputHandler controller;
	
	public PpalmsGui() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("PPALMS v0.0.1");
		this.setVisible(true);
	}
	
	public void setCommunicationActions() {
		//TODO
	}
	
}
