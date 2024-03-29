package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * ViewStrategy 
 * 
 * @brief GUI state blueprint.
 * The PpalmsGui uses this to give behavior to
 * the display of the user interface.
 * 
 * In the PPALMS design document, ViewStrategy
 * is one of the classes specified. It's purpose
 * is reflected in that document.
 * 
 * @author Anthony Narlock
 *
 */
public abstract class ViewStrategy extends JPanel {
	
	/**
	 * @brief Sets and defines the components of the strategy.
	 */
	public abstract void setViewPanel();
	
	/**
	 * @brief Sets predefined component actions.
	 */
	public abstract void setControllerActions();
	
	/**
	 * @brief Displays an error message dialog when an error occurs.
	 * @param message
	 */
	public void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Error Message",
		        JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * @brief ViewStrategy's constructor will call
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
