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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.PpalmsInputHandler;
import view.AnnotationInterface;

/**
 * PpalmsGui
 * 
 * @brief The PPALMS Graphical User Interface.
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
 * In the PPALMS design document, PpalmsGui
 * is one of the classes specified. It's purpose
 * is reflected in that document.
 * 
 * @author Anthony Narlock
 *
 */
public class PpalmsGui extends JFrame {
	
	/**
	 * @brief See {@link ViewStrategy} for definition.
	 */
	private ViewStrategy viewStrategy;
	
	/**
	 * @brief See {@link PpalmsInputHandler} for definition.
	 */
	private PpalmsInputHandler controller;
	
	/**
	 * @brief See {@link AnnotationInterface} for definition.
	 */
	private AnnotationInterface annotationInterface; 
	
	/**
	 * @brief The PpalmsGui constructor defines the Java
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
	 * @brief Getter method for ViewStrategy
	 * @return viewStrategy
	 */
	public ViewStrategy getViewStrategy() {
		return viewStrategy;
	}
	
	/**
	 * @brief Getter method for PpalmsInputHandler
	 * @return controller
	 */
	public PpalmsInputHandler getPpalmsInputHandler() {
		return controller;
	}
	
	/**
	 * @brief setCommmunicationActions method defines how the
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
								if(controller.processInput(yourFileLines)) {
									updateViewStrategy(new LMSInputStrategy());
								} else {
									viewStrategy.showErrorDialog("Invalid source code file.\nThe number of lines in the source code must\nbe between 1 and 50.");
								}
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
			JSpinner numberOfStudentsSpinner = ((LMSInputStrategy) viewStrategy).getNumberOfStudentsSpinner();
			JButton confirmLmsTargetButton = ((LMSInputStrategy) viewStrategy).getConfirmLmsTargetButton();
			lmsTargetComboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!controller.processInput(lmsTargetComboBox, "lmsTarget")) {
						problemTypeComboBox.setEnabled(false);
						confirmLmsTargetButton.setEnabled(false);
						numberOfStudentsSpinner.setEnabled(false);
					} else {
						problemTypeComboBox.setEnabled(true);
						if(problemTypeComboBox.getSelectedIndex() != 0) {
							numberOfStudentsSpinner.setEnabled(true);
						}
					}
				}
			});
			problemTypeComboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!controller.processInput(problemTypeComboBox, "problemType")) {
						numberOfStudentsSpinner.setEnabled(false);
						confirmLmsTargetButton.setEnabled(false);
					} else {
						numberOfStudentsSpinner.setEnabled(true);
						confirmLmsTargetButton.setEnabled(true);
					}
				}
			});
			numberOfStudentsSpinner.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					//This will always return true due to the nature of a JSpinner
					//It automatically performs input validation.
					controller.processInput(numberOfStudentsSpinner, "numberOfStudents");
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
					else {
						viewStrategy.showErrorDialog("A system error occurred which prevented the file from being made.\nMost likely, the file \"problem.json\" already exists, and you should move or delete it.");
						
					}
				}
				
			});
			
			//Get the annotation panel
			JPanel annotationPanel = ((ProblemInputStrategy) viewStrategy).getAnnotationPanel();
			List<String> alines = controller.getProblem().getSourceCodeLines();
			
		    //Set the controls of the annotation panel
		    switch (controller.getProblem().getProblemType()) {
			    case Ordering:
					this.annotationInterface = new ChooseLinesAnnotation(getPpalmsInputHandler(), getPpalmsInputHandler().getProblem());
					for(int i = 0; i < alines.size(); i++) {
						annotationPanel.add(((ChooseLinesAnnotation) annotationInterface).createAnnotationLineButton(exportProblem, i, alines.get(i)));
					}
					break;
			    case FillInTheBlank:
			    	this.annotationInterface = new ChooseLinesAddBlank(getPpalmsInputHandler(), getPpalmsInputHandler().getProblem());
					for(int i = 0; i < alines.size(); i++) {
						annotationPanel.add(((ChooseLinesAddBlank) annotationInterface).createAnnotationLineButtonPanel(exportProblem, i, alines.get(i)));
					}
			    	break;
			    case MultipleChoice:
			    	this.annotationInterface = new ChooseLinesAnnotation(getPpalmsInputHandler(), getPpalmsInputHandler().getProblem());
			    	for(int i = 0; i < alines.size(); i++) {
			    		annotationPanel.add(((ChooseLinesAnnotation) annotationInterface).createAnnotationLineButton(exportProblem, i, alines.get(i)));
			    	}
			    	break;
		    }
			refocusFrame();
		}
	}
	
	private PpalmsInputHandler getPpalmsProblemType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @brief A helper function that updates the view.
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
	 * @brief A helper function that refocuses the JFrame.
	 * The purpose of this is to "refresh" the frame
	 * that is needed on some updates.
	 */
	private void refocusFrame() {
		this.setSize(499, 500);
		this.setSize(500, 500);
	}
	
	public AnnotationInterface getAnnotationInterface() { return this.annotationInterface; }

}