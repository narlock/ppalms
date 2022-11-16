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
		// TODO Auto-generated method stub
		lmsTargetLabel = new JLabel("Hello World");
		this.add(lmsTargetLabel);
	}

	@Override
	public void setControllerActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showErrorDialog(String message) {
		// TODO Auto-generated method stub
		
	}

}
