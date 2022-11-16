package controller;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JTextField;

import model.PpalmsLogicHandler;
import model.PpalmsProblem;

public class PpalmsInputHandler {
	private PpalmsProblem problem;
	private PpalmsLogicHandler problemHandler;
	private boolean readyToProceed;
	
	public PpalmsInputHandler() {
		this.problem = new PpalmsProblem();
		this.problemHandler = new PpalmsLogicHandler();
		this.readyToProceed = false;
	}
	
	public boolean processInput(JComponent component, String event) {
		//TODO
		switch (event) {
			case "sourceCodeExtension":
				//This means that component is the text field being sent.
				problem.setSourceCode(((JTextField) component).getText());
				if(problemHandler.validateCodeInput(problem)) { 
					readyToProceed = true; 
					return true;
				}
				break;
		}
			
		return false;
	}

	public boolean isReadyToProceed() {
		return readyToProceed;
	}

	public void setReadyToProceed(boolean readyToProceed) {
		this.readyToProceed = readyToProceed;
	}
}
