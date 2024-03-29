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
 * ChooseLinesAddBlank
 * 
 * @brief Concrete implementation of AnnotationInterface.
 * Provides graphical user interface the component for
 * annotation FillInTheBlank problem type.
 * 
 * @author Anthony Narlock
 *
 */
public class ChooseLinesAddBlank extends AnnotationInterface {
	
	/**
	 * @brief a list of each of the problem lines
	 */
	private List<JButton> problemLines;
	
	/**
	 * @brief Combo boxes for a problem line
	 * Used only for testing purposes
	 */
	private List<JComboBox<String>> problemLineComboBox;
	
	/**
	 * The index for a lower bound
	 */
	int lowerBoundIndex;
	
	/**
	 * The index for an upper bound
	 */
	int upperBoundIndex;

	/**
	 * @brief See {@link AnnotationInterface} for definition.
	 */
	public ChooseLinesAddBlank(PpalmsInputHandler controller, PpalmsProblem problem) {
		super(controller, problem);
	}

	/**
	 * @brief See {@link AnnotationInterface} for definition.
	 */
	@Override
	public void setUpInterface() {
		this.problemLines = new ArrayList<JButton>();
		this.problemLineComboBox = new ArrayList<JComboBox<String>>();
	}

	/**
	 * @brief See {@link AnnotationInterface} for definition.
	 */
	@Override
	public void setAnnotationActions() {
		
	}

	/**
	 * @brief A helper function in which creates an annotation line button
	 * that is used in the ProblemInputStrategy. The reason that
	 * this has been taken out is so that the application can
	 * reuse this function for each of the lines the user
	 * inputs.
	 * 
	 * @param exportProblem
	 * @param index
	 * @param line
	 * @return JButton for a line in the input source code.
	 */
	public JPanel createAnnotationLineButtonPanel(JButton exportProblem, int index, String line) {
		JPanel linePanel = new JPanel();
		JButton button = new JButton(line);
		JComboBox<String> lowerBoundComboBox = new JComboBox<String>();
		JComboBox<String> upperBoundComboBox = new JComboBox<String>();
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(index);
				if(!button.getForeground().equals(Color.GREEN)) {
					button.setForeground(Color.GREEN);
					getController().processInput(index, "addAnnotation");
					getController().processInput(index, "createBounds");
					populateLowerBoundComboBox(line);
					lowerBoundComboBox.setVisible(true);
					exportProblem.setEnabled(false);
				}
			}
			
			private void populateLowerBoundComboBox(String line) {
				for(int i = 0; i < line.length(); i++) {
					lowerBoundComboBox.addItem(line.charAt(i) + " (" + i + ")");
				}
			}
			
		});
		problemLines.add(button);
		
		lowerBoundComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().toString().contains("hidden")) {
					System.out.println("setVisible event");
				} else {
					lowerBoundIndex = lowerBoundComboBox.getSelectedIndex();
					getController().processInput(lowerBoundIndex, "setLowerBound", index);
					populateUpperBoundComboBox(line);
					upperBoundComboBox.setVisible(true);
				}
			}
			
			private void populateUpperBoundComboBox(String line) {
				if(lowerBoundIndex + 1 == line.length()) {
					
				} else {
					upperBoundComboBox.removeAllItems();
					for(int i = lowerBoundIndex; i < line.length(); i++) {
						upperBoundComboBox.addItem(line.charAt(i) + " (" + i + ")");
					}
				}
			}
		});
		lowerBoundComboBox.setVisible(false);
		problemLineComboBox.add(lowerBoundComboBox);
		
		upperBoundComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().toString().contains("hidden")) {
					System.out.println("setVisible event");
				} else {
					upperBoundIndex = upperBoundComboBox.getSelectedIndex();
					if(upperBoundIndex + 1 >= line.length()) {
						upperBoundIndex = lowerBoundIndex;
					}
					getController().processInput(upperBoundIndex + 1, "setUpperBound", index);
					exportProblem.setEnabled(true);
				}
			}
			
		});
		upperBoundComboBox.setVisible(false);
		problemLineComboBox.add(upperBoundComboBox);
		
		linePanel.add(lowerBoundComboBox);
		linePanel.add(upperBoundComboBox);
		linePanel.add(button);
		
		return linePanel;
	}
	
	/**
	 * Obtains the first annotation line button
	 * @return first line JButton
	 */
	public JButton getFirstAnnotationLineButton() {
		return problemLines.get(0);
	}

	/**
	 * @brief Getter method for problem line ComboBox index.
	 * @param index
	 * @return problemLineComboBox.get(index)
	 */
	public JComboBox<String> getProblemLineComboBoxIndex(int index) {
		return problemLineComboBox.get(index);
	}
	
	/**
	 * @brief Getter for problem line ComboBox. 
	 * @return problemLineComboBox
	 */
	public List<JComboBox<String>> getProblemLineComboBox() {
		return problemLineComboBox;
	}
}
