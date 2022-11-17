package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LMSInputStrategy extends ViewStrategy {

	private JLabel lmsTargetLabel;
	private JComboBox<String> lmsTargetComboBox;
	private JLabel problemTypeLabel;
	private JComboBox<String> problemTypeComboBox;
	private JButton confirmLmsTargetButton;
	
	public LMSInputStrategy() {
		super();
	}
	
	@Override
	public void setViewPanel() {
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
		this.add(lmsTargetLabel);
		this.add(lmsTargetComboBox);
		this.add(problemTypeLabel);
		this.add(problemTypeComboBox);
		this.add(confirmLmsTargetButton);
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
