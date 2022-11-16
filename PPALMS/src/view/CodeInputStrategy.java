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
		super();
	}
	
	@Override
	public void setViewPanel() {
		fileChooser = new JFileChooser();
		codeInputLabel = new JLabel("Input Source Code");
		codeInputButton = new JButton("Select File");
		this.add(codeInputLabel);
		this.add(codeInputButton);
	}

	public JLabel getCodeInputLabel() { return this.codeInputLabel; }
	public JButton getCodeInputButton() { return this.codeInputButton; }
	public JFileChooser getFileChooser() { return this.fileChooser; }

	@Override
	public void setControllerActions() {
		// TODO Auto-generated method stub
	}
}
