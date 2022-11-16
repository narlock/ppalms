package controller;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import model.PpalmsLogicHandler;
import model.PpalmsProblem;

public class PpalmsInputHandler {
	private PpalmsProblem problem;
	private PpalmsLogicHandler problemHandler;
	
	public PpalmsInputHandler() {
		this.problem = new PpalmsProblem();
		this.problemHandler = new PpalmsLogicHandler();
	}
	
	public boolean processInput(JComponent component, String event) {
		//TODO
		switch (event) {
			case "sourceCodeExtension":
				//This means that component is the text field being sent.
				problem.setSourceCode(((JTextField) component).getText());
				if(problemHandler.validateCodeInput(problem)) {  
					return true;
				}
				break;
			case "sourceCode":
				//This event will be called after it has been validated.
				problem.setSourceCode(((JTextField) component).getText());
				break;
		}
			
		return false;
	}
}
