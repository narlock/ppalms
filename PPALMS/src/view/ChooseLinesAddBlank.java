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
	private List<JComboBox<Character>> problemLineComboBox;
	
	/**
	 * The index for a lower bound
	 */
	int lowerBoundIndex;
	
	/**
	 * The index for an upper bound
	 */
	int upperBoundIndex;

	public ChooseLinesAddBlank(PpalmsInputHandler controller, PpalmsProblem problem) {
		super(controller, problem);
	}

	@Override
	public void setUpInterface() {
		this.problemLines = new ArrayList<JButton>();
		this.problemLineComboBox = new ArrayList<JComboBox<Character>>();
	}

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
		JComboBox<Character> lowerBoundComboBox = new JComboBox<Character>();
		JComboBox<Character> upperBoundComboBox = new JComboBox<Character>();
		
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
					lowerBoundComboBox.addItem(line.charAt(i));
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
					for(int i = lowerBoundIndex + 1; i < line.length(); i++) {
						upperBoundComboBox.addItem(line.charAt(i));
					}
					upperBoundComboBox.setSelectedIndex(0);
				}
			}
		});
		lowerBoundComboBox.setVisible(false);
		problemLineComboBox.add(lowerBoundComboBox);
		
		upperBoundComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				upperBoundIndex = upperBoundComboBox.getSelectedIndex();
				getController().processInput(upperBoundIndex, "setUpperBound", index);
				exportProblem.setEnabled(true);
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
	 * Getter for JUnit tests
	 * @return first line JButton
	 */
	public JButton getFirstAnnotationLineButton() {
		return problemLines.get(0);
	}

	public JComboBox<Character> getProblemLineComboBoxIndex(int index) {
		return problemLineComboBox.get(index);
	}
	
	public List<JComboBox<Character>> getProblemLineComboBox() {
		return problemLineComboBox;
	}
}
