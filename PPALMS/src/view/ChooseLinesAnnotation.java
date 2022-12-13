package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.PpalmsInputHandler;
import model.PpalmsProblem;

/**
 * ChooseLinesAnnotation
 * @brief Provides concrete implementation of methods by extending 
 * the abstract class AnnotationInterface. 
 * 
 * @author Stephanie Ye
 *
 */
public class ChooseLinesAnnotation extends AnnotationInterface {
	
	private List<JButton> problemLines;
	
	/**
	 * @brief Constructor. 
	 * @param controller
	 * @param problem
	 */
	public ChooseLinesAnnotation(PpalmsInputHandler controller, PpalmsProblem problem) {
		super(controller, problem);
	}

	/**
	 * @brief Setter method for the interface.
	 */
	public void setUpInterface() {
		this.problemLines = new ArrayList<JButton>();
	}
	
	/**
	 * @brief Setter method for annotation actions.
	 */
	public void setAnnotationActions() {
		
	}
	
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
					getController().processInput(index, "addAnnotation");
					exportProblem.setEnabled(true);
				}
			}	
		});
		problemLines.add(button);
		return button;
	}
	
	/**
	 * Getter for JUnit tests
	 * @return first line JButton
	 */
	public JButton getFirstAnnotationLineButton() {
		return problemLines.get(0);
	}
}
