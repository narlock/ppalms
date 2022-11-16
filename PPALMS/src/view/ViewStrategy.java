package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class ViewStrategy extends JPanel {
	public abstract void setViewPanel();
	public abstract void setControllerActions();
	public void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Error Message",
		        JOptionPane.ERROR_MESSAGE);
	}
	
	public ViewStrategy() {
		setViewPanel();
		setControllerActions();
	}
}
