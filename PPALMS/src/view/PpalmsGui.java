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
		this.setResizable(true);
		this.setTitle("PPALMS v0.0.1");
		
		// The default view strategy on launch will be CodeInputStrategy
		this.viewStrategy = new CodeInputStrategy();
		this.add(viewStrategy);
		
		this.setVisible(true);
	}
	
	public void setCommunicationActions() {
		//TODO
	}
	
}
