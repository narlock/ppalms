package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.PpalmsInputHandler;

/**
 * The PPALMS Graphical User Interface.
 * This is the interface in which the user will
 * interact with a keyboard, mouse, and display.
 * 
 * The user will interact with this GUI to
 * interact with the application to create
 * their Parson's problem.
 * 
 * The PpalmsGui serves as the view to the user
 * and allows the user to give inputs that
 * serve as the controller in the MVC
 * design pattern.
 * 
 * @author narlock
 *
 */
public class PpalmsGui extends JFrame {
	
	/**
	 * See {@link ViewStrategy} for definition.
	 */
	private ViewStrategy viewStrategy;
	
	/**
	 * See {@link PpalmsInputHandler} for definition.
	 */
	private PpalmsInputHandler controller;
	
	/**
	 * The PpalmsGui constructor defines the Java
	 * JFrame in which creates the window that
	 * the user will interact with.
	 * 
	 * Defines the member attributes.
	 */
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
	
	/**
	 * Getter method for ViewStrategy
	 * @return viewStrategy
	 */
	public ViewStrategy getViewStrategy() {
		return viewStrategy;
	}
	
	/**
	 * Getter method for PpalmsInputHandler
	 * @return controller
	 */
	public PpalmsInputHandler getPpalmsInputHandler() {
		return controller;
	}
	
	/**
	 * Getter method for private method getAnnotationLineButton
	 * 
	 * @param exportProblem
	 * @param index
	 * @param line
	 * @return createAnnotationLineButton(exportProblem, index, line)
	 */
	public JButton getAnnotationLineButton(JButton exportProblem, int index, String line) {
		return createAnnotationLineButton(exportProblem, index, line);
	}
	
	
	/**
	 * setCommmunicationActions method defines how the
	 * view's components like buttons or text fields will be
	 * a controller event.
	 * 
	 * This method works by checking the current "state" of the
	 * view. Given this state, we can define the actions that
	 * the user can control given that view.
	 */
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
	
	/**
	 * A helper function that updates the view.
	 * 
	 * @param newStrategy
	 */
	public void updateViewStrategy(ViewStrategy newStrategy) {
		this.setSize(499,500);
		this.remove(viewStrategy);
		viewStrategy = newStrategy;
		setCommunicationActions();
		this.add(viewStrategy);
		this.repaint();
		this.setSize(500,500);
	}
	
	/**
	 * A helper function that refocuses the JFrame.
	 * The purpose of this is to "refresh" the frame
	 * that is needed on some updates.
	 */
	private void refocusFrame() {
		this.setSize(499, 500);
		this.setSize(500, 500);
	}
	
	/**
	 * A helper function in which creates an annotation line button
	 * that is used in the LMSInputStrategy. The reason that
	 * this has been taken out is so that the application can
	 * reuse this function for each of the lines the user
	 * inputs.
	 * 
	 * @param exportProblem
	 * @param index
	 * @param line
	 * @return JButton for a line in the inputted source code.
	 */
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