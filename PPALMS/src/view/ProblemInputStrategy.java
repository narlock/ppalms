package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProblemInputStrategy extends ViewStrategy {

	private JLabel titleInputLabel;
	private JTextField titleInputTextField;
	private JLabel descriptionInputLabel;
	private JTextField descriptionInputTextField;
	private JLabel annotationsLabel;
	private JTextArea sourceCodeArea; //May be changed... May need something better for ordering.. maybe stacked panels that are movable.
	// private List<JPanel> sourceCodeArea; // We can use this to stack problem lines on top of one another
	private JButton exportProblem;
	
	@Override
	public void setViewPanel() {
		// TODO Auto-generated method stub
		
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
