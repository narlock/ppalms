package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.platform.commons.util.StringUtils;

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
//		this.viewStrategy = new ProblemInputStrategy();
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
							try {
								List<String> yourFileLines = Files.readAllLines(Paths.get(inputFile.getAbsolutePath()));
								controller.processInput(yourFileLines);
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
			JTextField titleInputTextField = ((ProblemInputStrategy) viewStrategy).getTitleInputTextField();
			JTextArea descriptionInputTextField = ((ProblemInputStrategy) viewStrategy).getDescriptionInputTextField();
			JButton exportProblem = ((ProblemInputStrategy) viewStrategy).getExportProblem();
			titleInputTextField.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) { } //Could add something to change color

				@Override
				public void focusLost(FocusEvent e) {
					// TODO When we leave the text field, call the update event
					if(controller.processInput(titleInputTextField, "problemTitle")) {
						
					} else {
						viewStrategy.showErrorDialog("An unexpected error occured when validating problem title.");
					}
				}
				
			});
			descriptionInputTextField.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {} //Could add something to change color

				@Override
				public void focusLost(FocusEvent e) {
					// TODO When we leave the text field, call the update event
					if(controller.processInput(descriptionInputTextField, "problemDescription")) {
						
					} else {
						viewStrategy.showErrorDialog("An unexpected error occured when validating problem desc.");
					}
				}
				
			});
			exportProblem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(controller.processInput(null, "exportProblem"))
						System.exit(1);
				}
				
			});
			
			
			JPanel annotationPanel = ((ProblemInputStrategy) viewStrategy).getAnnotationPanel();
			List<String> alines = controller.getPpalmsProblem().getSourceCodeLines();
			for(int i = 0; i < alines.size(); i++) {
				annotationPanel.add(createAnnotationLineButton(exportProblem, i, alines.get(i)));
			}
			refocusFrame();
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
	
	private void refocusFrame() {
		this.setSize(499, 500);
		this.setSize(500, 500);
	}
	
	private JButton createAnnotationLineButton(JButton exportProblem, int index, String line) {
		JButton button = new JButton(line);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(index);
				if(!button.getForeground().equals(Color.GREEN)) {
					button.setForeground(Color.GREEN);
					controller.processInput(index, "addAnnotation");
					exportProblem.setEnabled(true);
				}
			}
			
		});
		return button;
	}
}
