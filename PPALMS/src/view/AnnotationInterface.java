package view;

import javax.swing.JComponent;
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
public abstract class AnnotationInterface extends JComponent{
	private PpalmsInputHandler controller;
	private PpalmsProblem problem;
	
	/**
	 * @brief AnnotationInterface's constructor contains the 
	 * controller (InputHandler) and the problem (PpalmsProblem)
	 */
	public AnnotationInterface() {
		this.controller = new PpalmsInputHandler();
		this.problem = new PpalmsProblem();
	}
	
	/**
	 * @brief Function to set up the visual components of the interface
	 */
	public abstract void setupInterface();
	
	/**
	 * @brief Function to set the actions of the annotations with respect to the InputHandler
	 */
	public abstract void setAnnotationActions();
}
