package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import controller.PpalmsInputHandler;
import model.PpalmsProblem;

/**
 * AnnotationInterface
 * 
 * @brief The purpose of this class is for it to be  
 * a layer of abstraction that will allow the 
 * implementation of new concrete annotation interfaces 
 * to be easier when implementing new problem types
 * 
 * @author Stephanie Ye
 *
 */
public abstract class AnnotationInterface extends JPanel{
	private PpalmsInputHandler controller;
	private PpalmsProblem problem;
	
	/**
	 * @brief AnnotationInterface's constructor contains the 
	 * controller (InputHandler) and the problem (PpalmsProblem)
	 */
	public AnnotationInterface(PpalmsInputHandler controller, PpalmsProblem problem) {
	    this.controller = controller;
	    this.problem = problem;
	    this.setUpInterface();
	    this.setAnnotationActions();
	}
	
	/**
	 * @brief Function to set up the visual components of the interface
	 */
	public abstract void setUpInterface();
	
	/**
	 * @brief Function to set the actions of the annotations with respect to the InputHandler
	 */
	public abstract void setAnnotationActions();
	

	public PpalmsInputHandler getController() {
		return controller;
	}

	public void setController(PpalmsInputHandler controller) {
		this.controller = controller;
	}

	public PpalmsProblem getProblem() {
		return problem;
	}

	public void setProblem(PpalmsProblem problem) {
		this.problem = problem;
	}
	
}
