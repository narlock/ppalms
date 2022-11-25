package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * ViewStrategy serves as a GUI state blueprint.
 * The PpalmsGui uses this to give behavior to
 * the display of the user interface.
 * 
 * @author narlock
 *
 */
public abstract class ViewStrategy extends JPanel {
	
	/**
	 * Sets and defines the components of the strategy.
	 */
	public abstract void setViewPanel();
	
	/**
	 * Sets predefined component actions.
	 */
	public abstract void setControllerActions();
	
	/**
	 * Displays an error message dialog when an error occurs.
	 * @param message
	 */
	public void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Error Message",
		        JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * ViewStrategy's constructor will call
	 * the setViewPanel function and setControllerActions
	 * 
	 * This is to provide separation of method purposes
	 * and allows a method to serve a single purpose.
	 */
	public ViewStrategy() {
		setViewPanel();
		setControllerActions();
	}
}
