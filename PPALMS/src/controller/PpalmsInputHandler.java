package controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import model.LmsTarget;
import model.PpalmsLogicHandler;
import model.PpalmsProblem;
import model.ProblemType;

/**
 * PpalmsInputHandler serves as the controller of the
 * PPALMS application. This class handles the inputs
 * of the user.
 * 
 * @author narlock
 *
 */
public class PpalmsInputHandler {
	
	/**
	 * The PpalmsProblem that the user is creating.
	 * The user's inputs will modify this problem.
	 */
	private PpalmsProblem problem;
	
	/**
	 * See {@link PpalmsLogicHandler} for details.
	 */
	private PpalmsLogicHandler problemHandler;
	
	/**
	 * The constructor of the PpalmsInputHandler
	 * instantiates its member attributes.
	 */
	public PpalmsInputHandler() {
		this.problem = new PpalmsProblem();
		this.problemHandler = new PpalmsLogicHandler();
	}
	
	/**
	 * Processes a given user input.
	 * 
	 * @param component
	 * @param event
	 * @return true for successful input, false for unsuccessful input.
	 */
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
				if(problemHandler.exportPpalmsProblem(problem)) {
					return true;
				}
				break;
		}
			
		return false;
	}
	
	/**
	 * Processes a given user input specific
	 * for source code lines.
	 * 
	 * @param lines
	 * @return true for successful input, false for unsuccessful input.
	 */
	public boolean processInput(List<String> lines) {
		problem.setSourceCodeLines(lines);
		return true;
	}
	
	/**
	 * Processes a given user input specific
	 * for adding an annotation.
	 * 
	 * @param index
	 * @param event
	 * @return true for successful input, false for unsuccessful input.
	 */
	public boolean processInput(int index, String event) {
		switch (event) {
		case "addAnnotation":
			problem.getAnnotations().add(index);
			return true;
		}
		return false;
	}
	
	public PpalmsProblem getPpalmsProblem() { return problem; }
}
