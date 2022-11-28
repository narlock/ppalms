package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * ProblemInputStrategy is the view "state" in which
 * the PPALMS application will allow the user to input
 * problem title, problem description, create
 * problem annotations, and export the problem.
 * 
 * In the PPALMS design document, ProblemInputStrategy
 * is one of the classes specified. It's purpose
 * is reflected in that document.
 * 
 * @author Anthony Narlock
 *
 */
public class ProblemInputStrategy extends ViewStrategy {

	/**
	 * The label component indicating title for problem.
	 */
	private JLabel titleInputLabel;
	
	/**
	 * The text field component that the user can input
	 * title with their keyboard for the problem.
	 */
	private JTextField titleInputTextField;
	
	/**
	 * The label component indicating description
	 * for the problem.
	 */
	private JLabel descriptionInputLabel;
	
	/**
	 * The text area component that the user can input
	 * description with their keyboard for the problem.
	 */
	private JTextArea descriptionInputTextField;
	
	/**
	 * The label component indicating annotation
	 * area for the problem.
	 */
	private JLabel annotationsLabel;
	
	/**
	 * The sourceCodeArea and annotationPanel are
	 * panels that define problem annotation interface.
	 */
	private JPanel sourceCodeArea;
	private JPanel annotationPanel;
	
	/**
	 * The button component that allows the user
	 * to export their created problem.
	 */
	private JButton exportProblem;
	
	@Override
	public void setViewPanel() {
		// TODO Auto-generated method stub
		JPanel verticalPanel = new JPanel();
		verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
		titleInputLabel = new JLabel("Problem Title");
		titleInputTextField = new JTextField(10);
		descriptionInputLabel = new JLabel("Problem Description");
		descriptionInputTextField = new JTextArea(5, 10);
		JScrollPane descriptionScrollPane = new JScrollPane(descriptionInputTextField);
		annotationsLabel = new JLabel("Annotations");
		annotationPanel = new JPanel();
		annotationPanel.setLayout(new BoxLayout(annotationPanel, BoxLayout.Y_AXIS));
		annotationPanel.setSize(10, 10);
		JScrollPane scrollPane = new JScrollPane(annotationPanel);
	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setSize(450,200);
        sourceCodeArea = new JPanel(null);
        sourceCodeArea.setPreferredSize(new Dimension(500, 400));
        sourceCodeArea.add(scrollPane);
		exportProblem = new JButton("Export Problem");
		
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		verticalPanel.add(titleInputLabel);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		verticalPanel.add(titleInputTextField);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		verticalPanel.add(descriptionInputLabel);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		verticalPanel.add(descriptionScrollPane);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		verticalPanel.add(annotationsLabel);
		verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		verticalPanel.add(sourceCodeArea);
		this.add(exportProblem, BorderLayout.SOUTH);
		this.add(verticalPanel);
	}
	

	/**
	 * Setter for default controller actions.
	 */
	@Override
	public void setControllerActions() {
		exportProblem.setEnabled(false);
	}
	
	/**
	 * Getter method for problem title input text field.
	 * 
	 * @return titleInputTextField
	 */
	public JLabel getTitleInputLabel() { return titleInputLabel; }
	public JTextField getTitleInputTextField() { return titleInputTextField; }
	
	/**
	 * Getter method for problem description input text field. 
	 * 
	 * @return descriptionInputTextField
	 */
	public JLabel getDescriptionInputLabel() { return descriptionInputLabel; }
	public JTextArea getDescriptionInputTextField() { return descriptionInputTextField; }
	
	/**
	 * Getter method for exported Problem.
	 * 
	 * @return exportProblem
	 */
	public JButton getExportProblem() { return exportProblem; }
	
	/**
	 * Getter method for problem annotation panel.
	 * 
	 * @return annotationPanel
	 */
	public JPanel getAnnotationPanel() { return annotationPanel; }
	
	/**
	 * Getter method for problem annotation label.
	 * 
	 * @return annotationsLabel
	 */
	public JLabel getAnnotationsLabel() { return annotationsLabel; }
}
