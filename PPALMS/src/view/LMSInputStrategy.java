package view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * LMSInputStrategy
 * 
 * @brief The view "state" in which
 * the PPALMS application will allow the user to input
 * the target LMS and the type of Parson's problem
 * to create.
 * 
 * In the PPALMS design document, LMSInputStrategy
 * is one of the classes specified. It's purpose
 * is reflected in that document.
 * 
 * @author Anthony Narlock
 *
 */
public class LMSInputStrategy extends ViewStrategy {

	/**
	 * @brief The label component indicating the target LMS.
	 */
	private JLabel lmsTargetLabel;
	
	/**
	 * @brief The combo box (if the term "combo box" is unfamiliar,
	 * think of it as a "drop down menu") component that
	 * the user can input to select their target LMS.
	 */
	private JComboBox<String> lmsTargetComboBox;
	
	/**
	 * @brief The label component indicating the problem
	 * type.
	 */
	private JLabel problemTypeLabel;
	
	/**
	 * @brief The combo box (if the term "combo box" is unfamiliar,
	 * think of it as a "drop down menu") component that
	 * the user can input to select their target LMS.
	 */
	private JComboBox<String> problemTypeComboBox;
	
	/**
	 * @brief The label component indicating the number
	 * of students.
	 */
	private JLabel numberOfStudentsLabel;
	
	/**
	 * @brief The spinner component for selecting the
	 * number of students.
	 * 
	 * The JSpinner forces the number to be within the bounds.
	 * It will not update upon invalid input.
	 */
	private JSpinner numberOfStudentsSpinner;
	
	/**
	 * @brief The button component that the user can click
	 * to confirm their selections.
	 */
	private JButton confirmLmsTargetButton;
	
	/**
	 * @brief Sets the default view panel for LMSInputStrategy.
	 * Generates a LMSTarget ComboBox with LMS options for selection,
	 * And the same for ProblemType ComboBox. 
	 */
	@Override
	public void setViewPanel() {
		JPanel verticalPanel = new JPanel();
			verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
		lmsTargetLabel = new JLabel("Select LMS");
		lmsTargetComboBox = new JComboBox<String>();
			lmsTargetComboBox.addItem("Expand");
			lmsTargetComboBox.addItem("Canvas");
			lmsTargetComboBox.addItem("D2L");
			lmsTargetComboBox.addItem("Absorb");
			lmsTargetComboBox.addItem("Matrix");
			lmsTargetComboBox.addItem("Talent");
		problemTypeLabel = new JLabel("Select Problem Type");
		problemTypeComboBox = new JComboBox<String>();
			problemTypeComboBox.addItem("Expand");
			problemTypeComboBox.addItem("Ordering");
		numberOfStudentsLabel = new JLabel("Number of Students");
		SpinnerModel value = new SpinnerNumberModel(1, 1, 1000, 1);
		numberOfStudentsSpinner = new JSpinner(value);
		confirmLmsTargetButton = new JButton("Confirm");
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		verticalPanel.add(lmsTargetLabel);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		verticalPanel.add(lmsTargetComboBox);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		verticalPanel.add(problemTypeLabel);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		verticalPanel.add(problemTypeComboBox);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		verticalPanel.add(numberOfStudentsLabel);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		verticalPanel.add(numberOfStudentsSpinner);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		verticalPanel.add(confirmLmsTargetButton);
		this.add(verticalPanel);
	}

	/**
	 * @brief Default controller actions with both problemType
	 * ComboBox and LmsTarget Button components disabled. 
	 */
	@Override
	public void setControllerActions() {
		problemTypeComboBox.setEnabled(false);
		numberOfStudentsSpinner.setEnabled(false);
		confirmLmsTargetButton.setEnabled(false);
	}
	
	/**
	 * @brief Getter that returns LMSTargetLabel component
	 * 
	 * @return lmsTargetlabel
	 */
	public JLabel getLmsTargetLabel() { return lmsTargetLabel; }
	
	/**
	 * @brief Getter that returns LMSTargetLabel component
	 * 
	 * @return lmsTargetComboBox
	 */
	public JComboBox<String> getLmsTargetComboBox() { return lmsTargetComboBox; }
	
	/**
	 * @brief Getter that returns LMSTargetLabel component
	 * 
	 * @return problemTypeLabel
	 */
	public JLabel getProblemTypeLabel() { return problemTypeLabel; }
	
	/**
	 * @brief Getter that returns problemType ComboBox component
	 * 
	 * @return problemTypeComboBox
	 */
	public JComboBox<String> getProblemTypeComboBox() { return problemTypeComboBox; }
	
	/**
	 * @brief Getter that returns confirmLmsTargetButton component
	 * 
	 * @return confirmLmsTargetButton
	 */
	public JButton getConfirmLmsTargetButton() { return confirmLmsTargetButton; }

	/**
	 * @brief Getter that returns numberOfStudentsSpinner component
	 * 
	 * @return numberOfStudentsSpinner
	 */
	public JSpinner getNumberOfStudentsSpinner() { return numberOfStudentsSpinner; }
}
