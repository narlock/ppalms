package view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * LMSInputStrategy is the view "state" in which
 * the PPALMS application will allow the user to input
 * the target LMS and the type of Parson's problem
 * to create.
 * 
 * @author narlock
 *
 */
public class LMSInputStrategy extends ViewStrategy {

	/**
	 * The label component indicating the target LMS.
	 */
	private JLabel lmsTargetLabel;
	
	/**
	 * The combo box (if the term "combo box" is unfamiliar,
	 * think of it as a "drop down menu") component that
	 * the user can input to select their target LMS.
	 */
	private JComboBox<String> lmsTargetComboBox;
	
	/**
	 * The label component indicating the problem
	 * type.
	 */
	private JLabel problemTypeLabel;
	
	/**
	 * The combo box (if the term "combo box" is unfamiliar,
	 * think of it as a "drop down menu") component that
	 * the user can input to select their target LMS.
	 */
	private JComboBox<String> problemTypeComboBox;
	
	/**
	 * The button component that the user can click
	 * to confirm their selections.
	 */
	private JButton confirmLmsTargetButton;
	
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
		verticalPanel.add(confirmLmsTargetButton);
		this.add(verticalPanel);
	}

	@Override
	public void setControllerActions() {
		problemTypeComboBox.setEnabled(false);
		confirmLmsTargetButton.setEnabled(false);
	}
	
	public JLabel getLmsTargetLabel() { return lmsTargetLabel; }
	public JComboBox<String> getLmsTargetComboBox() { return lmsTargetComboBox; }
	public JLabel getProblemTypeLabel() { return problemTypeLabel; }
	public JComboBox<String> getProblemTypeComboBox() { return problemTypeComboBox; }
	public JButton getConfirmLmsTargetButton() { return confirmLmsTargetButton; }

}
