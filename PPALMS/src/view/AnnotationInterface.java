package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
    
    /**
	 * @brief A helper function in which creates an annotation line button
	 * that is used in the LMSInputStrategy. The reason that
	 * this has been taken out is so that the application can
	 * reuse this function for each of the lines the user
	 * inputs.
	 * 
	 * @param exportProblem
	 * @param index
	 * @param line
	 * @return JButton for a line in the input source code.
	 */
	public JButton createAnnotationLineButton(JButton exportProblem, int index, String line) {
		JButton button = new JButton(line);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(index);
				if(!button.getForeground().equals(Color.GREEN)) {
					button.setForeground(Color.GREEN);
					controller.processInput(index, "addAnnotation");
					exportProblem.setEnabled(true);
				}
			}	
		});
		return button;
	}
	
	/**
	 * @brief Getter method for private method getAnnotationLineButton
	 * 
	 * @param exportProblem
	 * @param index
	 * @param line
	 * @return createAnnotationLineButton(exportProblem, index, line)
	 */
	public JButton getAnnotationLineButton(JButton exportProblem, int index, String line) {
		return createAnnotationLineButton(exportProblem, index, line);
	}
}
