package controller;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import model.LmsTarget;
import model.PpalmsLogicHandler;
import model.PpalmsProblem;
import model.ProblemType;

public class PpalmsInputHandler {
	private PpalmsProblem problem;
	private PpalmsLogicHandler problemHandler;
	
	public PpalmsInputHandler() {
		this.problem = new PpalmsProblem();
		this.problemHandler = new PpalmsLogicHandler();
	}
	
	public boolean processInput(JComponent component, String event) {
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
			case "lmsTarget":
				String targetLms = (String) ((JComboBox<String>) component).getSelectedItem();
				try {
					problem.setLmsTarget(LmsTarget.valueOf(targetLms));
				} catch (IllegalArgumentException e) {
					return false;
				}
				return true;
			case "problemType":
				String problemType = (String) ((JComboBox<String>) component).getSelectedItem();
				try {
					problem.setProblemType(ProblemType.valueOf(problemType));
				} catch (IllegalArgumentException e) {
					return false;
				}
				return true;
			case "problemTitle":
				String problemTitle = ((JTextField) component).getText();
				problem.setTitle(problemTitle);
				if(problemHandler.validateTitleDescInput(problem)) {
					return true;
				}
				break;
			case "problemDescription":
				String problemDescription = ((JTextArea) component).getText();
				problem.setDescription(problemDescription);
				if(problemHandler.validateTitleDescInput(problem)) {
					return true;
				}
				break;
			case "exportProblem":
				//TODO Trigger export problem
				System.out.println("Exporting Problem!");
				break;
		}
			
		return false;
	}
}
