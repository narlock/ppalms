package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.PpalmsInputHandler;

public class PpalmsGui extends JFrame {
	
	private ViewStrategy viewStrategy;
	private PpalmsInputHandler controller;
	
	public PpalmsGui() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setTitle("PPALMS v0.0.1");
		
		// The default view strategy on launch will be CodeInputStrategy
		this.viewStrategy = new CodeInputStrategy();
		this.add(viewStrategy);
		
		this.controller = new PpalmsInputHandler();
		
		this.setVisible(true);
		this.setCommunicationActions();
	}
	
	public void setCommunicationActions() {
		if (this.viewStrategy instanceof CodeInputStrategy) 
		{
			JButton codeInputButton = ((CodeInputStrategy) viewStrategy).getCodeInputButton();
			codeInputButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					File inputFile;
					JFileChooser fileChooser = ((CodeInputStrategy) viewStrategy).getFileChooser();
					int returnValue = fileChooser.showOpenDialog(null);
					if(returnValue == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile() != null) {
						inputFile = fileChooser.getSelectedFile();
						System.out.println(inputFile.getAbsolutePath());
						if(!controller.processInput(new JTextField(inputFile.getAbsolutePath()), "sourceCodeExtension")) {
							viewStrategy.showErrorDialog("Invalid File Extension");
						} else {
							Path filePath = Path.of(inputFile.getAbsolutePath());
							try {
								String content = Files.readString(filePath);
								controller.processInput(new JTextField(content), "sourceCode");
								updateViewStrategy(new LMSInputStrategy());
							} catch (IOException e1) {
								viewStrategy.showErrorDialog("An IOException was thrown.");
							}
						}
					} else {
						viewStrategy.showErrorDialog("No Source Code Selected");
					}
				}
			});
			
		} 
		else if (this.viewStrategy instanceof LMSInputStrategy) 
		{
			JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) viewStrategy).getLmsTargetComboBox();
			JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) viewStrategy).getProblemTypeComboBox();
			JButton confirmLmsTargetButton = ((LMSInputStrategy) viewStrategy).getConfirmLmsTargetButton();
			lmsTargetComboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!controller.processInput(lmsTargetComboBox, "lmsTarget")) {
						problemTypeComboBox.setEnabled(false);
						confirmLmsTargetButton.setEnabled(false);
					} else {
						problemTypeComboBox.setEnabled(true);
					}
				}
			});
			problemTypeComboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!controller.processInput(problemTypeComboBox, "problemType")) {
						confirmLmsTargetButton.setEnabled(false);
					} else {
						confirmLmsTargetButton.setEnabled(true);
					}
				}
			});
			confirmLmsTargetButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateViewStrategy(new ProblemInputStrategy());
				}
			});
			
		} 
		else if(this.viewStrategy instanceof ProblemInputStrategy) 
		{
			
		}
	}
	
	public void updateViewStrategy(ViewStrategy newStrategy) {
		this.setSize(499,500);
		this.remove(viewStrategy);
		viewStrategy = newStrategy;
		setCommunicationActions();
		this.add(viewStrategy);
		this.repaint();
		this.setSize(500,500);
	}
	
}
