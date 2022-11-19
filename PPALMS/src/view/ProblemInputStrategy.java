package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProblemInputStrategy extends ViewStrategy {

	private JLabel titleInputLabel;
	private JTextField titleInputTextField;
	private JLabel descriptionInputLabel;
	private JTextArea descriptionInputTextField;
	private JLabel annotationsLabel;
	private JPanel contentPane;
	private JPanel annotationPanel;
	private JScrollPane annotationPane;
	private JTextArea sourceCodeArea; //May be changed... May need something better for ordering.. maybe stacked panels that are movable.
	// private List<JPanel> sourceCodeArea; // We can use this to stack problem lines on top of one another
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
        contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
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
		verticalPanel.add(contentPane);
		this.add(exportProblem, BorderLayout.SOUTH);
		this.add(verticalPanel);
	}

	@Override
	public void setControllerActions() {
		exportProblem.setEnabled(false); //TODO May need to be set to false depending on problem type.
	}

	public JTextField getTitleInputTextField() { return titleInputTextField; }
	public JTextArea getDescriptionInputTextField() { return descriptionInputTextField; }
	public JButton getExportProblem() { return exportProblem; }
	public JPanel getAnnotationPanel() { return annotationPanel; }
}
