package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CodeInputStrategy extends ViewStrategy {

	private JLabel codeInputLabel;
	private JButton codeInputButton;
	private JFileChooser fileChooser;
	
	private File inputFile;
	
	public CodeInputStrategy() {
		setViewPanel();
		setControllerActions();
	}
	
	@Override
	public void setViewPanel() {
		codeInputLabel = new JLabel("Input Source Code");
		codeInputButton = new JButton("Select File");
		this.add(codeInputLabel);
		this.add(codeInputButton);
	}

	@Override
	public void setControllerActions() {
		fileChooser = new JFileChooser();
		codeInputButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnValue = fileChooser.showOpenDialog(getParent());
				if(returnValue == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile() != null) {
					inputFile = fileChooser.getSelectedFile();
					// Call validate source code from this file
					// Then Call update input file event to update source code to controller.
				} else {
					showErrorDialog("No Source Code Selected");
				}
			}
		});
	}

	@Override
	public void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Error Message",
		        JOptionPane.ERROR_MESSAGE);
	}

}
