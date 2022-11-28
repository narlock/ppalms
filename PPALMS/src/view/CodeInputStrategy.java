package view;

import java.awt.Dimension;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * CodeInputStrategy is the view "state" in which
 * the PPALMS application will allow the user to
 * input their source code file for problem
 * creation.
 * 
 * In the PPALMS design document, CodeInputStrategy
 * is one of the classes specified. It's purpose
 * is reflected in that document.
 * 
 * @author Anthony Narlock
 *
 */
public class CodeInputStrategy extends ViewStrategy {

	/**
	 * The label component indicating code input.
	 */
	private JLabel codeInputLabel;
	
	/**
	 * The button component that the user can
	 * click with their mouse to input open
	 * file chooser dialog.
	 */
	private JButton codeInputButton;
	
	/**
	 * The file chooser component that allows
	 * the user to input a file.
	 */
	private JFileChooser fileChooser;
	
	@Override
	public void setViewPanel() {
		JPanel verticalPanel = new JPanel();
		verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
		fileChooser = new JFileChooser();
		codeInputLabel = new JLabel("Input Source Code");
		codeInputButton = new JButton("Select File");
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		verticalPanel.add(codeInputLabel);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		verticalPanel.add(codeInputButton);
		this.add(verticalPanel);
	}

	/**
	 * Getter method for code input label.
	 * 
	 * @return codeInputLabel
	 */
	public JLabel getCodeInputLabel() { return this.codeInputLabel; }
	
	/**
	 * Getter method for code input button.
	 * 
	 * @return codeInputButton
	 */
	public JButton getCodeInputButton() { return this.codeInputButton; }
	
	/**
	 * Getter method for filechooser component.
	 * 
	 * @return fileChooser
	 */
	public JFileChooser getFileChooser() { return this.fileChooser; }

	@Override
	public void setControllerActions() {}
}
